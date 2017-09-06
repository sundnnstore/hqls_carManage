package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.HqlsGeography;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.GeographyService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags="地理位置")
@RestController
public class GeographyController {
	
	@Autowired
	private GeographyService geographyService;

	
	@ApiOperation(value = "按照当前经纬度获取1公里内的客户", notes = "tangwt")
	@PostMapping("findcustomers")
	public ResponseEntity<RestModel<List<HqlsGeography>>> findCustomers(@RequestParam(value = "lat", required = true) String lat,
			@RequestParam(value = "lng", required = true) String lng) {
		return geographyService.findCustomers(lat, lng);
	} 
}
