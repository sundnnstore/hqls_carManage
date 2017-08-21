package com.sinoauto.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.StoreDto;
import com.sinoauto.dto.StoreInfoDto;
import com.sinoauto.dto.StoreTreeDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.StoreService;
import com.sinoauto.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "门店管理")
@RestController
public class StoreController {
	@Autowired
	private StoreService storeService;
	@Autowired
	private UserService userService;
	
	
	@ApiOperation(value="根据当前登陆人查询门店信息/切换门店账号",notes="tangrx")
	@GetMapping("findstorebyuserid")
	public ResponseEntity<RestModel<List<StoreDto>>> findStoreInfo(@RequestHeader(value = "Authorization") String Authorization){
		return storeService.findStoreInfo(Authorization);
	}
	
	
	@ApiOperation(value = "修改门店名称",notes = "tangrx")
	@PostMapping("changestorename")
	public ResponseEntity<RestModel<String>> changeStoreName(@RequestParam(value = "storeName") String storeName,@RequestParam(value="storeId") Integer storeId){
		return storeService.changeStoreName(storeName,storeId);
		
	}
	
	@ApiOperation(value = "修改门店联系方式",notes = "tangrx")
	@PostMapping("changestoremobile")
	public ResponseEntity<RestModel<String>> changeStoreMobile(@RequestParam(value = "mobile") String mobile,@RequestParam(value="storeId") Integer storeId){
		return storeService.changeStoreMobile(mobile,storeId);
		
	}
	@ApiOperation(value ="修改门店背景",notes ="tangrx")
	@PostMapping("changeurl")
	public ResponseEntity<RestModel<String>> changeStoreUrl(@RequestParam(value = "backUrl") String backUrl,@RequestParam(value="storeId") Integer storeId){
			return storeService.changeStoreUrl(backUrl,storeId);
	}
	
	
	@ApiOperation(value = "根据门店名称/联系人/联系人电话/地址 分页查询门店信息",notes = "tangrx")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeName", value = "门店名称", required = false, dataType = "String"),
						 @ApiImplicitParam(paramType = "query", name = "userName", value = "联系人", required = false, dataType = "String"),
						 @ApiImplicitParam(paramType = "query", name = "mobile", value = "联系人号码", required = false, dataType = "String"),
						 @ApiImplicitParam(paramType = "query", name = "address", value = "地址", required = false, dataType = "String"),
						 @ApiImplicitParam(paramType = "query", name = "provinceId", value = "省份ID", required = false, dataType = "int"),
						 @ApiImplicitParam(paramType = "query", name = "cityId", value = "市ID", required = false, dataType = "int"),
						 @ApiImplicitParam(paramType = "query", name = "countyId", value = "区县ID", required = false, dataType = "int"),
						 @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页数", required = true, dataType = "int"),
						 @ApiImplicitParam(paramType = "query", name = "pageSize", value = "个数", required = true, dataType = "int")})
	@GetMapping("findstoreinfo")
	public ResponseEntity<RestModel<List<StoreInfoDto>>> findStore(String storeName,String userName,String mobile,String address,Integer provinceId,Integer cityId,Integer countyId,Integer pageIndex,Integer pageSize){
		return storeService.findStore(storeName,userName,mobile,address,provinceId,cityId,countyId,pageIndex,pageSize);
		
	}

	
	@ApiOperation(value = "门店禁用与启用",notes ="tangrx")
	@PostMapping("storeisuseable")
	public ResponseEntity<RestModel<String>> changeIsUseable(@RequestParam(value="storeId") Integer storeId){
		return storeService.changeIsUseable(storeId);
		
	}
	
	@ApiOperation(value = "修改门店地址",notes = "tangrx")
	@PostMapping("changestoreaddress")
	public ResponseEntity<RestModel<String>> changeStoreAddress(@RequestParam(value="provinceId") Integer provinceId,@RequestParam(value="cityId") Integer cityId,
																 @RequestParam(value="countyId") Integer countyId,@RequestParam(value="address") String address,
																 @RequestParam(value="storeId") Integer storeId){
		return storeService.changeStoreAddress(provinceId,cityId,countyId,address,storeId);
	}
	
	
	@ApiOperation(value = "查询级别门店",notes = "tangrx")
	@PostMapping("findstore")
	public ResponseEntity<RestModel<List<StoreTreeDto>>> findStore(){
			int storeId =1;
			List<StoreTreeDto> result = new ArrayList<>();
			result.add(storeService.findStoreIsUseable(storeId));
			return RestModel.success(result);
		
	}
	
	@ApiOperation(value = "新增门店信息",notes = "tangrx")
	@PostMapping("insertstore")
	public ResponseEntity<RestModel<Integer>> insertStore(@RequestHeader(value = "Authorization") String Authorization,@RequestBody StoreInfoDto storeInfoDto
														  ){
		return storeService.insertStore(Authorization,storeInfoDto);
		
	}
	
	@ApiOperation(value = "修改账号",notes = "tangrx")
	@PostMapping("changeaccount")
	public ResponseEntity<RestModel<String>> changeAccount(@RequestHeader(value = "Authorization") String Authorization,
															@RequestParam(value="newMobile") String newMobile){
		return userService.updateUserAccount(Authorization, newMobile);
		
	}
	
	@ApiOperation(value = "查询所有门店",notes = "tangrx")
	@GetMapping("findallstore")
	public ResponseEntity<RestModel<List<CommonDto>>> findAllStore(){
		return storeService.findAllStore();
	}
	
	@ApiOperation(value = "根据storedId查询当前门店信息",notes = "tangrx")
	@GetMapping("getstorebystoreid")
	public ResponseEntity<RestModel<StoreInfoDto>> getStoreByStoreId(@RequestParam(value="storeId") Integer storeId){
		return storeService.getStoreByStoreId(storeId);
		
	}
	
	@ApiOperation(value = "根据storeId编辑当条信息",notes = "tangrx")
	@PostMapping("changestorebystoreid")
	public ResponseEntity<RestModel<Integer>> changeStoreByStoreId(@RequestHeader(value = "Authorization") String Authorization,@RequestBody StoreInfoDto storeInfoDto){
		return storeService.changeStoreByStoreId(Authorization,storeInfoDto);
	}
	
	
	
}
