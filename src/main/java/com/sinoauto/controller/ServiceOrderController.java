package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.sinoauto.dao.bean.HqlsServiceType;
import com.sinoauto.dto.ServiceOrderDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.ServiceOrderService;
import com.sinoauto.service.ServiceTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "服务订单模块")
@RestController
public class ServiceOrderController {
	
	@Autowired
	private ServiceTypeService serviceTypeService;
	@Autowired
	private ServiceOrderService serviceOrderService;

	@ApiOperation(value = "按服务项目名称查找服务项目集合", notes = "tangwt")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "typeName", value = "服务项目名称", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", required = true, dataType = "int") })
	@GetMapping(value = "findservicetypesbytypename")
	public ResponseEntity<RestModel<List<HqlsServiceType>>> findServiceTypesByTypeName(String typeName, Integer pageIndex, Integer pageSize) {
		return serviceTypeService.findServiceTypesByTypeName(typeName, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "添加服务项目", notes = "tangwt")
	@PostMapping("addservicetype")
	public ResponseEntity<RestModel<String>> addServiceType(@RequestBody HqlsServiceType serviceType){
		if(StringUtils.isEmpty(serviceType.getServiceTypeName())){
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"项目名不能为空");
		}
		if(StringUtils.isEmpty(serviceType.getServiceTypeContent())){
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"项目内容不能为空");
		}
		return serviceTypeService.addServiceType(serviceType);
	}
	
	@ApiOperation(value = "添加服务编辑", notes = "tangwt")
	@PostMapping("updateservicetype")
	public ResponseEntity<RestModel<String>> updateServiceType(@RequestBody HqlsServiceType serviceType){
		if(serviceType.getServiceTypeId() == null){
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"项目ID不能为空");
		}
		if(StringUtils.isEmpty(serviceType.getServiceTypeName())){
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"项目名不能为空");
		}
		if(StringUtils.isEmpty(serviceType.getServiceTypeContent())){
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"项目内容不能为空");
		}
		return serviceTypeService.addServiceType(serviceType);
	}
	
	@ApiOperation(value = "修改服务项目状态", notes = "tangwt")
	@PostMapping("updateservicetypestatus")
	public ResponseEntity<RestModel<String>> updateServiceTypeStatus(@RequestParam(value="serviceTypeId",required=true)Integer serviceTypeId){
		if(null == serviceTypeId){
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(),"项目ID为空");
		}
		return serviceTypeService.updateServiceTypeStatus(serviceTypeId);
	}
	
	@ApiOperation(value = "按订单状态查找服务订单集合", notes = "tangwt")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "orderStatus", value = "服务订单状态", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", required = true, dataType = "int") })
	@GetMapping(value = "findordersbystatus")
	public ResponseEntity<RestModel<List<ServiceOrderDto>>> findServiceOrdersByOrderStatus(Integer orderStatus,Integer storeId, Integer pageIndex, Integer pageSize) {
		return serviceOrderService.findServiceOrdersByOrderStatus(orderStatus,storeId, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "服务订单完成接口", notes = "tangwt")
	@PostMapping("finishorder")
	public ResponseEntity<RestModel<String>> finishOrder(@RequestHeader String Authorization,@RequestParam("serviceOrderId")Integer serviceOrderId){
		return serviceOrderService.finishOrder(Authorization,serviceOrderId);
	}

}
