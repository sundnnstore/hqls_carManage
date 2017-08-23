package com.sinoauto.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPartsBrand;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.dto.PartsQueryDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.PartsBrandService;
import com.sinoauto.service.PartsService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "配件管理")
@RestController
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@Autowired
	private PartsBrandService partsBrandService;
	
	@ApiOperation(value = "按配件类型查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbytype")
	public ResponseEntity<RestModel<List<CommonDto>>> findPartsListByType(
			@RequestParam(value = "partsType", required = true) Integer partsType,
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		
		return partsService.findListByType(partsType, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "查询所有配件列表", notes = "wuxiao")
	@GetMapping(value = "findallparts")
	public ResponseEntity<RestModel<List<CommonDto>>> findAllParts(
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		
		return partsService.findAllParts(pageIndex, pageSize);
	}
	
	@ApiOperation(value = "按配件Id查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbypid")
	public ResponseEntity<RestModel<Object>> findPartsListByPid(
			@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId,
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		
		return partsService.findListByPid(partsTypeId, pageIndex, pageSize);
	}
	
	/**
	 * 	按条件查询配件信息
	 * 	@User liud
	 * 	@Date 2017年8月17日下午3:33:14
	 * 	@param partsDto
	 * 	@return
	 */
	@ApiOperation(value="按条件查询配件管理",notes="liud")
	@GetMapping(value="findparts")
	public ResponseEntity<RestModel<Page<PartsDto>>> findPartsByCondition(@ModelAttribute PartsQueryDto partsDto,@RequestParam("pageIndex")Integer pageIndex,@RequestParam("pageSize")Integer pageSize){
		return partsService.findPartsByCondition(partsDto,pageIndex,pageSize);
	}
	
	@ApiOperation(value = "查询配件详情", notes = "wuxiao")
	@GetMapping(value = "getpartsdetail")
	public ResponseEntity<RestModel<PartsDetailDto>> getPartsDetail(@RequestParam(value = "partsId", required = true) Integer partsId) {
		
		return partsService.getPartsDetail(partsId);
	}
	
	@ApiOperation(value = "查找订单对应的配件", notes = "liud")
	@GetMapping("findpartsbypurchorderid")
	public ResponseEntity<RestModel<List<HqlsParts>>> findPartsByPurchOrderId(@RequestParam("purchOrderId")Integer purchOrderId){
		return partsService.findPartsByPurchOrderId(purchOrderId);
	}
	
	@ApiOperation(value = "新增配件", notes = "liud")
	@PostMapping("addparts")
	public ResponseEntity<RestModel<Integer>> addParts(@RequestBody PartsOperDto partsOperDto){
		return partsService.addParts(partsOperDto);
	}
	
	@ApiOperation(value = "修改配件", notes = "liud")
	@PostMapping("updateparts")
	public ResponseEntity<RestModel<Integer>> updateParts(@RequestBody PartsOperDto partsOperDto){
		return partsService.updateParts(partsOperDto);
	}
	
	@ApiOperation(value = "查询配件所有品牌", notes = "liud")
	@GetMapping("findpartsbrands")
	public ResponseEntity<RestModel<List<HqlsPartsBrand>>> findPartsBrands(){
		return partsBrandService.findPartsBrands();
	}
}
