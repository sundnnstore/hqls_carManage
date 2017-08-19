package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsLogisticsCompany;
import com.sinoauto.dto.CommonDto;

@Mapper
public interface LogisticsCompanyMapper {

	@Select("select logistics_id as `key`, logistics_name as `value` from hqls_logistics_company")
	public List<CommonDto> findAll();
	
	@Select("select * from hqls_logistics_company where logistics_id = #{1}")
	public HqlsLogisticsCompany getById(Integer companyId);
	
}