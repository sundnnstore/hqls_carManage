package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsFinanceFlow;

@Mapper
public interface FinanceFlowMapper {
	
	public int insert(HqlsFinanceFlow financeFlow);

}