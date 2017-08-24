package com.sinoauto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsCustomer;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dao.bean.HqlsServiceOrder;
import com.sinoauto.dao.bean.HqlsServiceType;
import com.sinoauto.dao.bean.HqlsStore;
import com.sinoauto.dao.bean.HqlsStoreFinance;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.CustomerMapper;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.ServiceOrderMapper;
import com.sinoauto.dao.mapper.ServiceTypeMapper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dao.mapper.StoreMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.ServiceOrderDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;
import com.sinoauto.util.push.PushAction;
import com.sinoauto.util.push.PushParms;
import com.sinoauto.util.push.PushUtil;

@Service
public class ServiceOrderService {

	@Autowired
	private ServiceOrderMapper serviceOrderMapper;
	@Autowired
	private StoreFinanceMapper storeFinanceMapper;
	@Autowired
	private FinanceFlowMapper financeFlowMapper;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private CustomerMapper customerMapper;
	@Autowired
	private ServiceTypeMapper serviceTypeMapper;
	@Autowired
	private StoreMapper storeMapper;

	public ResponseEntity<RestModel<List<ServiceOrderDto>>> findServiceOrdersByOrderStatus(Integer orderStatus, Integer storeId, Integer pageIndex,
			Integer pageSize) {
		if (pageSize != null && pageIndex != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<ServiceOrderDto> orders = serviceOrderMapper.findServiceOrdersByOrderStatus(orderStatus, storeId);
		return RestModel.success(orders, (int) orders.getTotal());
	}

	/**
	 * 服务订单完成功能
	 * @param serviceOrderId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> finishOrder(String token, Integer serviceOrderId) {
		// 判断服务订单是否已经完成，
		// 未完成的订单，确认完成
		// 推送给车小主，更新门店的余额,记录账单
		try {
			// 获取当前用户
			RestModel<TokenModel> rest = authService.validToken(token);
			if (rest.getErrcode() != 0) {// 解析token失败
				return null;
			}
			Integer userId = rest.getResult().getUserId();// 当前登录人的userid
			HqlsUser user = userMapper.getUserByGloabUserId(userId);
			HqlsServiceOrder order = serviceOrderMapper.getServiceOrderByOrderId(serviceOrderId);
			if (order.getOrderStatus() == 2) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "订单已完成");
			}
			// 订单完成操作
			serviceOrderMapper.updateOrderStauts(serviceOrderId);
			// 推送给车小主
			// TODO 推送给车小主完成接口
			// 更新余额
			int result = storeFinanceMapper.getStoreFinanceByStoreId(order.getStoreId());
			if (result == 0) {// 插入余额记录
				HqlsStoreFinance finance = new HqlsStoreFinance();
				finance.setStoreId(order.getStoreId());
				finance.setRemark("");
				storeFinanceMapper.insert(finance);
			}
			storeFinanceMapper.addCash(order.getStoreId(), order.getOrderAmount());
			// 增加账单流水
			HqlsFinanceFlow financeFlow = new HqlsFinanceFlow();
			financeFlow.setStoreId(order.getStoreId());
			financeFlow.setTransactionNo("FW" + order.getStoreId() + System.currentTimeMillis());
			financeFlow.setChangeType(4);
			financeFlow.setChangeMoney(order.getOrderAmount());
			financeFlow.setChargeType(1);
			financeFlow.setOperPerson(user.getUserName());
			financeFlowMapper.insert(financeFlow);
			return RestModel.success("操作成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);
	}

	/**
	 * 新增服务订单接口
	 * @param order
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> createOrder(ServiceOrderDto order) {
		try {
			// 判断传过来的服务项目是否存在
			HqlsServiceType serviceType = serviceTypeMapper.getServiceTypesByServiceTypeName(order.getServiceType());
			if (serviceType == null) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "服务项目未正确匹配");
			}
			// 根据门店编码获取门店ID
			HqlsStore store = storeMapper.getStoreByStoreCode(order.getStoreCode());
			if (store == null) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "门店编码未正确匹配");
			}
			order.setStoreId(store.getStoreId());
			order.setServiceTypeId(serviceType.getServiceTypeId());
			// 新增一条客户信息(先判断客户是否存在)
			HqlsCustomer customer = customerMapper.getCustomerByMobile(order.getCustomerMobile());
			if (customer == null) {
				customer = new HqlsCustomer();
				customer.setCustomerName(order.getCustomerName());
				customer.setAvatarUrl(order.getAvatarUrl());
				customer.setMobile(order.getCustomerMobile());
				customerMapper.insert(customer);
			}
			order.setCustomerId(customer.getCustomerId());
			//order.setOrderType(1);//服务订单
			// 新增一条服务订单信息
			serviceOrderMapper.insert(order);
			// 推送给门店的联系人
			// 通过门店ID查找门店的联系人
			HqlsUser user = userMapper.getUserByStoreId(order.getStoreId());
			PushAction pa = new PushAction("serviceorder", 0, true, "");
			List<PushAction> action = new ArrayList<>();
			action.add(pa);
			String title = "您有一条新的服务订单";
			if(order.getOrderType() == 2){
				title = "您有一条新的预约订单";
			}
			PushParms parms = PushUtil.comboPushParms(user.getMobile(), action, null, title, "", null, 0);
			PushUtil.push2Andriod(parms);
			PushUtil.push2IOSByAPNS(parms);
			return RestModel.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);

	}

}