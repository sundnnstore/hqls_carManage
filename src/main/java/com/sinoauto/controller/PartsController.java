package com.sinoauto.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPartsBrand;
import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsLevelDto;
import com.sinoauto.dto.PartsModelDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.dto.PartsQueryDto;
import com.sinoauto.dto.PartsTreeRecursionDto;
import com.sinoauto.dto.PartsTypeAndPartsListDto;
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
	
	@ApiOperation(value = "查询所有配件类型列表", notes = "wuxiao")
	@GetMapping(value = "findallparts")
	public ResponseEntity<RestModel<Map<String, Object>>> findAllParts(
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		
		return partsService.findAllParts(pageIndex, pageSize);
	}
	
	@ApiOperation(value = "按配件类别Id查询配件列表", notes = "wuxiao")
	@GetMapping(value = "findpartsbypid")
	public ResponseEntity<RestModel<Object>> findPartsListByPid(
			@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId,
			@RequestParam(value = "modelId", required = false) Integer modelId,
			@RequestParam(value = "pageIndex", required = false) Integer pageIndex,
			@RequestParam(value = "pageSize", required = false) Integer pageSize) {
		
		return partsService.findListByPid(partsTypeId, modelId, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "按配件名称、规格或型号查询配件", notes = "wuxiao")
	@GetMapping(value = "getpartsbycondition")
	public ResponseEntity<RestModel<List<PartsDesListDto>>> getPartsByCondition(
			@RequestParam(value = "partsTypeId", required = true) Integer partsTypeId,
			@RequestParam(value = "condition", required = false) String condition) {
		
		return partsService.findPartsByConditon(partsTypeId, condition);
	}
	
	@Deprecated
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
	
	@ApiOperation(value = "商品管理新增查看", notes = "liud")
	@GetMapping("partstreeofadded")
	public List<PartsTreeRecursionDto> partsTreeOfAdd(@RequestParam("pid") Integer pid,@RequestParam("operflag") Integer operflag){
		List<PartsTreeRecursionDto> trees = new ArrayList<>();
//		Integer topId = partsService.findTopId(pid);
//		if(topId==null) topId=0;
		PartsTreeRecursionDto partsTree =  partsService.partsTreeRecursion(pid,operflag);
		if(partsTree!=null){trees.add(partsTree);};
		return trees;
	}
	
	
	@ApiOperation(value = "编辑、查看加载的节点树", notes = "liud")
	@GetMapping("partstreeofaddeditview")
	public List<PartsTreeRecursionDto> partsTreeOfAddEditView(@RequestParam("pid") Integer pid,@RequestParam("operflag") Integer operflag){
		List<PartsTreeRecursionDto> trees = new ArrayList<>();
		/**
		 * 反查第一级
		 */
		Integer topId = partsService.findTopId(pid);
		if(topId==null) topId=0;
		PartsTreeRecursionDto partsTree =  partsService.partsTreeForEditAndView(topId,operflag);
		if(partsTree!=null){trees.add(partsTree);};
		return trees;
	}
	
	
	/**
	 * 
	 * 	@User liud
	 * 	@Date 2017年8月25日上午11:11:39
	 * 	@param pid 父级ID
	 * 	@param operflag 1:新增  2.编辑 3.查看 
	 * 	@return
	 */
	@ApiOperation(value = "查询配件树", notes = "liud")
	@GetMapping("findpartslevel")
	public PartsTreeRecursionDto partsTreelevel(@RequestParam("pid") Integer pid,@RequestParam("operflag") Integer operflag){
		return  partsService.partsTreeRecursion(pid,operflag);
	}
	
	@ApiOperation(value = "查询配件相应等级集合", notes = "liud")
	@GetMapping("levelinfo")
	public ResponseEntity<RestModel<List<List<PartsLevelDto>>>> findPartsByLevel(
							@RequestParam("partsTypeId") Integer partsTypeId,
							@RequestParam("selecDepth") Integer selecDepth,
							@RequestParam("pageIndex") Integer pageIndex,
							@RequestParam("pageSize") Integer pageSize){
		return partsService.findPartsByLevel(partsTypeId,selecDepth,pageIndex,pageSize);
	}
	
	@ApiOperation(value = "新增配件子集", notes = "liud")
	@PostMapping("addpartstype")
	public ResponseEntity<RestModel<Integer>> insert(@RequestBody HqlsPartsType hqlsPartsType){
		return partsTypeService.insert(hqlsPartsType);
	}
	
	@ApiOperation(value = "新增同级配件", notes = "liud")
	@PostMapping("addsamelevelpartstype")
	public ResponseEntity<RestModel<Integer>> addSameLevel(@RequestBody HqlsPartsType hqlsPartsType){
		return partsTypeService.addSameLevel(hqlsPartsType);
	}
	
	@ApiOperation(value = "检查是否可以删除", notes = "liud")
	@GetMapping("checkdel")
	public ResponseEntity<RestModel<CommonDto>> checkIsCanbeDel(@RequestParam("partTypeId") Integer partsTypeId){
		return partsTypeService.checkIsCanbeDel(partsTypeId);
	}
	
	@ApiOperation(value = "检查新增的名称是否存在", notes = "liud")
	@GetMapping("checkadd")
	public ResponseEntity<RestModel<Boolean>> checkIsCanbeAdd(@RequestParam("typeName") String typeName){
		return partsTypeService.checkIsCanbeAdd(typeName);
	}
	
	@ApiOperation(value = "删除配件类型", notes = "liud")
	@DeleteMapping("deletepartstype")
	public ResponseEntity<RestModel<Boolean>> deletePartsType(@RequestParam("partTypeId") Integer partTypeId){
		return partsTypeService.delete(partTypeId);
	}
	
	/**
	 *  根据配件类型id查询配件类型对象
	 * 	@User liud
	 * 	@Date 2017年9月4日下午12:54:56
	 * 	@param partTypeId
	 * 	@return
	 */
	@ApiOperation(value = "根据配件类型id查询配件类型对象", notes = "liud")
	@GetMapping("findparttypeobj")
	public ResponseEntity<RestModel<HqlsPartsType>> getHqlsPartsTypeById(@RequestParam("partTypeId") Integer partTypeId){
		return partsTypeService.findHqlsPartsTypeById(partTypeId);
	}
	
	/**
	 *  修改配件类型对象
	 * 	@User liud
	 * 	@Date 2017年9月4日下午1:09:37
	 * 	@param pt
	 * 	@return
	 */
	@ApiOperation(value = "修改", notes = "liud")
	@PostMapping("update")
	public ResponseEntity<RestModel<HqlsPartsType>> update(@RequestBody HqlsPartsType pt){
		return partsTypeService.update(pt);
	}
	
	/**
	 * 查询所有车辆品牌
	 * @return
	 * @author wuxiao
	 */
	@ApiOperation(value = "查询所有车辆品牌/按品牌名称查询", notes = "wux")
	@GetMapping("allbrands")
	public ResponseEntity<RestModel<Map<String, List<CommonDto>>>> allBrands(@RequestParam(value="brandName", required=false) String brandName) {
		return partsService.findAllBrands(brandName);
	}
	
	/**
	 * 根据品牌Id查询车系
	 * @return
	 * @author wuxiao
	 */
	@ApiOperation(value = "根据品牌Id查询车系", notes = "wux")
	@GetMapping("findseriesbybrandid")
	public ResponseEntity<RestModel<List<CommonDto>>> findSeriesByBrandId(@RequestParam(value="brandId", required=true) Integer brandId) {
		return partsService.findSeriesByBrandId(brandId);
	}
	
	/**
	 * 根据车系Id查询车型
	 * @return
	 * @author wuxiao
	 */
	@ApiOperation(value = "根据车系Id查询车型", notes = "wux")
	@GetMapping("findmodelsbyseriesid")
	public ResponseEntity<RestModel<List<CommonDto>>> findModelsBySeriesId(@RequestParam(value="seriesId", required=true) Integer seriesId) {
		return partsService.findModelsBySeriesId(seriesId);
	}
	
	/**
	 * 根据条件（配件名称或车型名称）查询配件
	 * @param condition
	 * @return
	 */
	@ApiOperation(value = "根据条件（配件名称或车型名称）查询配件", notes = "wux")
	@GetMapping("findpartslistbycondition")
	public ResponseEntity<RestModel<List<PartsTypeAndPartsListDto>>> findPartsListByCondition(
			@RequestParam(value="condition", required=false) String condition) {
		return partsService.findPartsByCondition(condition);
	}
	
	/**
	 *  中台车辆品牌下拉
	 * 	@User liud
	 * 	@Date 2017年10月19日下午5:16:39
	 * 	@param brandName
	 * 	@param pageIndex
	 * 	@param pageSize
	 * 	@return
	 */
	@ApiOperation(value = "车辆品牌下拉框,支持模糊查询,分页查询", notes = "liud")
	@GetMapping("carbrandcombobox")
	public ResponseEntity<RestModel<Page<CommonDto>>> carSeriesCombobox(String brandName,
			@RequestParam(value="pageIndex",required=true)Integer pageIndex, 
			@RequestParam(value="pageSize",required=true)Integer pageSize) {
		return partsService.carBrandCombobox(brandName, pageIndex, pageSize);
	}
	
	/**
	 *  中台车系下拉框数据
	 * 	@User liud
	 * 	@Date 2017年10月18日下午5:22:46
	 * 	@param brandId 品牌ID
	 * 	@param serName 车型名称
	 * 	@param pageIndex 页面索引
	 * 	@param pageSize 页面大小
	 * 	@return
	 */
	@ApiOperation(value = "车系下拉框,支持模糊查询,分页查询", notes = "liud")
	@GetMapping("carseriescombobox")
	public ResponseEntity<RestModel<Page<CommonDto>>> carSeriesCombobox(
			@RequestParam(value="brandId",required=true)Integer brandId,String serName,
			@RequestParam(value="pageIndex",required=true)Integer pageIndex, 
			@RequestParam(value="pageSize",required=true)Integer pageSize) {
		return partsService.carSeriesCombobox(brandId, serName, pageIndex, pageSize);
	}
	
	/**
	 *  中台车型下拉框数据
	 * 	@User liud
	 * 	@Date 2017年10月19日下午5:15:35
	 * 	@param seriesId
	 * 	@param modelName
	 * 	@param pageIndex
	 * 	@param pageSize
	 * 	@return
	 */
	@ApiOperation(value = "车型下拉框,支持模糊查询,分页查询", notes = "liud")
	@GetMapping("carmodelcombobox")
	public ResponseEntity<RestModel<Page<CommonDto>>> carModelCombobox(
			@RequestParam(value="seriesId",required=true)Integer seriesId,
			String modelName,
			@RequestParam(value="pageIndex",required=true)Integer pageIndex, 
			@RequestParam(value="pageSize",required=true)Integer pageSize) {
		return partsService.carModelCombobox(seriesId, modelName, pageIndex, pageSize);
	}
	
	@ApiOperation(value = "配件车型明细查询", notes = "liud")
	@GetMapping("viewpartsmodel")
	public ResponseEntity<RestModel<Page<PartsModelDto>>> viewPartsModel(
			@RequestParam(value="partsId",required=true)Integer partsId,
			@RequestParam(value="pageIndex",required=true) Integer pageIndex,
			@RequestParam(value="pageSize",required=true) Integer pageSize) {
		return partsService.viewPartsModels(partsId,pageIndex,pageSize);
	}
	
	@ApiOperation(value = "删除配件车型关联表ID", notes = "liud")
	@DeleteMapping("delpartscarmodel")
	public @ResponseBody boolean deletePartsCarModelByModelId(@RequestParam(value="carModelId",required=true) Integer carModelId){
		return partsService.deletePartsCarModelByModelId(carModelId);
	}
	
	@ApiOperation(value = "查询所有热门品牌", notes = "wux")
	@GetMapping("hotbrand")
	public ResponseEntity<RestModel<List<CommonDto>>> hotBrand() {
		return partsService.queryAllHotBrand();
	}
	
}
