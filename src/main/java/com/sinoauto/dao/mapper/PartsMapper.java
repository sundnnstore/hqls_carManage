package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;

@Mapper
public interface PartsMapper {
	
	/**
	 * 按主键查询配件
	 * @param partsId
	 * @return
	 */
	@Select("select * from hqls_parts where parts_id = #{1} and is_usable = 1")
	public HqlsParts getPartsById(Integer partsId);

	/**
	 * 按配件类型查询第一级分类，pid = 0
	 * @param partsType（1，易损件；2通用件）
	 * @return
	 */
	@Select("select parts_type_id, type_name from hqls_parts_type where parts_type = #{1} and pid = 0")
	public Page<CommonDto> findPartsTypeListByType(Integer partsType);
	
	/**
	 * 按父级Id查询配件类型
	 * @param pid
	 * @return
	 */
	@Select("select parts_type_id, type_name from hqls_parts_type where pid = #{1}")
	public Page<CommonDto> findPartsTypeListByPid(Integer pid);
	
	/**
	 * 根据配件类型查询所有配件信息
	 * @param partTypeId
	 * @return
	 */
	public Page<PartsDesListDto> findPartsListByTypeId(@Param("partTypeId") Integer partTypeId);
	
	@Select("select count(1) from hqls_parts_type where type.pid = #{1}")
	public int getPartsCountByPid(Integer pid);
	
	/**
	 * 查询配件详情
	 * @param partsId
	 * @return
	 */
	public PartsDetailDto getPartsDetail(@Param("partsId") Integer partsId);
	
	/**
	 * 根据配件Id查询数据库最新信息
	 * @param partsIds
	 * @return
	 */
	public PartsDesListDto checkShopCart(@Param("partsId") Integer partsId);
	
}