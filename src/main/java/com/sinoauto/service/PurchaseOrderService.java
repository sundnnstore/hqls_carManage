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
import com.sinoauto.dto.PurchaseOrderDto;
import com.sinoauto.dto.PurchaseOrderParamDto;
import com.sinoauto.dto.PurchaseOrderQueryDto;
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
			shipAddressMapper.insert(shipAddress);
			return RestModel.success("新增收货地址成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "新增异常！");
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
	
	public ResponseEntity<RestModel<List<HqlsShipAddress>>> findAll() {
		return RestModel.success(shipAddressMapper.findAll());
	}
	
	@Transactional
	public ResponseEntity<RestModel<String>> generatorPurchaseOrder(PurchaseOrderParamDto orderParamDto) {
		try {
			// 获取商品列表
			List<PartsDesListDto> parts = orderParamDto.getPartsList();
			// 生成订单
			HqlsPurchaseOrder order = new HqlsPurchaseOrder();
			order.setOrderNo("HQ" + orderParamDto.getStoreId() + System.currentTimeMillis());
			order.setDiscountFee(0.00);
			order.setOrderStatus(1);
			order.setOtherFee(orderParamDto.getOtherFee());
			order.setPayFee(calculationAmount(parts));
			order.setRemark(orderParamDto.getRemark());
			purchaseOrderMapper.insert(order);
			
			// 生成订单详情表
			for (PartsDesListDto partDesc: parts) {
				HqlsOrderDetail detail = new HqlsOrderDetail();
				detail.setBuyCount(partDesc.getPurchaseNum());
				detail.setBuyPrice(partDesc.getCurPrice());
				detail.setPartsId(partDesc.getPartsId());
				detail.setDiscountPrice(0.00);
				detail.setPurchaseOrderId(order.getPurchaseOrderId());
				orderDetailMapper.insert(detail);
			}
			return RestModel.success("创建订单成功！");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "生成订单异常");
	}
	
	public ResponseEntity<RestModel<ShopCartInfoDto>> checkShopCart(List<ShopCartParamDto> param) {
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
			infoList.setTotalAmount(calculationAmount(partsList));
			
			return RestModel.success(infoList);
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "购物车查询异常");
	}
	
	public ResponseEntity<RestModel<List<PurchaseOrderParamDto>>> findOrderByStatus(Integer storeId, Integer orderStatus) {
		
		return RestModel.success(purchaseOrderMapper.findOrder(storeId, orderStatus));
	}
	
	public ResponseEntity<RestModel<PurchaseOrderParamDto>> getOrderByOrderId(Integer orderId) {
		//计算总费用
		PurchaseOrderParamDto order = purchaseOrderMapper.getOrderByOrderId(orderId);
		BigDecimal totalFee = new BigDecimal(calculationAmount(order.getPartsList()));
		totalFee = totalFee.add(new BigDecimal(order.getOtherFee()));
		order.setTotalFee(totalFee.setScale(2, RoundingMode.HALF_UP).doubleValue());
		
		return RestModel.success(order);
	}
	
	@Transactional
	public ResponseEntity<RestModel<String>> payOperation(Integer orderId, Integer payType, double money, String payNo, String token) {
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
			HqlsPurchaseOrder order = purchaseOrderMapper.getOrderById(orderId);
			if (null == order) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "订单不存在");
			}
			// 从财务余额中扣掉支付金额
			HqlsStoreFinance finance = storeFinanceMapper.getStoreFinance(order.getStoreId());
			Double[] returnMoney = calculationBalance(finance, money);
			if (returnMoney == null) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "余额不足！");
			}
			finance.setBalance(returnMoney[0]);
			finance.setCashAble(returnMoney[2]);
			finance.setCashDisable(returnMoney[1]);
			storeFinanceMapper.updateBalance(finance);
			
			List<HqlsOrderDetail> detail = orderDetailMapper.getDetailByOrderId(orderId);
			// 计算优惠
			BigDecimal originPrice = new BigDecimal(0.00);
			for (HqlsOrderDetail d: detail) {
				originPrice = originPrice.add(new BigDecimal(d.getBuyPrice()).multiply(new BigDecimal(d.getBuyCount())));
			}
			Double discountFee = (originPrice.subtract(new BigDecimal(money))).setScale(2, RoundingMode.HALF_UP).doubleValue();
			discountFee = discountFee < 0 ? 0.00 : discountFee;
			// 修改订单
			order.setDiscountFee(discountFee);
			order.setOrderStatus(2);
			order.setPayFee(money);
			order.setPayType(payType);
			order.setPayNo(payNo);
			purchaseOrderMapper.payOperation(order);
			
			// 添加财务流水
			HqlsFinanceFlow flow = new HqlsFinanceFlow();
			flow.setChangeMoney(money);
			flow.setChangeType(3);
			flow.setChargeType(2);
			flow.setIsDelete(0);
			flow.setOperPerson(user.getUserName());
			flow.setOrderNo(order.getOrderNo());
			flow.setTransactionNo(payNo);
			financeFlowMapper.insert(flow);
			
			return RestModel.success("支付成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
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
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "确认收货异常");
	}
	
	/**
	 * 计算门店财务余额
	 * @return
	 */
	public Double[] calculationBalance(HqlsStoreFinance finance, Double payMoney) {
		BigDecimal diffBalance = new BigDecimal(finance.getBalance()).subtract(new BigDecimal(payMoney)).setScale(2, RoundingMode.HALF_UP);
		if (diffBalance.doubleValue() < 0) {
			return null;
		}
		BigDecimal diff = new BigDecimal(finance.getCashDisable()).subtract(new BigDecimal(payMoney)).setScale(2, RoundingMode.HALF_UP);
		Double[] returnMoney = new Double[3];
		// 剩余余额
		returnMoney[0] = diffBalance.doubleValue();
		if (diff.doubleValue() < 0) {
			// 先扣除不可用提现，再扣可用提现
			Double CashAble = (new BigDecimal(finance.getCashAble())
					.subtract(new BigDecimal(payMoney).subtract(new BigDecimal(finance.getCashAble())))).setScale(2, RoundingMode.HALF_UP).doubleValue();
			// 不可用提现用完
			returnMoney[1] = 0.00;
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
			HqlsParts hqpart = partsMapper.getPartsById(p.getPartsId());
			BigDecimal eachOrderPrice = new BigDecimal(hqpart.getCurPrice()).multiply(new BigDecimal(p.getPurchaseNum()));
			result.add(eachOrderPrice);
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
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
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
			KdniaoTrackQueryAPI api = new KdniaoTrackQueryAPI();
			String result = api.getOrderTracesByJson(company.getCompanyNo(), logisticsNo);
			return RestModel.success(result);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "查询物流异常");
	}
}