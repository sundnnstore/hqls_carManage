package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsUserJoin;

@Mapper
public interface UserJoinMapper {
	
	public Page<HqlsUserJoin> findUserJoinInfo(@Param("storeName") String storeName,@Param("contactName") String contactName,@Param("contactMobile") String contactMobile);
	
	public int insert(HqlsUserJoin hqlsUserJoin);

}
