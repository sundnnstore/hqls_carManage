package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dto.RechargeDto;

@Mapper
public interface FinanceFlowMapper {

	public int insert(HqlsFinanceFlow financeFlow);

	public Page<RechargeDto> findFlowListByContidion(@Param("changeType") Integer changeType, @Param("storeId") Integer storeId,
			@Param("customerName") String customerName, @Param("mobile") String mobile, @Param("createTime") String createTime,
			@Param("flowStatus") Integer flowStatus);

	public List<HqlsFinanceFlow> findFlowList(@Param("storeId") Integer storeId);

	public HqlsFinanceFlow findFlow(@Param("financeFlowId") Integer financeFlowId);

	@Update("update hqls_cash_back set flow_status = #{flowStatus} where transaction_no = #{transactionNo}")
	public int updateFlowStatus(@Param("transactionNo") String transactionNo, @Param("flowStatus") Integer flowStatus);

}