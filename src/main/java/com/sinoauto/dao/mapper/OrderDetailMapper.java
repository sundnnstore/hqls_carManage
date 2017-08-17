package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsOrderDetail;

@Mapper
public interface OrderDetailMapper {

	public int insert(HqlsOrderDetail detail);
	
}