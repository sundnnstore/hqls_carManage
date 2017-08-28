package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dto.RechargeDto;

@Mapper
public interface FinanceFlowMapper {

	public int insert(HqlsFinanceFlow financeFlow);

	public Page<RechargeDto> findFlowListByContidion(@Param("changeType") Integer changeType, @Param("storeId") Integer storeId,
			@Param("customerName") String customerName, @Param("mobile") String mobile, @Param("createTime") String createTime,
			@Param("flowStatus") Integer flowStatus);

}