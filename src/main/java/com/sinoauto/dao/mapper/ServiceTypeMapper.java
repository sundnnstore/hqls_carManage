package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsServiceType;

@Mapper
public interface ServiceTypeMapper {
	
	@Select("select * from hqls_service_type where service_type_name like concat('%',ifnull(#{1},''),'%')")
	public Page<HqlsServiceType> findServiceTypesByTypeName(String typeName);
	
	public Integer insert(HqlsServiceType serviceType);
	
	public Integer update(HqlsServiceType serviceType);
	
	@Update("UPDATE hqls_service_type SET is_usable= "+
			" CASE is_usable "+ 
			" WHEN 0 THEN 1 "+
			" WHEN 1 THEN 0 "+
			"END "+
			"WHERE service_type_id = #{1}")
	public Integer updateServiceTypeStatus(Integer serviceTypeId);
	
	@Select("select * from hqls_service_type where where service_type_name=#{1} and is_usable = 1")
	public HqlsServiceType getServiceTypesByServiceTypeName(String typeName);

}