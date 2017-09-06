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

	@Select("select * from hqls_user where user_id = (select user_id from hqls_user_store where store_id = #{1} and is_contact = 1 limit 1)")
	public HqlsUser getUserByStoreId(Integer storeId);
	
	@Select("select mobile from hqls_user where user_id = #{userId}")
	public String getUserByUserId(@Param("userId") Integer userId);
	
	@Update("UPDATE hqls_user set mobile=#{newMobile} where user_id = #{userId}")
	public int updateUserByUserId(@Param("newMobile") String newMobile,@Param("userId") Integer userId);
	
	@Select("select * from hqls_user where user_id = #{1}")
	public HqlsUser getUserById(Integer userId);
	
	@Update("update hqls_user set user_name = #{userName}, dml_time = now() where user_id = #{userId}")
	public int updateUser(HqlsUser user);
	
	public UserDto getUserDtoByUserId(@Param("userId") Integer userId);
	
	@Select("SELECT hs.store_id FROM hqls_user_store  hus LEFT JOIN hqls_store hs "+
			"ON hus.store_id = hs.store_id "+
			"WHERE hus.user_id =( SELECT user_id FROM hqls_user WHERE global_user_id = #{1} AND is_useable =1) "+ 
			"AND hs.is_useable = 1 LIMIT 1")
	public Integer checkUser(Integer globalUserId);
	
	@Select("select * from hqls_user where mobile = #{1}")
	public HqlsUser getUserByMobile(String mobile);
	
	@Update("update hqls_user set is_useable=true where user_id = #{userId}")
	public int updateUserIsUseable(@Param("userId") Integer userId);

}