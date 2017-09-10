package com.sinoauto.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dao.mapper.PartsTypeMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsTypeDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;


@Service
public class PartsTypeService {
	@Autowired
	private PartsTypeMapper partsTypeMapper; 
	
	@Autowired
	private PartsMapper partsMapper;
	
	/**
	 * 	配件类型
	 * 	@User liud
	 * 	@Date 2017年8月23日下午12:13:21
	 * 	@return
	 */
	public List<PartsTypeDto> partsTypes(){
		List<PartsTypeDto> partsType=partsTypeMapper.partsType();
		if(partsType==null){partsType=new ArrayList<>();};
		return partsType;
	}
	
	/**
	 *  新增子节点
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:03:34
	 * 	@return
	 */
	public ResponseEntity<RestModel<Integer>> insert(HqlsPartsType hqlsPartsType){
		try {
			HqlsPartsType parent = partsTypeMapper.findPartsTypeByPartsTypeId(hqlsPartsType.getPartsTypeId());
			HqlsPartsType add=new HqlsPartsType();
			add.setTypeName(hqlsPartsType.getTypeName());
			add.setPid(hqlsPartsType.getPartsTypeId());
			add.setPartsType(parent.getPartsType()); //配件类型
			//创建一个配件类型
			partsTypeMapper.insert(add);
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"新增节点树失败");
		}
		return RestModel.success();
	}
	
	/**
	 * 新增同级
	 * @param hqlsPartsType
	 * @return
	 */
	public ResponseEntity<RestModel<Integer>> addSameLevel(HqlsPartsType hqlsPartsType){
		try {
			if(hqlsPartsType.getPartsTypeId()!=null){
				//查询出当前partstypeid的pid
				Integer pid = partsTypeMapper.findPidByPartsTypeId(hqlsPartsType.getPartsTypeId());
				if(pid!=null){
					//查询父级对象
					HqlsPartsType parent = partsTypeMapper.findPartsTypeByPartsTypeId(pid);
					HqlsPartsType add=new HqlsPartsType();
					add.setTypeName(hqlsPartsType.getTypeName());
					add.setPid(pid);
					//add.setPartsType(hqlsPartsType.getPartsType());
					add.setPartsType(parent.getPartsType());
					//创建一个配件类型
					partsTypeMapper.insert(add);
				}
				
			}else{
				return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"配件类型id不能为空");
			}
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"新增节点树失败");
		}
		return RestModel.success();
	}
	
	/**
	 * 检查是否可是删除
	 * @return
	 */
	public ResponseEntity<RestModel<Boolean>> checkIsCanbeDel(Integer partsTypeId){
		List<CommonDto> exitChildren = partsMapper.findPartsTypeListByPid(partsTypeId);
		if(!exitChildren.isEmpty()){
			return RestModel.success(true);
		}else{
			return RestModel.success(false);
		}
	}
	
	/**
	 * 检查是否可是新增
	 * @return
	 */
	public ResponseEntity<RestModel<Boolean>> checkIsCanbeAdd(String typeName){
		Integer count = partsMapper.findPartsTypeListBytypeName(typeName);
		if(count>0){
			return RestModel.success(true);
		}else{
			return RestModel.success(false);
		}
	}
	
	/**
	 * 删除配件类型
	 * @param partsTypeId
	 * @return
	 */
	public ResponseEntity<RestModel<Boolean>> delete(Integer partsTypeId){
		try {
			partsTypeMapper.delete(partsTypeId);
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR,-1, "删除配件类型异常");
		}
		return RestModel.success();
		
	}
	
	/**
	 *  根据配件类型id查询配件类型对象
	 * 	@User liud
	 * 	@Date 2017年9月4日下午12:54:56
	 * 	@param partTypeId
	 * 	@return
	 */
	public ResponseEntity<RestModel<HqlsPartsType>> findHqlsPartsTypeById(Integer partTypeId){
		HqlsPartsType hqlsPartsType = null;
		try {
			hqlsPartsType=partsTypeMapper.findPartsTypeByPartsTypeId(partTypeId);
			if(hqlsPartsType==null){
				hqlsPartsType = new  HqlsPartsType();
			}
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR,-1, "查询配件类型异常");
		}
		return RestModel.success(hqlsPartsType);
	}
	
	
	/**
	 * 	修改节点树,只能修改当前节点的名称
	 * 	@User liud
	 * 	@Date 2017年9月4日下午1:06:34
	 * 	@param pt
	 * 	@return
	 */
	public ResponseEntity<RestModel<HqlsPartsType>> update(HqlsPartsType pt){
		try {
			partsTypeMapper.update(pt);
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR,-1, "修改配件类型异常");
		}
		return RestModel.success();
	}
}
	