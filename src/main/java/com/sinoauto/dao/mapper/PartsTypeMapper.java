package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsPartsType;

@Mapper
public interface PartsTypeMapper {
	
	public void insert(HqlsPartsType hqlsPartsType);
}