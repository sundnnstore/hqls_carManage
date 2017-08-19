package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DictMapper {

	@Select("select description from hqls_dict where dict_key = #{dictKey} and dict_value = #{dictValue}")
	public String getDescByKeyAndValue(@Param("dictKey") String dictKey, @Param("dictValue")  String dictValue);
	
}