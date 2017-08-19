package com.sinoauto.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Date;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dto.CommonDto;
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
	
	public ResponseEntity<RestModel<List<CommonDto>>> findListByType(Integer partsType) {
		return RestModel.success(partsMapper.findPartsTypeListByType(partsType));
	}
	
	public ResponseEntity<RestModel<Object>> findListByPid(Integer partsTypeId) {
		//查询此类别下的子类数量
		int count = partsMapper.getPartsCountByPid(partsTypeId);
		Object objList;
		//此类别下还有子类
		if (count > 0) {
			objList = partsMapper.findPartsTypeListByType(partsTypeId);
		}
		//此类别下没有子类，展示商品列表
		else {
			objList = partsMapper.findPartsListByTypeId(partsTypeId);
		}
		
		return RestModel.success(objList);
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
	public ResponseEntity<RestModel<Page<PartsDto>>> findPartsByCondition(PartsDto partsDto,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		List<PartsDto> partsDtos = null;//返回集合
		Page<PartsDto> partsDtoPage=null;//返回页面
		try {
			partsDtos= partsMapper.findPartsByCondition(partsDto);
			if(partsDtos==null){
				partsDtos =new ArrayList<>();
				
			}
			partsDtoPage =(Page<PartsDto>)partsDtos;
			return RestModel.success(partsDtoPage,(int)partsDtoPage.getTotal());
		} catch (Exception e) {
			//事物处理
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"查询信息异常");
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
				hqlsPartsType.setTypeName(partsOperDto.getTypeName());
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
				hqlsParts.setPartsCode(partsOperDto.getPartsCode());
				hqlsParts.setPartsFactory(partsOperDto.getPartsFactory());
				hqlsParts.setPartsModel(partsOperDto.getPartsModel());
				hqlsParts.setPartsName(partsOperDto.getPartsName());
				hqlsParts.setPartsSpec(partsOperDto.getPartsSpec());
				hqlsParts.setPartsTypeId(partsOperDto.getPartsTypeId());//配件类型id,配件父类
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
					//修改配件的基本属性
					partsMapper.update(partsOperDto);
					Integer partsPicsSize =partsOperDto.getPartsPics().size();
					Integer partsAttrExtrsSize=partsOperDto.getPartsAttrExtrs().size();
					if(partsPicsSize>0){
						//删除配件id对应的所有图片
						partsPicMapper.delete(partsId);
					}
					if(partsAttrExtrsSize>0){
						//删除配件的动态属性
						partsAttrExtrMapper.delete(partsId);
					}
					
					//插入配件图片,和配件动态属性
					if(partsPicsSize>0||partsAttrExtrsSize>0){
						insertPartsPicAndAttrExr(partsOperDto,partsId);
					}
				}
			}
			return RestModel.success();
		} catch (Exception e) {
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
				partsAttrExtrMapper.insert(partsAttrExtrs);
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
	 * 	未完成,等写完页面再写
	 * 	@User liud
	 * 	@Date 2017年8月19日下午1:35:56
	 * 	@param pid
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<PartsTreeRecursionDto>>> partsTreeRecursion(Integer pid){
		List<PartsTreeRecursionDto> childrenParts = null;
		try {
			pid=pid==null?1:pid;
			//查询每一级菜单,第一次进来是第一级
			childrenParts = partsMapper.partsTreeRecursion(pid);
			if(childrenParts!=null){
				//调用
				for (PartsTreeRecursionDto child : childrenParts) {
					if(child.getPartsTypeId()!=null){
						//每一次将
						partsTreeRecursion(child.getPartsTypeId());
					}
					
				}
			}else{
				childrenParts = new ArrayList<>();
			}
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"配件树形菜单递归查询异常");
		}
		
		return null;
	}
		
}