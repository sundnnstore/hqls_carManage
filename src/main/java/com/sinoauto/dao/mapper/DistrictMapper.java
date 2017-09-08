package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface DistrictMapper {
	
	@Select("SELECT CONCAT(full_name,',',lat,',',lng,',',dist_code) FROM hqls_district WHERE city_name =#{cityName}")
	public List<String> findAllCountyByCityName(@Param("cityName")String cityName);

}
