package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsPartsBrand;

@Mapper
public interface PartsBrandMapper {

	@Select("select brand.* from hqls_parts as parts, hqls_parts_brand as brand "
			+ "where parts.parts_brand_id = brand.parts_brand_id and parts.parts_id = #{1}")
	public List<HqlsPartsBrand> findPartsBrandListByPartsId(Integer partsId);
	
}