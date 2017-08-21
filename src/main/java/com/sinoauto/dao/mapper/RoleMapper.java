package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsRole;
import com.sinoauto.dto.CommonDto;

@Mapper
public interface RoleMapper {
	
	@Select("select * from hqls_role hr left join hqls_user_role hur on hr.role_id = hur.role_id  where hur.user_id = #{userId}")
	public List<HqlsRole> findRolesByUserId(@Param("userId")Integer userId);

	@Select("select role_id as `key`, role_name as `value` from hqls_role")
	public List<CommonDto> findAll();
	
}