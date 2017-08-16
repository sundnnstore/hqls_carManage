package com.sinoauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.service.StoreService;

import io.swagger.annotations.Api;

@Api(tags = "门店")
@RestController
public class StoreController {
	
	@Autowired
	private StoreService storeService;
	
	
	

}
