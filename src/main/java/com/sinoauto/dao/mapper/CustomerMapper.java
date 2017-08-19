package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsCustomer;

@Mapper
public interface CustomerMapper {
	
	@Select("select * from hqls_customer where mobile = #{mobile}")
	public HqlsCustomer getCustomerByMobile(@Param("mobile")String mobile);
	
	public int insert(HqlsCustomer customer);

}