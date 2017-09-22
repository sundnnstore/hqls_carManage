package com.sinoauto.service;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dao.mapper.PartsTypeMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsTreeRecursionDto;
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
	public List<PartsTypeDto> partsTypes() {
		List<PartsTypeDto> partsType = partsTypeMapper.partsType();
		if (partsType == null) {
			partsType = new ArrayList<>();
		}
		;
		return partsType;
	}

	/**
	 *  新增子节点
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:03:34
	 * 	@return
	 */
	public ResponseEntity<RestModel<Integer>> insert(HqlsPartsType hqlsPartsType) {
		Integer partsType = null;
		try {
			if(hqlsPartsType.getPartsTypeId()==0){ //新增一级菜单
				partsType = hqlsPartsType.getPartsType();
			}else{
				HqlsPartsType parent = partsTypeMapper.findPartsTypeByPartsTypeId(hqlsPartsType.getPartsTypeId());
				partsType = parent.getPartsType();
			}
			HqlsPartsType add = new HqlsPartsType();
			add.setTypeName(hqlsPartsType.getTypeName());
			add.setPid(hqlsPartsType.getPartsTypeId());
			add.setPartsType(partsType); // 配件类型
			add.setIcon(hqlsPartsType.getIcon());
			// 创建一个配件类型
			partsTypeMapper.insert(add);
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "新增节点树失败");
		}
		return RestModel.success();
	}

	/**
	 * 新增同级
	 * @param hqlsPartsType
	 * @return
	 */
	public ResponseEntity<RestModel<Integer>> addSameLevel(HqlsPartsType hqlsPartsType) {
		try {
			Integer partsType = 0;
			if (hqlsPartsType.getPartsTypeId() != null) {
				// 查询出当前partstypeid的pid
				Integer pid = partsTypeMapper.findPidByPartsTypeId(hqlsPartsType.getPartsTypeId());
				if (pid != null&&pid==0) {
					partsType = hqlsPartsType.getPartsType();
				}else{
					// 查询父级对象
					HqlsPartsType parent = partsTypeMapper.findPartsTypeByPartsTypeId(pid);
					partsType =parent.getPartsType();
				}
				HqlsPartsType add = new HqlsPartsType();
				add.setTypeName(hqlsPartsType.getTypeName());
				add.setPid(pid);
				add.setPartsType(partsType);
				add.setIcon(hqlsPartsType.getIcon());
				partsTypeMapper.insert(add);
			} else {
				return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "配件类型id不能为空");
			}
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "新增节点树失败");
		}
		return RestModel.success();
	}

	/**
	 * 检查是否可是删除
	 * @return
	 */
	public ResponseEntity<RestModel<CommonDto>> checkIsCanbeDel(Integer partsTypeId) {
		//查询图片
		HqlsPartsType partsType = partsTypeMapper.findPartsTypeByPartsTypeId(partsTypeId);
		CommonDto common = new CommonDto();
		common.setValue(partsType.getIcon());
		List<CommonDto> exitChildren = partsMapper.findPartsTypeListByPid(partsTypeId); //是否存在子集
		if (!exitChildren.isEmpty()) { 
			common.setKey(1);
			return RestModel.success(common);
		} else {
			//如果不存在子集,是否下面有商品
			Integer count = partsMapper.findPartsByPartsTypeId(partsTypeId);
			if(count>0){
				common.setKey(1);
			}else{
				common.setKey(0);	
			}
			
			return RestModel.success(common);
		}
	}

	/**
	 * 检查是否可是新增
	 * @return
	 */
	public ResponseEntity<RestModel<Boolean>> checkIsCanbeAdd(String typeName) {
		Integer count = partsMapper.findPartsTypeListBytypeName(typeName);
		if (count > 0) {
			return RestModel.success(true);
		} else {
			return RestModel.success(false);
		}
	}

	/**
	 * 删除配件类型
	 * @param partsTypeId
	 * @return
	 */
	public ResponseEntity<RestModel<Boolean>> delete(Integer partsTypeId) {
		try {
			partsTypeMapper.delete(partsTypeId);
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, -1, "删除配件类型异常");
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
	public ResponseEntity<RestModel<HqlsPartsType>> findHqlsPartsTypeById(Integer partTypeId) {
		HqlsPartsType hqlsPartsType = null;
		try {
			hqlsPartsType = partsTypeMapper.findPartsTypeByPartsTypeId(partTypeId);
			if (hqlsPartsType == null) {
				hqlsPartsType = new HqlsPartsType();
			}
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, -1, "查询配件类型异常");
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
	@Transactional
	public ResponseEntity<RestModel<HqlsPartsType>> update(HqlsPartsType pt) {
		try {
			// 1.如果只修改当前节点的名称
			if (pt.getTypeName() != null || !pt.getTypeName().equals("undefined")) {
				partsTypeMapper.update(pt);
			}

			// 如果配件类型不为空,根据pid修改所有配件类型的类型
			if (pt.getPartsType() != null || !pt.getPartsType().equals("")) {
				//partsTypeMapper.updatePartsType(pt);
				updateByRecursion(pt.getPartsTypeId(),pt.getPartsType());
			}
			return RestModel.success();
		} catch (Exception e) {
			System.out.println(e);
			// 事物处理
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, -1, "修改配件类型异常");
		
	}
	
	/**
	 *  递归更新
	 * 	@User liud
	 * 	@Date 2017年9月11日下午2:01:53
	 * 	@param partsTypeId
	 */
	public void updateByRecursion(Integer partsTypeId,Integer partsType){
		List<PartsTreeRecursionDto> childTree = partsMapper.partsChildTreeByPid(partsTypeId);
		//更新所有子节点
		if (childTree != null) {
			for (PartsTreeRecursionDto child : childTree) {		
				updateByRecursion(child.getId(),partsType);
				HqlsPartsType pt  = new HqlsPartsType();
				pt.setPartsTypeId(child.getId());
				pt.setPartsType(partsType);
				System.out.println("跳出来子节点的名称:"+child.getName()+"\nID:"+child.getId());
				partsTypeMapper.update(pt);
			}
		}
	}
}
