package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sinoauto.dao.bean.HqlsUserStore;

@Mapper
public interface UserStoreMapper {
	
	public int insert(HqlsUserStore userStore);
	
	public int updateUserStore(@Param("storeId") Integer storeId);
	
	@Select("select * from hqls_user_store where user_id=#{userId} and store_id =#{storeId}")
	public List<HqlsUserStore> findUserStore(@Param("userId") Integer userId,@Param("storeId") Integer storeId);
	
	@Update("UPDATE hqls_user_store set is_contact where user_id=#{userId} and store_id=#{storeId}")
	public int updateIsContant(@Param("userId") Integer userId,@Param("storeId") Integer storeId);
	
	@Delete("delete from hqls_user_store where user_id=#{userId} and store_id=#{storeId}")
	public int deleteByStoreIdAndUserId(@Param("storeId") Integer storeId,@Param("userId") Integer userId);
	

}