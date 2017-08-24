package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dto.PartsTypeDto;

@Mapper
public interface PartsTypeMapper {
	
	/**
	 *  新增节点树
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:02:55
	 * 	@param hqlsPartsType
	 */
	public void insert(HqlsPartsType hqlsPartsType);
	
	/**
	 *  查询配件类型
	 * 	@User liud
	 * 	@Date 2017年8月23日下午12:07:52
	 * 	@return
	 */
	@Select("select parts_type_id as id,type_name as name,pid from hqls_parts_type")
	public List<PartsTypeDto> partsType();
	
	/**
	 * 修改节点树的关系
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:08:16
	 * 	@param pt
	 */
	public void update(HqlsPartsType pt);
	
}