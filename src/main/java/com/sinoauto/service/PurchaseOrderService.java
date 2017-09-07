package com.sinoauto.service;
import org.springframework.beans.factory.annotation.Autowired;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.mapper.PurchaseOrderMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dao.bean.HqlsLogisticsCompany;
import com.sinoauto.dao.bean.HqlsLogisticsLog;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsOrderDetail;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPurchaseOrder;
import com.sinoauto.dao.bean.HqlsShipAddress;
import com.sinoauto.dao.bean.HqlsStoreFinance;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.LogisticsCompanyMapper;
import com.sinoauto.dao.mapper.LogisticsLogMapper;
import com.sinoauto.dao.mapper.OrderDetailMapper;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dao.mapper.ShipAddressMapper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PayReturnParamDto;
import com.sinoauto.dto.PurchaseOrderDto;
import com.sinoauto.dto.PurchaseOrderParamDto;
import com.sinoauto.dto.PurchaseOrderQueryDto;
import com.sinoauto.dto.SettlementOperationParamDto;
import com.sinoauto.dto.ShopCartInfoDto;
import com.sinoauto.dto.ShopCartParamDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;
import com.sinoauto.util.KdniaoTrackQueryAPI;

import cn.jiguang.common.utils.StringUtils;


@Service
public class PurchaseOrderService {

	@Autowired
	private ShipAddressMapper shipAddressMapper;
	@Autowired
	private PartsMapper partsMapper;
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private FinanceFlowMapper financeFlowMapper;
	@Autowired
	private StoreFinanceMapper storeFinanceMapper;
	@Autowired
	private LogisticsCompanyMapper logisticsCompanyMapper;
	@Autowired
	private LogisticsLogMapper logisticsLogMapper;
	
	@Transactional
	public ResponseEntity<RestModel<String>> addShipAddress(HqlsShipAddress shipAddress) {
		try {
			if (shipAddress.getShipAddressId() != null) {
				shipAddressMapper.update(shipAddress);
			} else {
				if (shipAddress.getIsDefault() == null) {
					shipAddress.setIsDefault(0);
				}
				shipAddressMapper.insert(shipAddress);
			}
			return RestModel.success();
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "新增异常！");
	}
	
	public ResponseEntity<RestModel<HqlsShipAddress>> getAddressById(Integer addressId) {
		
		return RestModel.success(shipAddressMapper.getAddressById(addressId));
	}
	
