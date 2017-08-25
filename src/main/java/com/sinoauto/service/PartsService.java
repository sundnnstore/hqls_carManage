package com.sinoauto.service;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPartsAttrExtr;
import com.sinoauto.dao.bean.HqlsPartsPic;
import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dao.mapper.PartsAttrExtrMapper;
import com.sinoauto.dao.mapper.PartsPicMapper;
import com.sinoauto.dao.mapper.PartsTypeMapper;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.dto.PartsQueryDto;
import com.sinoauto.dto.PartsTreeDto;
import com.sinoauto.dto.PartsTreeRecursionDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;


@Service
public class PartsService {

	@Autowired
	private PartsMapper partsMapper;
	
	@Autowired
	private PartsPicMapper partsPicMapper;
	
	@Autowired
	private PartsAttrExtrMapper partsAttrExtrMapper;
	
	@Autowired
	private PartsTypeMapper partsTypeMapper;
	
	public ResponseEntity<RestModel<List<CommonDto>>> findListByType(Integer partsType, Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		Page<CommonDto> page = partsMapper.findPartsTypeListByType(partsType);
		return RestModel.success(page, (int) page.getTotal());
	}
	
	public ResponseEntity<RestModel<Map<String, Object>>> findAllParts(Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		Page<CommonDto> page1 = partsMapper.findPartsTypeListByType(1);
		PageHelper.startPage(pageIndex, pageSize);
		Page<CommonDto> page2 = partsMapper.findPartsTypeListByType(2);
		Map<String, Object> map = new HashMap<>();
		map.put("obj1", page1);
		map.put("count1", page1.getTotal());
		map.put("obj2", page2);
		map.put("count2", page2.getTotal());
		return RestModel.success(map);
	} 
	
	public ResponseEntity<RestModel<Boolean>> hasChildType(Integer partsTypeId) {
		//查询此类别下的子类数量
		int count = partsMapper.getPartsCountByPid(partsTypeId);
		if (count > 0) {
			return RestModel.success(true);
		}
		return RestModel.success(false);
	}
	
	public ResponseEntity<RestModel<Object>> findListByPid(Integer partsTypeId, Integer pageIndex, Integer pageSize) {
		//查询此类别下的子类数量
		int count = partsMapper.getPartsCountByPid(partsTypeId);
		Object objList;
//		PageHelper.startPage(pageIndex, pageSize);
		//此类别下还有子类
		if (count > 0) {
			objList = partsMapper.findPartsTypeListByPid(partsTypeId);
		}
		//此类别下没有子类，展示商品列表
		else {
			objList = partsMapper.findPartsListByTypeId(partsTypeId, null);
		}
		return RestModel.success(objList);
	}
	
	public ResponseEntity<RestModel<List<PartsDesListDto>>> findPartsByConditon(Integer partsTypeId, String condition) {
		if (condition != null) {
			condition = condition.trim();
		}
		return RestModel.success(partsMapper.findPartsListByTypeId(partsTypeId, condition));
	}
	
	public ResponseEntity<RestModel<PartsDetailDto>> getPartsDetail(Integer partsId) {
		
		return RestModel.success(partsMapper.getPartsDetail(partsId));
	}
	
