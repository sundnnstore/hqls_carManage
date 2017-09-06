package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsGeography;

@Mapper
public interface GeographyMapper {

	
	@Insert("insert into hqls_geography (customer_name,mobile,car_no,lat_g,lng_g,lat_b,lng_b,address) values(#{customerName},#{mobile},#{carNo},#{latG},#{lngG},#{latB},#{lngB},#{address})")
	public int insertOperData(@Param("customerName")String customerName,@Param("carNo")String carNo,@Param("mobile")String mobile,@Param("latG")String latG,@Param("lngG")String lngG,@Param("latB")String latB,@Param("lngB")String lngB,@Param("address")String address);
	
	@Select("SELECT *, ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{lat}*PI()/180-lat_b*PI()/180)/2),2)+COS(#{lat}*PI()/180)*COS(lat_b*PI()/180)*POW(SIN((#{lng}*PI()/180-lng_b*PI()/180)/2),2)))*1000) AS juli "+ 
				" FROM hqls_geography"+
				" WHERE lat_b > #{lat} -0.01"+
				" and lat_b < #{lat} +0.01"+
				" AND lng_b >#{lng} -0.01"+
				" AND lng_b <#{lng} +0.01"+
				" HAVING juli<=1000 ")
	public List<HqlsGeography> findCustomersByLatAndLng(@Param("lat") double lat,@Param("lng") double lng);
}
