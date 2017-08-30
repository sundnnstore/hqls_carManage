package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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

	public Page<HqlsFinanceFlow> findFlowList(@Param("storeId") Integer storeId);

	public HqlsFinanceFlow findFlow(@Param("financeFlowId") Integer financeFlowId);

	@Update("update hqls_finance_flow set flow_status = #{flowStatus} where transaction_no = #{transactionNo}")
	public int updateFlowStatus(@Param("transactionNo") String transactionNo, @Param("flowStatus") Integer flowStatus);

	@Select("select store_id from hqls_finance_flow where transaction_no = #{transactionNo} ")
	public int getStoreIdByTransactionNo(@Param("transactionNo") String transactionNo);

}