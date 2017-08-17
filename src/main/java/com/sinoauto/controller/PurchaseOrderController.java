package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.HqlsShipAddress;
import com.sinoauto.dto.PurchaseOrderParamDto;
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
		
		return purchaseOrderService.addShipAddress(shipAddress);
	}
	
	@ApiOperation(value = "删除收货地址", notes = "wuxiao")
	@DeleteMapping("deleteshipaddress")
	public ResponseEntity<RestModel<String>> deleteShipAddress(@RequestParam(value = "shipAddressId", required = true) Integer shipAddressId) {
		
		return purchaseOrderService.deleteShipAddress(shipAddressId);
	}
	
	@ApiOperation(value = "批量删除收货地址", notes = "wuxiao")
	@DeleteMapping("batchdeleteshipaddress")
	public ResponseEntity<RestModel<String>> batchDeleteShipAddress(@RequestBody List<Integer> shipAddressIds) {
		
		return purchaseOrderService.batchDeleteShipAddress(shipAddressIds);
	}
	
	@ApiOperation(value = "查询所有收货地址", notes = "wuxiao")
	@GetMapping("findallshipaddress")
	public ResponseEntity<RestModel<List<HqlsShipAddress>>> findAllShipAddress() {
		
		return purchaseOrderService.findAll();
	}
	
	@ApiOperation(value = "生成结算订单", notes = "wuxiao")
	@PostMapping("generatorpurchaseorder")
	public ResponseEntity<RestModel<String>> generatorPurchaseOrder(@RequestBody PurchaseOrderParamDto orderParamDto) {
		if (orderParamDto.getPartsList().isEmpty()) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.PARAM_NOT_NULL, "请先选择商品");
		}
		return purchaseOrderService.generatorPurchaseOrder(orderParamDto);
	}
	
	@ApiOperation(value = "查看购物车信息", notes = "wuxiao")
	@PostMapping("checkshopcart")
	public ResponseEntity<RestModel<ShopCartInfoDto>> checkShopCart(@RequestBody List<ShopCartParamDto> param) {
		
		return purchaseOrderService.checkShopCart(param);
	}
	
}