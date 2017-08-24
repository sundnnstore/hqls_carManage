package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sinoauto.dao.bean.HqlsCustomer;

@Mapper
public interface CustomerMapper {
	
	@Select("select * from hqls_customer where mobile = #{mobile}")
	public HqlsCustomer getCustomerByMobile(@Param("mobile")String mobile);
	
	public int insert(HqlsCustomer customer);
	
	@Update("update hqls_customer set customer_name = #{customerName},avatar_url=#{avatarUrl} where customer_id = #{customerId}")
	public int updateCustomer(HqlsCustomer customer);

}