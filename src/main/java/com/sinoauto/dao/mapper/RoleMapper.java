package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsRole;
import com.sinoauto.dto.RoleDto;
import com.sinoauto.dto.CommonDto;

@Mapper
public interface RoleMapper {
	
	@Select("select * from hqls_role hr left join hqls_user_role hur on hr.role_id = hur.role_id  where hur.user_id = #{userId}")
	public List<HqlsRole> findRolesByUserId(@Param("userId")Integer userId);
	
	@Select("select * from hqls_role")
	public Page<HqlsRole> findRoles();
	
	@Select("select count(*) from hqls_user_role where role_id = #{roleId}")
	public Integer checkRoles(@Param("roleId") Integer roleId);
	
	@Select("select * from hqls_role where role_name = #{roleName}")
	public HqlsRole checkRoleIsExit(String roleName);
	
	@Insert("insert into hqls_role (role_name,role_type,remark,create_time) values (#{roleName},2,'',now())")
	@Options(useGeneratedKeys=true ,keyProperty="roleId")
	public int insertRole(RoleDto role);
	
	@Insert("insert into hqls_role_authority (role_id,authority_id) values (#{roleId},#{authorityId})")
	public int insertRoleAuthority(@Param("roleId")Integer roleId,@Param("authorityId")Integer authorityId);

	@Select("select role_id as `key`, role_name as `value` from hqls_role")
	public List<CommonDto> findAll();
	
}