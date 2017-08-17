package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsUserStore;

@Mapper
public interface UserStoreMapper {
	
	public int insert(HqlsUserStore userStore);

}