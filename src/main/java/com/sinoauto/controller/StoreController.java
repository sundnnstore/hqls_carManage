package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.StoreDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.StoreService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "门店")
@RestController
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	@ApiOperation(value="根据当前登陆人查询门店信息",notes="tangrx")
	@PostMapping("findstoreinfo")
	public ResponseEntity<RestModel<List<StoreDto>>> getStoreInfo(@RequestHeader(value = "Authorization") String Authorization){
		
		return storeService.getStoreInfo(Authorization);
	}
	
	
	

}
