package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsShipAddress;
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
import com.sinoauto.service.PurchaseOrderService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "采购订单管理")
@RestController
public class PurchaseOrderController {

	@Autowired
	private PurchaseOrderService purchaseOrderService;
	
	@ApiOperation(value = "新增收货地址", notes = "wuxiao")
	@PostMapping("addshipaddress")
	public ResponseEntity<RestModel<String>> addShipAddress(@RequestBody HqlsShipAddress shipAddress) {
		
		if (null == shipAddress.getStoreId()) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "所属门店Id不能为空");
		}
		if (StringUtils.isEmpty(shipAddress.getRecipient())) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "收货人不能为空");
		}
		if (StringUtils.isEmpty(shipAddress.getMobile())) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "收货人电话不能为空");
		}
		if (null == shipAddress.getProvinceId() || null == shipAddress.getCityId() || null == shipAddress.getCountyId()) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "省市区不能为空");
		}
		
		return purchaseOrderService.addShipAddress(shipAddress);
	}
	
	@ApiOperation(value = "修改收货地址", notes = "wuxiao")
	@PostMapping("updateshipaddress")
	public ResponseEntity<RestModel<String>> updateShipAddress(@RequestBody HqlsShipAddress shipAddress) {
		
		if (StringUtils.isEmpty(shipAddress.getRecipient())) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "收货人不能为空");
		}
		if (StringUtils.isEmpty(shipAddress.getMobile())) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "收货人电话不能为空");
		}
		if (null == shipAddress.getProvinceId() || null == shipAddress.getCityId() || null == shipAddress.getCountyId()) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "省市区不能为空");
		}
		
		return purchaseOrderService.updateShipAddress(shipAddress);
	}
	
	@ApiOperation(value = "删除收货地址", notes = "wuxiao")
	@DeleteMapping("deleteshipaddress")
	public ResponseEntity<RestModel<String>> deleteShipAddress(@RequestParam(value = "shipAddressId", required = true) Integer shipAddressId) {
		
		return purchaseOrderService.deleteShipAddress(shipAddressId);
	}
	
	@ApiOperation(value = "批量删除收货地址", notes = "wuxiao")
	@DeleteMapping("batchdeleteshipaddress")
	public ResponseEntity<RestModel<String>> batchDeleteShipAddress(@RequestBody Integer[] shipAddressIds) {
		
		return purchaseOrderService.batchDeleteShipAddress(shipAddressIds);
	}
	
	@ApiOperation(value = "根据门店查询所有收货地址", notes = "wuxiao")
	@GetMapping("findallshipaddress")
	public ResponseEntity<RestModel<List<HqlsShipAddress>>> findAllShipAddress(@RequestParam(value = "storeId", required = false) Integer storeId) {
		
		return purchaseOrderService.findAll(storeId);
	}
	
	@ApiOperation(value = "根据Id查询收货地址", notes = "wuxiao")
	@GetMapping("getaddressbyid")
	public ResponseEntity<RestModel<HqlsShipAddress>> getAddressById(@RequestParam(value = "addressId", required = true) Integer addressId) {
		
		return purchaseOrderService.getAddressById(addressId);
	}
	
	@ApiOperation(value = "点击结算进入待支付页", notes = "wuxiao")
	@PostMapping("settlementoperation")
	public ResponseEntity<RestModel<ShopCartInfoDto>> settlementOperation(@RequestBody SettlementOperationParamDto settlementParam) {
		
		return purchaseOrderService.settlementOperation(settlementParam);
	}
	
	@ApiOperation(value = "查看购物车信息", notes = "wuxiao")
	@PostMapping("checkshopcart")
	public ResponseEntity<RestModel<ShopCartInfoDto>> checkShopCart(@RequestBody List<ShopCartParamDto> param) {
		
		return purchaseOrderService.checkShopCart(param);
	}
	
	@ApiOperation(value = "按订单状态查询", notes = "wuxiao")
	@GetMapping("findorderbystoreidandstatus")
	public ResponseEntity<RestModel<Page<PurchaseOrderParamDto>>> findOrderByStoreIdAndStatus(
			@RequestParam(value = "storeId", required = true) Integer storeId,
			@RequestParam(value = "orderStatus", required = false) Integer orderStatus,
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		return purchaseOrderService.findOrderByStatus(storeId, orderStatus,pageIndex,pageSize);
	}
	
	@ApiOperation(value = "按订单Id查询", notes = "wuxiao")
	@GetMapping("findorderbyorderid")
	public ResponseEntity<RestModel<ShopCartInfoDto>> findOrderByOrderId(
			@RequestParam(value = "orderId", required = true) Integer orderId) {
		
		return purchaseOrderService.getOrderByOrderId(orderId);
	}
	
	@ApiOperation(value = "支付操作", notes = "wuxiao")
	@PostMapping("payoperation")
	public ResponseEntity<RestModel<PayReturnParamDto>> payOperation(@RequestBody SettlementOperationParamDto param, @RequestHeader(value="Authorization") String Authorization) {
		
		return purchaseOrderService.payOperation(param, Authorization);
	}
	
	@ApiOperation(value = "查询门店余额", notes = "wuxiao")
	@GetMapping("checkbalance")
	public ResponseEntity<RestModel<Double>> checkBalance(@RequestParam(value = "storeId", required = true) Integer storeId) {
		
		return purchaseOrderService.queryBalance(storeId);
	}
	
	@ApiOperation(value = "确认收货", notes = "wuxiao")
	@GetMapping("confirmReceipt")
	public ResponseEntity<RestModel<String>> confirmReceipt(@RequestParam(value = "orderId", required = true) Integer orderId) {
		
		return purchaseOrderService.confirmReceipt(orderId);
	}
	
	@ApiOperation(value = "查找订单", notes = "liud")
	@GetMapping("findpurchorder")
	public ResponseEntity<RestModel<Page<PurchaseOrderDto>>> findPurchaseOrderByContidion(@ModelAttribute PurchaseOrderQueryDto purchaseOrderDto,@RequestParam("pageIndex")Integer pageIndex,@RequestParam("pageSize")Integer pageSize){
		return purchaseOrderService.findPurchaseOrderByContidion(purchaseOrderDto, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "查找所有物流公司", notes = "wuxiao")
	@GetMapping("findalllogisticscompany")
	public ResponseEntity<RestModel<List<CommonDto>>> findAllLogisticsCompany() {
		return purchaseOrderService.findAllLogisticsCompany();
	}
	
	@ApiOperation(value = "发货操作", notes = "wuxiao")
	@GetMapping("shipoperation")
	public ResponseEntity<RestModel<String>> shipOperation(
			@RequestParam(value = "orderId", required = true) Integer orderId,
			@RequestParam(value = "logisticsId", required = false) Integer logisticsId,
			@RequestParam(value = "logisticsNo", required = false) String logisticsNo,
			@RequestParam(value = "remark", required = false) String remark) {
		return purchaseOrderService.shipOperation(orderId, logisticsId, logisticsNo, remark);
	}
	
	@ApiOperation(value = "查看物流信息", notes = "wuxiao")
	@GetMapping("getlogisticsinfo")
	public ResponseEntity<RestModel<Object>> getLogisticsInfo(@RequestParam(value = "orderId", required = true) Integer orderId) {
		return purchaseOrderService.getLogisticsInfo(orderId);
	}
	
	@ApiOperation(value = "条件查询订单", notes = "liud")
	@GetMapping("findorderlistbycondition")
	public ResponseEntity<RestModel<List<PurchaseOrderDto>>> findOrderListByContidion(
			@RequestParam(value = "orderStatus", required = false) Integer orderStatus,
			@RequestParam(value = "storeId", required = false) Integer storeId,
			@RequestParam(value = "userName", required = false) String userName,
			@RequestParam(value = "mobile", required = false) String mobile,
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize){
		return purchaseOrderService.findOrderListByContidion(orderStatus, storeId, userName, mobile, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "查看订单详情", notes = "wuxiao")
	@GetMapping("getpartsbyorderid")
	public ResponseEntity<RestModel<List<PartsDesListDto>>> getPartsByOrderId(@RequestParam(value = "orderId", required = true) Integer orderId) {
		return purchaseOrderService.getPartsByOrderId(orderId);
	}
	
	@ApiOperation(value = "取消订单", notes = "wuxiao")
	@GetMapping("cancelorder")
	public ResponseEntity<RestModel<String>> cancelOrder(@RequestParam(value = "orderId", required = true) Integer orderId,
			 @RequestHeader(value="Authorization") String Authorization) {
		 return purchaseOrderService.cancelOrder(orderId, Authorization);
	}
	
	@ApiOperation(value = "添加物流备注", notes = "wuxiao")
	@GetMapping("addlogisticsremark")
	public ResponseEntity<RestModel<String>> addLogisticsRemark(@RequestParam(value = "orderId", required = true) Integer orderId,
			@RequestParam(value = "remark", required = true) String remark) {
		return purchaseOrderService.addLogisticsRemark(orderId, remark);
	}

}
