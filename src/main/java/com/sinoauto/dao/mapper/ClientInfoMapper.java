package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface ClientInfoMapper {
	
	@Delete("delete from hqls_client_info where client_id = #{1}")
	public int deleteClientInfoByCId(String clientId);
	
	@Insert("insert into hqls_client_info (client_id,user_id,create_time) values (#{clientId},#{userId},now())")
	public int insertClientInfo(@Param("clientId")String clientId,@Param("userId")Integer userId);

}