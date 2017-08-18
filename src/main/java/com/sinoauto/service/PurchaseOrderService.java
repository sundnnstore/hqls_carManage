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
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsOrderDetail;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPurchaseOrder;
import com.sinoauto.dao.bean.HqlsShipAddress;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.OrderDetailMapper;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dao.mapper.ShipAddressMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PurchaseOrderDto;
import com.sinoauto.dto.PurchaseOrderParamDto;
import com.sinoauto.dto.ShopCartInfoDto;
import com.sinoauto.dto.ShopCartParamDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;


@Service
public class PurchaseOrderService {

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
	public ResponseEntity<RestModel<String>> batchDeleteShipAddress(List<Integer> shipAddressIds) {
		try {
			Integer[] addressIds = (Integer[]) shipAddressIds.toArray();
			shipAddressMapper.batchDelete(addressIds);
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
			List<HqlsOrderDetail> detail = orderDetailMapper.getDetailByOrderId(orderId);
			// 计算优惠
			BigDecimal originPrice = new BigDecimal(0.00);
			for (HqlsOrderDetail d: detail) {
				originPrice = originPrice.add(new BigDecimal(d.getBuyPrice()).multiply(new BigDecimal(d.getBuyCount())));
			}
			Double discountFee = (originPrice.divide(new BigDecimal(money))).setScale(2, RoundingMode.HALF_UP).doubleValue();
			discountFee = discountFee < 0 ? 0.00 : discountFee;
			// 修改订单
			order.setDiscountFee(discountFee);
			order.setOrderStatus(2);
			order.setPayFee(money);
			order.setPayType(payType);
			order.setPayNo(payNo);
			purchaseOrderMapper.payOperation(order);
			
			//添加财务流水
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
	
	
	/**
	 * 计算结算金额
	 * @return
	 */
	public Double calculationAmount(List<PartsDesListDto> parts) {
		
		if (parts.isEmpty()) return 0.00;
		BigDecimal result = new BigDecimal(0.00);
		for (PartsDesListDto p: parts) {
			HqlsParts hqpart = partsMapper.getPartsById(p.getPartsId());
			BigDecimal eachOrderPrice = new BigDecimal(hqpart.getCurPrice()).multiply(new BigDecimal(p.getPartsName()));
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
	public ResponseEntity<RestModel<Page<PurchaseOrderDto>>> findPurchaseOrderByContidion(PurchaseOrderDto purchaseOrderDto,Integer pageIndex,Integer pageSize){
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
}