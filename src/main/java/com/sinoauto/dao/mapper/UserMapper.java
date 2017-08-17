package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dto.UserDto;

@Mapper
public interface UserMapper {

	@Select("select * from hqls_user where global_user_id=#{1} and is_useable = 1")
	public HqlsUser getUserByGloabUserId(Integer gloabUserId);
	
	
	public int insert(HqlsUser user);

	public Page<UserDto> findUsersByConditions(@Param("roleId")Integer roleId, @Param("userName")String userName, @Param("mobile")String mobile);
	
	
	@Select("select * from hqls_user where mobile=#{mobile}")
	public HqlsUser getUserInfoByMobile(@Param("mobile") String mobile);

}