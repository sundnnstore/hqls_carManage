package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.c.CCustomer;

@Mapper
public interface CCustomerMapper {
	
	@Select("select * from c_customer where global_user_id = #{userId}")
	public CCustomer getCustomerByUserId(@Param("userId")Integer userId);

}