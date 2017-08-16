package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.sinoauto.dto.StoreDto;

public interface StoreMapper {
	
	
	public List<StoreDto> findStoreInfo(@Param("userId") Integer userId);

}