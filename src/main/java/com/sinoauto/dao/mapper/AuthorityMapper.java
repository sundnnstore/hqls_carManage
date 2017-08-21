package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsAuthority;
import com.sinoauto.dto.AuthorityDto;

@Mapper
public interface AuthorityMapper {
	
	
	public List<HqlsAuthority> findFirstAuthorities(@Param("userId")Integer userId,@Param("isBack")Integer isBack);
	
	public List<HqlsAuthority> findSecondAuthorities(@Param("userId")Integer userId,@Param("isBack")Integer isBack);
	
	@Select("SELECT authority_id as id,authority_name as name,pid as pId FROM hqls_authority")
	public List<AuthorityDto> findAllAuthorities();
	
	@Select("SELECT ha.authority_id as id,ha.authority_name as name,ha.pid as pId FROM hqls_authority ha left join hqls_role_authority"
			+ " hra on ha.authority_id = hra .authority_id where hra.role_id = #{roleId}")
	public List<AuthorityDto> findCheckedAuthoritiesByRoleId(@Param("roleId") Integer roleId);

}