package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsRebate;

@Mapper
public interface RebateMapper {
	
	public int insert(HqlsRebate rebate);
	
}