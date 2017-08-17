package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinoauto.dao.bean.HqlsAuthority;

@Mapper
public interface AuthorityMapper {
	
	
	public List<HqlsAuthority> findFirstAuthorities(@Param("userId")Integer userId,@Param("isBack")Integer isBack);
	
	public List<HqlsAuthority> findSecondAuthorities(@Param("userId")Integer userId,@Param("isBack")Integer isBack);

}