	@Transactional
	public ResponseEntity<RestModel<String>> updateShipAddress(HqlsShipAddress shipAddress) {
		try {
			shipAddressMapper.update(shipAddress);
			return RestModel.success("修改收货地址成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "修改异常！");
	}
	
	@Transactional
	public ResponseEntity<RestModel<String>> deleteShipAddress(Integer shipAddressId) {
		try {
			shipAddressMapper.delete(shipAddressId);
			return RestModel.success("删除收货地址成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "删除异常！");
	}
	
	@Transactional
	public ResponseEntity<RestModel<String>> batchDeleteShipAddress(Integer[] shipAddressIds) {
		try {
			shipAddressMapper.batchDelete(shipAddressIds);
			return RestModel.success("批量删除收货地址成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "删除异常！");
	}
	
	public ResponseEntity<RestModel<List<HqlsShipAddress>>> findAll(Integer storeId) {
		
		return RestModel.success(shipAddressMapper.findAll(storeId));
	}
	
	/**
	 * 生成结算订单
	 * @param orderParamDto
	 */
	public Integer generatorPurchaseOrder(SettlementOperationParamDto orderParamDto) {
		// 获取商品列表
		List<ShopCartParamDto> parts = orderParamDto.getPartsList();
		// 生成订单
		HqlsPurchaseOrder order = new HqlsPurchaseOrder();
		order.setOrderNo("HQ" + orderParamDto.getStoreId() + System.currentTimeMillis());
		BigDecimal result = new BigDecimal(0.00);
		for (ShopCartParamDto p: parts) {
			HqlsParts hqpart = partsMapper.getPartsByPartsId(p.getPartsId());
			BigDecimal eachOrderPrice = new BigDecimal(hqpart.getCurPrice()).multiply(new BigDecimal(p.getNum()));
			result = result.add(eachOrderPrice);
		}
		Double discount = result.subtract(new BigDecimal(orderParamDto.getPayMoney())).setScale(2, RoundingMode.HALF_UP).doubleValue();
		order.setDiscountFee(discount);
		order.setOrderStatus(1);
		order.setShipAddressId(orderParamDto.getAddressId());
		order.setStoreId(orderParamDto.getStoreId());
		order.setOtherFee(orderParamDto.getOtherFee());
		order.setTotalFee(result.setScale(2, RoundingMode.HALF_UP).doubleValue());
		purchaseOrderMapper.insert(order);
		
		// 生成订单详情表
		for (ShopCartParamDto partDesc: parts) {
			HqlsOrderDetail detail = new HqlsOrderDetail();
			detail.setBuyCount(partDesc.getNum());
			detail.setBuyPrice(partDesc.getBuyPrice());
			detail.setPartsId(partDesc.getPartsId());
			detail.setDiscountPrice(0.00);
			detail.setPurchaseOrderId(order.getPurchaseOrderId());
			orderDetailMapper.insert(detail);
		}
		return order.getPurchaseOrderId();
	}
	
	/**
	 * 进入结算页
	 * @return
	 */
	public ResponseEntity<RestModel<ShopCartInfoDto>> settlementOperation(SettlementOperationParamDto orderParamDto) {
		try {
			List<ShopCartParamDto> parts = orderParamDto.getPartsList();
			if (parts.isEmpty()) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "请先选择商品");
			}
			// 查询每件商品的信息
			List<PartsDesListDto> partsList = new ArrayList<>();
			for (ShopCartParamDto sc: parts) {
				PartsDesListDto partsDesc = partsMapper.checkShopCart(sc.getPartsId());
				partsDesc.setPurchaseNum(sc.getNum());
				partsList.add(partsDesc);
			}
			// 默认地址获取
			
			// 组装返回数据
			ShopCartInfoDto order = new ShopCartInfoDto();
			order.setPartsDesList(partsList);
			order.setTotalAmount(calculationAmount(partsList));
			// 获取门店余额
			HqlsStoreFinance finance = storeFinanceMapper.getStoreFinance(orderParamDto.getStoreId());
			order.setBalance(finance.getBalance());
			if (finance.getBalance() != null) {
				order.setIsEnough(finance.getBalance() > order.getTotalAmount());
			} else {
				order.setIsEnough(false);
			}
			return RestModel.success(order);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "异常");
	}
	
	public ResponseEntity<RestModel<ShopCartInfoDto>> checkShopCart(List<ShopCartParamDto> param) {
		// 购物车中没商品
		if (param.isEmpty()) {
			return RestModel.success(null);
		}
		try {
			List<PartsDesListDto> partsList = new ArrayList<>();
			for (ShopCartParamDto sc: param) {
				PartsDesListDto partsDesc = partsMapper.checkShopCart(sc.getPartsId());
				partsDesc.setPurchaseNum(sc.getNum());
				partsList.add(partsDesc);
			}
			ShopCartInfoDto infoList = new ShopCartInfoDto();
			infoList.setPartsDesList(partsList);
			infoList.setTotalAmount(calculationAmount(partsList));// 计算商品总价
			
			return RestModel.success(infoList);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "查询异常");
	}
	
	/**
	 * 	按订单状态查询不同门店的信息
	 * 	@User liud
	 * 	@Date 2017年8月29日下午2:36:29
	 * 	@param storeId
	 * 	@param orderStatus
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<PurchaseOrderParamDto>>> findOrderByStatus(Integer storeId, Integer orderStatus) {
		return RestModel.success( purchaseOrderMapper.findOrder(storeId, orderStatus));
	}
	
	public ResponseEntity<RestModel<ShopCartInfoDto>> getOrderByOrderId(Integer orderId) {
		//计算总费用
		ShopCartInfoDto order = purchaseOrderMapper.getOrderByOrderId(orderId);
		BigDecimal totalFee = new BigDecimal(calculationAmount(order.getPartsDesList()));
		totalFee = totalFee.add(new BigDecimal(order.getOtherFee()));
		order.setTotalAmount(totalFee.setScale(2, RoundingMode.HALF_UP).doubleValue());
		order.setIsEnough(order.getBalance() > order.getTotalAmount());
		
		return RestModel.success(order);
	}
	
	@Transactional
	public ResponseEntity<RestModel<PayReturnParamDto>> payOperation(SettlementOperationParamDto param, String token) {
		// 获取当前用户
		RestModel<TokenModel> rest = authService.validToken(token);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		HqlsUser user = userMapper.getUserByGloabUserId(userId);
		if (user == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA, "用户不存在");
		}
		
		try {
			// 生成待支付订单
			Integer orderId = param.getOrderId() == null ? generatorPurchaseOrder(param) : param.getOrderId();
			HqlsPurchaseOrder order = purchaseOrderMapper.getOrderById(orderId);
			/*
			 *  余额支付方式
			 */
			if (param.getPayType() == 0) {
				// 从财务余额中扣掉支付金额
				HqlsStoreFinance finance = storeFinanceMapper.getStoreFinance(param.getStoreId());
//				Double[] returnMoney = calculationBalance(finance, param.getPayMoney());
				BigDecimal balance = new BigDecimal(finance.getBalance()).subtract(new BigDecimal(param.getPayMoney()));
				balance.setScale(2, RoundingMode.HALF_UP);
				finance.setBalance(balance.doubleValue());
				storeFinanceMapper.updateBalance(finance);
				
				// 修改订单
				order.setOrderStatus(2);
				order.setPayFee(param.getPayMoney());
				order.setPayType(param.getPayType());
				purchaseOrderMapper.payOperation(order);
				
				// 添加财务流水
				HqlsFinanceFlow flow = new HqlsFinanceFlow();
				flow.setChangeMoney(param.getPayMoney());
				flow.setChangeType(3);
				flow.setChargeType(2);
				flow.setIsDelete(0);
				flow.setOperPerson(user.getUserName());
				flow.setOrderNo(order.getOrderNo());
				flow.setStoreId(param.getStoreId());
				flow.setFlowStatus(1);
				financeFlowMapper.insert(flow);
				return RestModel.success();
				
			} else if (param.getPayType() == 1) { // 支付宝支付
				PayReturnParamDto pay = new PayReturnParamDto();
				pay.setChangeType(3);// 采购
				pay.setMoney(param.getPayMoney());
				pay.setOrderNo(order.getOrderNo());
				pay.setStoreId(order.getStoreId());
				return RestModel.success(pay);
			}
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "支付异常");
	}
	
	public ResponseEntity<RestModel<Double>> queryBalance(Integer storeId) {
		HqlsStoreFinance finance = storeFinanceMapper.getStoreFinance(storeId);
		if (finance == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "门店Id不存在！");
		}
		return RestModel.success(finance.getBalance());
	}
	
	public ResponseEntity<RestModel<String>> confirmReceipt(Integer orderId) {
		try {
			HqlsPurchaseOrder order = purchaseOrderMapper.getOrderById(orderId);
			if (order == null) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "订单不存在！");
			}
			order.setOrderStatus(4);
			purchaseOrderMapper.confirmReceipt(order);
			return RestModel.success("确认收货完成");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "确认收货异常");
	}
	
	/**
	 * 因不可用提现和可用提现暂时搁置。此段代码暂时不用
	 * 
	 * 用余额支付订单时计算门店余额中可用提现和不可用提现的扣除
	 * @deprecated
	 * @return Double[余额，不可用提现，可用提现]
	 * @author wuxiao
	 */
	public Double[] calculationBalance(HqlsStoreFinance finance, Double payMoney) {
		// 余额减去支付金额
		BigDecimal diffBalance = new BigDecimal(finance.getBalance()).subtract(new BigDecimal(payMoney)).setScale(2, RoundingMode.HALF_UP);
		if (diffBalance.doubleValue() < 0) {
			return null;
		}
		// 不可用提现减去支付金额
		BigDecimal diff = new BigDecimal(finance.getCashDisable()).subtract(new BigDecimal(payMoney)).setScale(2, RoundingMode.HALF_UP);
		Double[] returnMoney = new Double[3];
		// 剩余余额
		returnMoney[0] = diffBalance.doubleValue();
		if (diff.doubleValue() < 0) {
			// 先扣除不可用提现，再扣可用提现 （可用提现-（支付金额-不可用提现））
			Double CashAble = (new BigDecimal(finance.getCashAble())
					.subtract(new BigDecimal(payMoney).subtract(new BigDecimal(finance.getCashDisable())))).setScale(2, RoundingMode.HALF_UP).doubleValue();
			// 不可用提现用完
			returnMoney[1] = 0.0;
			// 可用提现
			returnMoney[2] = CashAble;
		} else {
			// 不可用提现
			returnMoney[1] = diff.doubleValue();
			// 可用提现
			returnMoney[2] = finance.getCashAble();
		}
		return returnMoney;
	}
	
	/**
	 * 计算购物车、支付操作时的所有商品金额
	 * @return
	 */
	public Double calculationAmount(List<PartsDesListDto> parts) {
		
		if (parts.isEmpty()) return 0.00;
		BigDecimal result = new BigDecimal(0.00);
		for (PartsDesListDto p: parts) {
			HqlsParts hqpart = partsMapper.getPartsByPartsId(p.getPartsId());
			BigDecimal eachOrderPrice = new BigDecimal(hqpart.getCurPrice()).multiply(new BigDecimal(p.getPurchaseNum()));
			result = result.add(eachOrderPrice);
		}
		result.setScale(2, RoundingMode.HALF_UP);
		return result.doubleValue();
	}
 
	/**
	 * 	查询采购订单列表
	 * 	@User liud
	 * 	@Date 2017年8月17日下午5:59:44
	 * 	@param purchaseOrderDto
	 * 	@return
	 */
	public ResponseEntity<RestModel<Page<PurchaseOrderDto>>> findPurchaseOrderByContidion(PurchaseOrderQueryDto purchaseOrderDto,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex,pageSize);
		List<PurchaseOrderDto> purchaseOrders=null;
		Page<PurchaseOrderDto> PurchaseOrderpage=null;
		try {
			purchaseOrders = purchaseOrderMapper.findPurchaseOrderByContidion(purchaseOrderDto);
			if(purchaseOrders==null){
				purchaseOrders =new ArrayList<>();
			}
			PurchaseOrderpage=(Page<PurchaseOrderDto>)purchaseOrders;
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"查询采购订单异常");
		}
		return RestModel.success(PurchaseOrderpage);
	}
	
	/**
	 *  确认发货
	 * 	@User liud
	 * 	@Date 2017年8月17日下午8:14:08
	 * 	@return
	 */
	public ResponseEntity<RestModel<Integer>> confirmShipment(HqlsPurchaseOrder order){
		try {
			purchaseOrderMapper.confirmShipment(order);
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"确认发货异常");
		}
		return RestModel.success();
	}
	
	/**
	 * 查询所有物流公司
	 * @return
	 */
	public ResponseEntity<RestModel<List<CommonDto>>> findAllLogisticsCompany() {
		
		return RestModel.success(logisticsCompanyMapper.findAll());
	}
	
	/**
	 * 发货操作
	 * @param orderId
	 * @param logisticsId
	 * @param logisticsNo
	 * @return
	 * @author wuxiao
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> shipOperation(Integer orderId, Integer logisticsId, String logisticsNo, String remark) {
		try {
			HqlsPurchaseOrder order = purchaseOrderMapper.getOrderById(orderId);
			if (logisticsId == null && StringUtils.isEmpty(logisticsNo)) {
				// 没有选择物流公司的情况下，将备注记入物流日志
				HqlsLogisticsLog log = new HqlsLogisticsLog();
				log.setPurchaseOrderId(orderId);
				log.setRemark(remark);
				logisticsLogMapper.insert(log);
				
				// 更改订单状态
				order.setOrderStatus(3);
				purchaseOrderMapper.updateOrderStatus(order);
			} else {
				//更新订单
				order.setLogisticsId(logisticsId);
				order.setLogisticsNo(logisticsNo);
				order.setOrderStatus(3);
				purchaseOrderMapper.update(order);
			}
			return RestModel.success("发货完成");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "发货操作异常");
	}
	
	public ResponseEntity<RestModel<Object>> getLogisticsInfo(Integer orderId) {
		HqlsPurchaseOrder order = purchaseOrderMapper.getOrderById(orderId);
		if (null == order) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "订单Id不存在");
		}
		try {
			Integer logisticsId = order.getLogisticsId();
			String logisticsNo = order.getLogisticsNo();
			// 没有走物流的情况
			if (null == logisticsId && StringUtils.isEmpty(logisticsNo)) {
				return RestModel.success(logisticsLogMapper.findLogisticsLogs(orderId));
			}
			HqlsLogisticsCompany company = logisticsCompanyMapper.getById(logisticsId);
			HqlsShipAddress address = shipAddressMapper.getShipAddressById(order.getShipAddressId());
			//查询快递鸟
			KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
			String add = address.getProvinceName() + address.getCityName() + address.getCountyName() + address.getAddress();
			String result = api.getOrderTracesByJson(company.getCompanyNo(), logisticsNo, company.getLogisticsName(), add);
			return RestModel.success(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "查询物流异常");
	}
	
	public ResponseEntity<RestModel<List<PurchaseOrderDto>>> findOrderListByContidion(Integer orderStatus, Integer storeId, 
			String userName, String mobile, Integer pageIndex, Integer pageSize){
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		if (userName != null) {
			userName = userName.trim();
		}
		if (mobile != null) {
			mobile = mobile.trim();
		}
		Page<PurchaseOrderDto> orderList = purchaseOrderMapper.findOrderListByContidion(orderStatus, storeId, userName, mobile);
		
		return RestModel.success(orderList, (int) orderList.getTotal());
	}
	
	public ResponseEntity<RestModel<List<PartsDesListDto>>> getPartsByOrderId(Integer orderId) {
		List<HqlsOrderDetail> detail = orderDetailMapper.getDetailByOrderId(orderId);
		List<PartsDesListDto> partsList = new ArrayList<>();
		for (HqlsOrderDetail od: detail) {
			PartsDesListDto des = partsMapper.checkShopCart(od.getPartsId());
			des.setBuyPrice(od.getBuyPrice());
			des.setPurchaseNum(od.getBuyCount());
			partsList.add(des);
		}
		return RestModel.success(partsList);
	}
	
	/**
	 * 采购订单支付宝支付成功回调：更改订单状态为已支付
	 * @param orderNo 订单号
	 * @return 0:更新状态失败； 1：成功
	 */
	@Transactional
	public int updatePurchaseOrderStatus(String orderNo) {
		try {
			return purchaseOrderMapper.updateOrderStatusForPay(orderNo);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
			return 0;
		}
	}
	
	/**
	 * 取消订单
	 * @param orderId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> cancelOrder(Integer orderId, String token) {
		// 获取当前用户
		RestModel<TokenModel> rest = authService.validToken(token);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		HqlsUser user = userMapper.getUserByGloabUserId(userId);
		if (user == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA, "用户不存在");
		}
		try {
			// 修改订单状态为已取消
			HqlsPurchaseOrder order = purchaseOrderMapper.getOrderById(orderId);
			if (order == null) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "订单不存在");
			}
			order.setOrderStatus(5);
			purchaseOrderMapper.updateOrderStatus(order);
			// 将支付金额退还至门店
			storeFinanceMapper.updateStoreBalance(order.getStoreId(), order.getPayFee());
			// 修改财务流水备注
			String remark = "订单已取消，支付金额返还至门店余额！";
			financeFlowMapper.updateOrderRemark(order.getStoreId(), order.getOrderNo(), remark);
			// 添加财务流水
			HqlsFinanceFlow flow = new HqlsFinanceFlow();
			flow.setChangeMoney(order.getPayFee());
			flow.setChangeType(3);
			flow.setChargeType(1);
			flow.setIsDelete(0);
			flow.setOperPerson(user.getUserName());
			flow.setOrderNo(order.getOrderNo());
			flow.setStoreId(order.getStoreId());
			flow.setFlowStatus(1);
			flow.setRemark("订单取消退还金额");
			financeFlowMapper.insert(flow);
			
			return RestModel.success("订单取消成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
	}
	
	/**
	 * 添加物流日志
	 * @param orderId
	 * @param remark
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> addLogisticsRemark(Integer orderId, String remark) {
		try {
			HqlsLogisticsLog log = new HqlsLogisticsLog();
			log.setPurchaseOrderId(orderId);
			log.setRemark(remark);
			logisticsLogMapper.insert(log);
			return RestModel.success("添加成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
	}
	
}