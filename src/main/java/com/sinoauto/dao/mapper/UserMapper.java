package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsUser;

@Mapper
public interface UserMapper {
	
	@Select("select * from hqls_user where global_user_id=#{1} and is_useable = 1")
	public HqlsUser getUserByGloabUserId(Integer gloabUserId);

}