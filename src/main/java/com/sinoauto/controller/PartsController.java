package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.PartsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "配件管理")
@RestController
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@ApiOperation(value = "按配件类型查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbytype")
	public ResponseEntity<RestModel<List<CommonDto>>> findPartsListByType(@RequestParam(value = "partsType", required = true) Integer partsType) {
		
		return partsService.findListByType(partsType);
	}
	
	@ApiOperation(value = "按配件类型查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbypid")
	public ResponseEntity<RestModel<Object>> findPartsListByPid(@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId) {
		
		return partsService.findListByPid(partsTypeId);
	}
	
	public ResponseEntity<RestModel<List<CommonDto>>> abc(){
		String a="b",
				b="ccc";
		System.out.println(a+" "+b);
		return null;
	}
	
	@ApiOperation(value = "查询配件详情", notes = "wuxiao")
	@GetMapping(value = "getpartsdetail")
	public ResponseEntity<RestModel<PartsDetailDto>> getPartsDetail(@RequestParam(value = "partsId", required = true) Integer partsId) {
		
		return partsService.getPartsDetail(partsId);
	}
	
}
