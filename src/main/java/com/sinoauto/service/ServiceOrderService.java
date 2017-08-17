package com.sinoauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dao.bean.HqlsServiceOrder;
import com.sinoauto.dao.bean.HqlsStoreFinance;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.ServiceOrderMapper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.ServiceOrderDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;

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

	public ResponseEntity<RestModel<List<ServiceOrderDto>>> findServiceOrdersByOrderStatus(Integer orderStatus, Integer storeId,Integer pageIndex, Integer pageSize) {
		if (pageSize != null && pageIndex != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<ServiceOrderDto> orders = serviceOrderMapper.findServiceOrdersByOrderStatus(orderStatus,storeId);
		return RestModel.success(orders, (int) orders.getTotal());
	}
	
	/**
	 * 服务订单完成功能
	 * @param serviceOrderId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> finishOrder(String token,Integer serviceOrderId){
		//判断服务订单是否已经完成，
		//未完成的订单，确认完成
		//推送给车小主，更新门店的余额,记录账单
		try{
			// 获取当前用户
			RestModel<TokenModel> rest = authService.validToken(token);
			if (rest.getErrcode() != 0) {// 解析token失败
				return null;
			}
			Integer userId = rest.getResult().getUserId();// 当前登录人的userid
			HqlsUser user = userMapper.getUserByGloabUserId(userId);
			HqlsServiceOrder order = serviceOrderMapper.getServiceOrderByOrderId(serviceOrderId);
			if(order.getOrderStatus() == 2){
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"订单已完成");
			}
			//订单完成操作
			serviceOrderMapper.updateOrderStauts(serviceOrderId);
			//推送给车小主
			//TODO 推送给车小主完成接口
			//更新余额
			int result = storeFinanceMapper.getStoreFinanceByStoreId(order.getStoreId());
			if(result == 0 ){//插入余额记录
				HqlsStoreFinance finance = new HqlsStoreFinance();
				finance.setStoreId(order.getStoreId());
				finance.setRemark("");
				storeFinanceMapper.insert(finance);
			}
			storeFinanceMapper.addCash(order.getStoreId(), order.getOrderAmount());
			//增加账单流水
			HqlsFinanceFlow financeFlow = new HqlsFinanceFlow();
			financeFlow.setStoreId(order.getStoreId());
			financeFlow.setTransactionNo("FW"+order.getStoreId()+System.currentTimeMillis());
			financeFlow.setChangeType(4);
			financeFlow.setChangeMoney(order.getOrderAmount());
			financeFlow.setChargeType(1);
			financeFlow.setOperPerson(user.getUserName());
			financeFlowMapper.insert(financeFlow);
			return RestModel.success("操作成功");
		}catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);
	}

}