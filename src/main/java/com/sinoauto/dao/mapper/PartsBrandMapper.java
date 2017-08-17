package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsPartsBrand;

@Mapper
public interface PartsBrandMapper {

	@Select("select * from hqls_parts_brand where parts_id = #{1}")
	public List<HqlsPartsBrand> findPartsBrandListByPartsId(Integer partsId);
	
}