package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface RebateMapper {

	// select sum(rebate_money),is_rebate from hqls_rebate where store_id=1 group by is_rebate ;
	// select sum(rebate_money) from hqls_rebate where store_id = 1 and is_rebate = 1 and DATEDIFF(now(),rebate_time)=0;

	@Select("select sum(rebate_money) from hqls_rebate where store_id = #{storeId} and is_rebate = 1 and DATEDIFF(now(),rebate_time)=0;")
	public Double getCurrentReturnedMoney(@Param("storeId") Integer storeId);

	@Select("select sum(rebate_money) from hqls_rebate where store_id = #{storeId} and is_rebate=1;")
	public Double getHaveReturned(@Param("storeId") Integer storeId);

	@Select("select sum(rebate_money) from hqls_rebate where store_id = #{storeId} and is_rebate=0;")
	public Double getRemainingReturned(@Param("storeId") Integer storeId);

}