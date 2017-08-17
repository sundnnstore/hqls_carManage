package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsUserRole;

@Mapper
public interface UserRoleMapper {
	
	public int insert(HqlsUserRole userRole);
	
	@Delete("delete from hqls_user_role where user_id = #{userId}")
	public int deleteUserRolesByUserId(Integer userId);
	
}