	/**
	 * 
	 * 按条件查询配件分类
	 * @User liud
	 * @Date 2017年8月17日上午11:28:59
	 * @param partsDto
	 * @return
	 */
	public ResponseEntity<RestModel<Page<PartsDto>>> findPartsByCondition(PartsQueryDto partsDto,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		List<PartsDto> partsDtos = null;//返回集合
		Page<PartsDto> partsDtoPage=null;//返回页面
		try {
			partsDtos= partsMapper.findPartsByCondition(partsDto);
			if(partsDtos==null){
				partsDtos =new ArrayList<>();
				
			}
			partsDtoPage =(Page<PartsDto>)partsDtos;
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"查询信息异常");
		}
		return RestModel.success(partsDtoPage,(int)partsDtoPage.getTotal());
	}
	
	/**
	 * 新增
	 * @User liud
	 * @Date 2017年8月17日下午12:44:33
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<Integer>> addParts(PartsOperDto partsOperDto){
		try {
			if(partsOperDto!=null){
				//配件父类
				HqlsPartsType hqlsPartsType = new HqlsPartsType();
				hqlsPartsType.setPartsType(partsOperDto.getPartsType());
				hqlsPartsType.setPid(partsOperDto.getPid());
				hqlsPartsType.setPname(partsOperDto.getPname());
				hqlsPartsType.setTypeName(partsOperDto.getPartsName()); //
				partsTypeMapper.insert(hqlsPartsType);
				
				//插入配件属性基础表
				HqlsParts hqlsParts = new HqlsParts();	
				hqlsParts.setCreateTime(new Date());
				hqlsParts.setCurPrice(partsOperDto.getCurPrice());
				hqlsParts.setDiscount(partsOperDto.getDiscount());
				hqlsParts.setDmlTime(new Date());
				hqlsParts.setIsUsable(partsOperDto.getIsUsable());
				hqlsParts.setOrigin(partsOperDto.getOrigin());
				hqlsParts.setPartsBrandId(partsOperDto.getPartsBrandId());
				hqlsParts.setPartsCode("HQ"+System.currentTimeMillis());
				hqlsParts.setPartsFactory(partsOperDto.getPartsFactory());
				hqlsParts.setPartsModel(partsOperDto.getPartsModel());
				hqlsParts.setPartsName(partsOperDto.getPartsName());
				hqlsParts.setPartsSpec(partsOperDto.getPartsSpec());
				hqlsParts.setPartsTypeId(hqlsPartsType.getPartsTypeId());//配件类型id
				hqlsParts.setPartsUnit(partsOperDto.getPartsUnit());
				hqlsParts.setPrice(partsOperDto.getPrice());
				hqlsParts.setRemark(partsOperDto.getRemark());
				hqlsParts.setShelfLife(partsOperDto.getShelfLife());
				partsMapper.insert(hqlsParts);
				//插入配件图片,和配件动态属性
				insertPartsPicAndAttrExr(partsOperDto,hqlsParts.getPartsId());
			}
			return RestModel.success();
		} catch (Exception e) {
			System.out.println(e);
			//事物处理
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"新增商品信息异常");
	}
	
	/**
	 * 修改
	 * @User liud
	 * @Date 2017年8月17日下午12:44:44
	 * @return
	 * 共用接口
	 *		1. 编辑配件信息
	 *		2. 上下架
	 */
	@Transactional
	public ResponseEntity<RestModel<Integer>> updateParts(PartsOperDto partsOperDto){
		try {
			if(partsOperDto!=null){
				Integer partsId = partsOperDto.getPartsId();
				if(partsId!=null){
					//根据配件ID查询 配给类型
					Integer partsTypeId =partsMapper.findPartsTypeIdByPartsId(partsId);
					if(partsOperDto.getPid()!=null){
						HqlsPartsType pt =new HqlsPartsType();
						pt.setPartsTypeId(partsTypeId);
						pt.setPid(partsOperDto.getPid());
						pt.setPname(partsOperDto.getPname());
						pt.setPartsType(partsOperDto.getPartsType());
						pt.setTypeName(partsOperDto.getTypeName());
						//根据配件类型修改配件父级菜单
						partsTypeMapper.update(pt);
					}
					
					
					//修改配件的基本属性
					partsMapper.update(partsOperDto);
					List<HqlsPartsPic> partsPics =partsOperDto.getPartsPics();
					List<HqlsPartsAttrExtr> partsAttrExtrs=partsOperDto.getPartsAttrExtrs();
					if(partsPics!=null&&!partsPics.isEmpty()){
						//删除配件id对应的所有图片
						partsPicMapper.delete(partsId);
					}
					if(partsAttrExtrs!=null&&!partsAttrExtrs.isEmpty()){
						//删除配件的动态属性
						partsAttrExtrMapper.delete(partsId);
					}
					
					//插入配件图片,和配件动态属性
					if(partsAttrExtrs!=null||partsPics!=null){
						insertPartsPicAndAttrExr(partsOperDto,partsId);
					}
				}
			}
			return RestModel.success();
		} catch (Exception e) {
			System.out.println(e);
			//事物处理
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"修改商品信息异常");
	}
	
	/**
	 * 	插入配件图片和配件参数配置
	 * 	@User liud
	 * 	@Date 2017年8月17日下午4:32:28
	 * 	@param partsOperDto
	 */
	public void insertPartsPicAndAttrExr(PartsOperDto partsOperDto,Integer partsId){
		/**
		 * 配件插入表
		 */
		HqlsPartsAttrExtr hqlsPartsAttrExtr=null;//插入配件动态属性表
		HqlsPartsPic partsPic=null;//插入配件图片
		
		//配件的配置属性
		if(partsOperDto.getPartsAttrExtrs().size()>0){
			for (HqlsPartsAttrExtr partsAttrExtrs : partsOperDto.getPartsAttrExtrs()) {
				hqlsPartsAttrExtr =new HqlsPartsAttrExtr();
				hqlsPartsAttrExtr.setAttrKey(partsAttrExtrs.getAttrKey());
				hqlsPartsAttrExtr.setAttrValue(partsAttrExtrs.getAttrValue());
				hqlsPartsAttrExtr.setPartsId(partsId);
				partsAttrExtrMapper.insert(hqlsPartsAttrExtr);
			}
		}
		//配件图片
		List<HqlsPartsPic> hqlsPartsPic=partsOperDto.getPartsPics();
		if(partsOperDto.getPartsPics().size()>0){
			for (int i = 0; i < hqlsPartsPic.size(); i++) {
				partsPic =new HqlsPartsPic();
				partsPic.setPartsId(partsId);
				partsPic.setCreateTime(new Date());
				partsPic.setSorting(i+1);
				partsPic.setUrl(hqlsPartsPic.get(i).getUrl());
				partsPicMapper.insert(partsPic);
			}
		}
	}
	
	/**
	 * 	查询采购订单对应的配件ID
	 * 	@User liud
	 * 	@Date 2017年8月18日下午4:18:39
	 * 	@param purchOrderId
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<HqlsParts>>> findPartsByPurchOrderId(Integer purchOrderId){
		List<HqlsParts> parts =null;
		try {
			parts= partsMapper.findPartsByPurchOrderId(purchOrderId);
			if(parts==null){
				parts =new ArrayList<>();
			}
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"查询订单对应的配件信息异常");
		}
		return RestModel.success(parts);
	}
	
	/**
	 *  配件树形菜单,点击查询下一级菜单
	 * 		1.根据一个pid找到下面的所有子类的id
	 * 		2.再拿子类的id去pid中找看是否存在子id
	 * 		3.如果存在子id则再去找
	 * 	@User liud
	 * 	@Date 2017年8月19日上午11:06:56
	 * 	@param pId
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<PartsTreeDto>>> partsTree(Integer pid){
		List<PartsTreeDto> childrenParts = null;
		try {
			pid=pid==null?1:pid;
			childrenParts = partsMapper.partsTree(pid);
			if(childrenParts==null){
				childrenParts =new ArrayList<>();
			}
			
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"配件树形菜单查询异常");
		}
		return RestModel.success(childrenParts);
	}
	
	/**
	 * 	配件树形菜单查询,根据一级菜单，加载出所有的子集菜单
	 * 	@User liud
	 * 	@Date 2017年8月19日下午1:35:56
	 * 	@param pid  第一级的菜单id
	 * 	@return
	 */
	public PartsTreeRecursionDto partsTreeRecursion(Integer pid){
		//父级菜单自己
		PartsTreeRecursionDto partsParent = partsMapper.partsParent(pid);
		if(partsParent==null){partsParent=new PartsTreeRecursionDto();};
		//查询出子集的集合
		List<PartsTreeRecursionDto> childTree = null; 
		childTree = partsMapper.partsChildTreeByPid(pid);
		//判断是否存在子菜单
		if(childTree!=null){
			//创建存储子菜单集合
			partsParent.setChildren(new ArrayList<>());
			//存储子节点树；
			for (PartsTreeRecursionDto child : childTree) {
				//查询子菜单下是否存在子菜单
				PartsTreeRecursionDto part = partsTreeRecursion(child.getId());
				partsParent.getChildren().add(part);
			}
		}else{
			childTree = new ArrayList<>();
		}
		return partsParent;
	}
	
	/**
	 *  根据配件等级查询配件信息
	 * 	@User liud
	 * 	@Date 2017年8月23日下午4:02:08
	 * 	@param onelevel 第一个等级,可以查询所有子集
	 * 	@param twolevel 第二个等级,查询上一个等级,和下一个等级
	 * 	@param threelevel 查询所有父级
	 * 	@return
	 */
	public PartsTreeRecursionDto findPartsByLevel(Integer onelevel,Integer twolevel,Integer threelevel){
		return null;
	}
}