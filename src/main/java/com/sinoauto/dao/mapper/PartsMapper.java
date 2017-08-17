package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsOperDto;

@Mapper
public interface PartsMapper {

	/**
	 * 按配件类型查询第一级配件，pid = 0
	 * @param partsType（1，易损件；2通用件）
	 * @return
	 */
	@Select("select parts.id, parts.parts_name "
			+ "from hqls_parts as parts, hqls_parts_type as type where type.pid = 0 "
			+ "and type.parts_type = #{1} and parts.parts_type_id = type.parts_type_id")
	public Page<CommonDto> findPartsListByType(Integer partsType);
	
	/**
	 * 按父级Id查询配件
	 * @param pid
	 * @return
	 */
	@Select("select parts.id, parts.parts_name from hqls_parts as parts, hqls_parts_type as type "
			+ "where type.pid = #{1} and parts.parts_type_id = type.parts_type_id")
	public Page<CommonDto> findPartsListByPid(Integer pid);
	
	/**
	 * 	中台按 条件查询配件的信息
	 * 	@User liud
	 * 	@Date 2017年8月17日下午1:18:10
	 * 	@param partsDto
	 * 	@return
	 */
	public List<PartsDto> findPartsByCondition(PartsDto partsDto);
	
	/**
	 * 	中台新增配件
	 * 	@User liud
	 * 	@Date 2017年8月17日下午1:19:42
	 * 	@param partsOperDto
	 */
	public void addParts(PartsOperDto partsOperDto);
	
	/**
	 * 	中台修改配件 
	 * 		1.包括上下架
	 * 		2.配件配置参数修改
	 * 		3.配件图片修改
	 * 	@User liud
	 * 	@Date 2017年8月17日下午1:19:30
	 * 	@param partsOperDto
	 */
	public void updateParts(PartsOperDto partsOperDto);
}