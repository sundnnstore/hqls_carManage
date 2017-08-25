package com.sinoauto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.dto.PartsQueryDto;
import com.sinoauto.dto.PartsTreeRecursionDto;
import com.sinoauto.dto.PartsTypeDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.PartsBrandService;
import com.sinoauto.service.PartsService;
import com.sinoauto.service.PartsTypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "配件管理")
@RestController
public class PartsController {

	@Autowired
	private PartsService partsService;
	
	@Autowired
	private PartsBrandService partsBrandService;
	
	@Autowired
	private PartsTypeService partsTypeService;
	
	
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
	public ResponseEntity<RestModel<Map<String, Object>>> findAllParts(
			@RequestParam(value = "pageIndex", required = true) Integer pageIndex,
			@RequestParam(value = "pageSize", required = true) Integer pageSize) {
		
		return partsService.findAllParts(pageIndex, pageSize);
	}
	
	@ApiOperation(value = "按配件Id查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbypid")
	public ResponseEntity<RestModel<Object>> findPartsListByPid(
			@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		
		return partsService.findListByPid(partsTypeId, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "按配件名称、规格或型号查询配件", notes = "wuxiao")
	@GetMapping(value = "getpartsbycondition")
	public ResponseEntity<RestModel<List<PartsDesListDto>>> getPartsByCondition(
			@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId,
			@RequestParam(value = "condition", required = false) String condition) {
		
		return partsService.findPartsByConditon(partsTypeId, condition);
	}
	
	@ApiOperation(value = "按配件Id查询配件是否有子类列表", notes = "wuxiao")
	@GetMapping(value = "haschildtype")
	public ResponseEntity<RestModel<Boolean>> hasChildType(@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId) {
		return partsService.hasChildType(partsTypeId);
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
	
	@ApiOperation(value = "查询配件类型集合", notes = "liud")
	@GetMapping("findpartstype")
	public List<PartsTypeDto> partsTypes(){
		return partsTypeService.partsTypes();
	}
	
	@ApiOperation(value = "查询配件树", notes = "liud")
	@GetMapping("findpartstree")
	public List<PartsTreeRecursionDto> partsTreeRecursion(@RequestParam("pid") Integer pid){
		List<PartsTreeRecursionDto> trees = new ArrayList<>();
		PartsTreeRecursionDto partsTree =  partsService.partsTreeRecursion(pid);
		if(partsTree!=null){trees.add(partsTree);};
		return trees;
	}
	
	@ApiOperation(value = "查询配件树", notes = "liud")
	@GetMapping("findpartslevel")
	public PartsTreeRecursionDto partsTreelevel(@RequestParam("pid") Integer pid){
		return  partsService.partsTreeRecursion(pid);
	}
	
	@ApiOperation(value = "根据配件等级查询配件id", notes = "liud")
	@GetMapping("findpartlevel")
	public PartsTreeRecursionDto findPartsByLevel(@RequestParam("onelevel") Integer onelevel ,@RequestParam("twolevel") Integer twolevel,@RequestParam("threelevel") Integer threelevel  ){
		return null;
	}
}
