package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dto.UserDto;

@Mapper
public interface UserMapper {

	@Select("select * from hqls_user where global_user_id=#{1} and is_useable = 1")
	public HqlsUser getUserByGloabUserId(Integer gloabUserId);

	public int insert(HqlsUser user);

	public Page<UserDto> findUsersByConditions(@Param("roleId") Integer roleId, @Param("userName") String userName, @Param("mobile") String mobile);

	@Select("select * from hqls_user where mobile=#{mobile}")
	public HqlsUser getUserInfoByMobile(@Param("mobile") String mobile);

	@Update("update hqls_user set global_user_id = #{globalUserId} where user_id = #{userId}")
	public int updateGlobalUserId(@Param("globalUserId") Integer globalUserId, @Param("userId") Integer userId);

	@Update("UPDATE hqls_user SET is_useable= " + " CASE is_useable " + " WHEN 0 THEN 1 " + " WHEN 1 THEN 0 " + "END " + "WHERE user_id = #{1}")
	public int updateUserStatus(Integer userId);

	@Update("UPDATE hqls_user set mobile=#{account} where user_id = #{userId}")
	public String changeAccount(@Param("account") String account, @Param("userId") Integer userId);

}