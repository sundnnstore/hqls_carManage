package com.sinoauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "门店")
@RestController
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	@ApiOperation(value="根据账号密码查询门店信息",notes="tangrx")
	@PostMapping("findstoreinfo")
	public String findStoreInfo(){
		return null;
	}
	
	
	
	
	

}
