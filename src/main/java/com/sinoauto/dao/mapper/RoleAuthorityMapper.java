package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface RoleAuthorityMapper {
	
	@Delete("delete from hqls_role_authority where role_id = #{roleId}")
	public int deleteByRoleId(Integer roleId);

}