package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface PartsPicMapper {

	@Select("select url from hqls_parts_pic where parts_id = #{1} order by sorting limit 0,1")
	public String getFirstUrlByPartsId(Integer partsId);
	
}