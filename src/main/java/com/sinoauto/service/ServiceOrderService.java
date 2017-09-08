package com.sinoauto.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsCustomer;
import com.sinoauto.dao.bean.HqlsExtraOrder;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dao.bean.HqlsServiceOrder;
import com.sinoauto.dao.bean.HqlsServiceType;
import com.sinoauto.dao.bean.HqlsStore;
import com.sinoauto.dao.bean.HqlsStoreFinance;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.ClientInfoMapper;
import com.sinoauto.dao.mapper.CustomerMapper;
import com.sinoauto.dao.mapper.ExtraOrderMapper;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.ServiceOrderMapper;
import com.sinoauto.dao.mapper.ServiceTypeMapper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dao.mapper.StoreMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.ServiceOrderDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RespEntity;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;
import com.sinoauto.util.HttpUtil;
import com.sinoauto.util.push.GeTuiUtil;
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
	@Autowired
	private ClientInfoMapper clientInfoMapper;
	@Autowired
	private ExtraOrderMapper extraOrderMapper;

	public ResponseEntity<RestModel<List<ServiceOrderDto>>> findServiceOrdersByOrderStatus(Integer orderStatus, Integer storeId, Integer pageIndex,
			Integer pageSize) {
		if (pageSize != null && pageIndex != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<ServiceOrderDto> orders = serviceOrderMapper.findServiceOrdersByOrderStatus(orderStatus, storeId);
		return RestModel.success(orders, (int) orders.getTotal());
	}

	@Transactional
	public ResponseEntity<RestModel<String>> finishOrderByCode(String token, String code) {
		Integer serviceOrderId = serviceOrderMapper.getOrderIdByCode(code);
		if (serviceOrderId != null && serviceOrderId > 0) {
			return finishOrder(token, serviceOrderId, code);
		} else {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "核销码不正确！");
		}

	}

	/**
	 * 服务订单完成功能
	 * @param serviceOrderId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> finishOrder(String token, Integer serviceOrderId, String code) {
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
			// 核销
			String res = checkCode(code, order.getOrderNo());
			if (res.contains("success")) {
				// 订单完成操作
				serviceOrderMapper.updateOrderStauts(serviceOrderId);
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
				financeFlow.setIsDelete(0);
				financeFlowMapper.insert(financeFlow);
				return RestModel.success("操作成功");
			} else if (res.contains("-5")) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "核销码已使用过！");
			} else if (res.contains("-3")) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "核销码错误！");
			} else if (res.contains("-2")) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "订单号错误！");
			} else {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "其他错误！");
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);
	}

	/**
	 * 服务订单完成功能
	 * @param serviceOrderId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> finishOrderedOrder(Integer serviceOrderId) {
		// 判断服务订单是否已经完成，
		// 未完成的订单，确认完成
		try {
			HqlsServiceOrder order = serviceOrderMapper.getServiceOrderByOrderId(serviceOrderId);
			if (order == null) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "订单号不正确");
			}
			if (order.getOrderStatus() == 2) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "订单已完成");
			}
			serviceOrderMapper.updateOrderStauts(serviceOrderId);
			return RestModel.success("订单完成！");
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
			} else {
				if (!StringUtils.isEmpty(order.getAvatarUrl())) {
					customer.setAvatarUrl(order.getAvatarUrl());
				}
				customer.setCustomerName(order.getCustomerName());
				customerMapper.updateCustomer(customer);
			}
			order.setCustomerId(customer.getCustomerId());
			// order.setOrderType(1);//服务订单
			// 新增一条服务订单信息
			serviceOrderMapper.insert(order);
			// 推送给门店的联系人
			// 通过门店ID查找门店的联系人
			HqlsUser user = userMapper.getUserByStoreId(order.getStoreId());
			if (user != null) {
				PushAction pa = new PushAction("ServiceOrder", 0, true, "");
				List<PushAction> action = new ArrayList<>();
				action.add(pa);
				String text = "您有一条新的服务订单";
				if (order.getOrderType() == 2) {
					text = "您有一条新的预约订单";
				}
				// 推送给IOSAPP端
				PushParms parms = PushUtil.comboPushParms(user.getMobile(), action, null, text, "", null, 0);
				PushUtil.push2IOSByAPNS(parms);
				String title = "订单提醒";
				List<String> clientIds = clientInfoMapper.findAllCIdsByUserId(user.getUserId());
				// 推送给安卓APP端
				GeTuiUtil.pushToAndroid(clientIds, title, text, "service_order", "服务订单");
			}
			return RestModel.success("success");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);

	}

	public String checkCode(String code, String orderNo) {
		String url = "http://www.chexiaozhu.cn/api/openapi/dataapi.ashx";
		Map<String, Object> params = new HashMap<>();
		params.put("method", "codeVerification");
		params.put("check", "shopnum1Api");
		params.put("AppKey", "21654209");
		params.put("Md5key", "56fbdc15701c985dd8d798060d9f51e5");
		params.put("OrderNumber", orderNo);
		params.put("Code", code);
		RespEntity res = HttpUtil.request("GET", url, null, params, null);
		return res.getResult();
	}

	public ResponseEntity<RestModel<String>> createExtraOrder(HqlsExtraOrder order) {
		order.setExtraOrderNo(UUID.randomUUID().toString());
		extraOrderMapper.insertExtraOrder(order);
		return RestModel.success("增加成功！");
	}

	@Transactional
	public ResponseEntity<RestModel<String>> orderPayBack(String extraOrderNo, Boolean isPay) {
		List<HqlsExtraOrder> orders = extraOrderMapper.getExtraOrderCountByExtraOrderNo(extraOrderNo);
		if (orders != null && orders.size() == 1) {
			if (isPay) {
				// 修改增项支付状态
				extraOrderMapper.updateExtraOrderPayStatus(extraOrderNo);
				// 修改服务订单的金额
				serviceOrderMapper.updateTotalAmount(orders.get(0).getServiceOrderId(), orders.get(0).getOrderAmount());
				//修改门店余额
				HqlsServiceOrder order = serviceOrderMapper.getServiceOrderByOrderId(orders.get(0).getServiceOrderId());
				int result = storeFinanceMapper.getStoreFinanceByStoreId(order.getStoreId());
				if (result == 0) {// 插入余额记录
					HqlsStoreFinance finance = new HqlsStoreFinance();
					finance.setStoreId(order.getStoreId());
					finance.setRemark("");
					storeFinanceMapper.insert(finance);
				}
				storeFinanceMapper.addCash(order.getStoreId(), orders.get(0).getOrderAmount());
				// 增加账单流水
				HqlsFinanceFlow financeFlow = new HqlsFinanceFlow();
				financeFlow.setStoreId(order.getStoreId());
				financeFlow.setTransactionNo("ZX" + order.getStoreId() + System.currentTimeMillis());
				financeFlow.setChangeType(4);
				financeFlow.setChangeMoney(orders.get(0).getOrderAmount());
				financeFlow.setChargeType(1);
				financeFlow.setOperPerson("服务增项金额");
				financeFlow.setOrderNo(orders.get(0).getExtraOrderNo());
				financeFlow.setIsDelete(0);
				financeFlowMapper.insert(financeFlow);
				//推送信息给b端门店
				HqlsUser user = userMapper.getUserByStoreId(order.getStoreId());
				if (user != null) {
					PushAction pa = new PushAction("ServiceOrder", 0, false, "");
					List<PushAction> action = new ArrayList<>();
					action.add(pa);
					String text = "您收到一笔新的服务款";
					// 推送给IOSAPP端
					PushParms parms = PushUtil.comboPushParms(user.getMobile(), action, null, text, "", null, 0);
					PushUtil.push2IOSByAPNS(parms);
					String title = "服务款";
					List<String> clientIds = clientInfoMapper.findAllCIdsByUserId(user.getUserId());
					// 推送给安卓APP端
					if(clientIds != null && clientIds.size() > 0){
						GeTuiUtil.pushToAndroid(clientIds, title, text, "money", "服务款");
					}
				}
			}
		} else {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "增项订单号不正确!");
		}
		return RestModel.success("修改订单状态成功！");
	}

}