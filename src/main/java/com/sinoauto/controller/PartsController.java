package com.sinoauto.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.CommonDto;
import com.sinoauto.entity.RestModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "配件管理")
@RestController
public class PartsController {

	@ApiOperation(value = "按配件类型查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbytype")
	public ResponseEntity<RestModel<List<CommonDto>>> findPartsListByType(@RequestParam("partsType") Integer partsType) {
		
		return null;
	}
	
	@ApiOperation(value = "按配件类型查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbypid")
	public ResponseEntity<RestModel<List<CommonDto>>> findPartsListByPid(@RequestParam("pid") Integer pid) {
		
		return null;
	}
	
}
