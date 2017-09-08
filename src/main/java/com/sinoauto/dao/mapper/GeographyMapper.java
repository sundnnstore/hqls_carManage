package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sinoauto.dao.bean.HqlsGeography;

@Mapper
public interface GeographyMapper {

	
	@Insert("insert into hqls_geography (customer_name,mobile,car_no,lat_g,lng_g,lat_b,lng_b,county_id,county_name,address) values(#{customerName},#{mobile},#{carNo},#{latG},#{lngG},#{latB},#{lngB},#{countyId},#{countyName},#{address})")
	public int insertOperData(@Param("customerName")String customerName,@Param("carNo")String carNo,@Param("mobile")String mobile,@Param("latG")String latG,@Param("lngG")String lngG,@Param("latB")String latB,@Param("lngB")String lngB,@Param("countyId")String countyId,@Param("countyName")String countyName,@Param("address")String address);
	
	@Select("SELECT *, ROUND(6378.138*2*ASIN(SQRT(POW(SIN((#{lat}*PI()/180-lat_b*PI()/180)/2),2)+COS(#{lat}*PI()/180)*COS(lat_b*PI()/180)*POW(SIN((#{lng}*PI()/180-lng_b*PI()/180)/2),2)))*1000) AS juli "+ 
				" FROM hqls_geography"+
				" HAVING juli<=#{distance} ")
	public List<HqlsGeography> findCustomersByLatAndLng(@Param("lat") double lat,@Param("lng") double lng,@Param("distance")Integer distance);
	
	@Select("SELECT COUNT(1) FROM hqls_geography WHERE county_id = #{countyCode}")
	public Integer getCustomersCountByCountyCode(@Param("countyCode")String countyCode);
	
	@Select("SELECT COUNT(1) FROM hqls_geography WHERE city_id = #{cityCode}")
	public Integer getCustomersCountByCityCode(@Param("cityCode")String cityCode);
	
	@Select("SELECT CONCAT(city_code,',',full_name) FROM hqls_district WHERE city_name = #{cityName} LIMIT 1")
	public String getCityInfoByCityName(@Param("cityName")String cityName);
	
	@Select("SELECT CONCAT(dist_code,',',full_name) FROM hqls_district")
	public List<String> getCitys();
	
	@Update("update hqls_district set lat = #{lat},lng=#{lng} where dist_code = #{distCode}")
	public int updateCity(@Param("distCode")String distCode,@Param("lat") String lat,@Param("lng")String lng);
}
