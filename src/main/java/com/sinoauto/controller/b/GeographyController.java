package com.sinoauto.controller.b;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.HqlsGeography;
import com.sinoauto.dto.CountyDto;
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
			@RequestParam(value = "lng", required = true) String lng,@RequestParam(value = "distance", required = true) Integer distance) {
		return geographyService.findCustomers(lat, lng,distance);
	} 
	
	@ApiOperation(value = "根据定位的城市名称获取区县信息", notes = "tangwt")
	@GetMapping("findcountys")
	public ResponseEntity<RestModel<List<CountyDto>>> findCountys(@RequestParam(value = "cityName", required = true) String cityName,@RequestParam(value = "county", required = true) Integer county) {
		return geographyService.findCountys(cityName,county);
	} 
}
