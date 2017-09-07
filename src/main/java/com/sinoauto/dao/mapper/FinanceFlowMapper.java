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
			@Param("flowStatus") Integer flowStatus,@Param("checkStatus") Integer checkStatus,@Param("payType") Integer payType);

	public Page<HqlsFinanceFlow> findFlowList(@Param("storeId") Integer storeId);

	public HqlsFinanceFlow findFlow(@Param("financeFlowId") Integer financeFlowId);

	@Update("update hqls_finance_flow set flow_status = #{flowStatus} where transaction_no = #{transactionNo} and flow_status != #{flowStatus} ")
	public int updateFlowStatus(@Param("transactionNo") String transactionNo, @Param("flowStatus") Integer flowStatus);

	@Select("select store_id from hqls_finance_flow where transaction_no = #{transactionNo} ")
	public int getStoreIdByTransactionNo(@Param("transactionNo") String transactionNo);

	@Select("select * from hqls_finance_flow where transaction_no = #{transactionNo} ")
	public HqlsFinanceFlow getFlowByTransactionNo(@Param("transactionNo") String transactionNo);

	@Update("update hqls_finance_flow set check_status = #{checkStatus},remark = #{remark} where finance_flow_id = #{financeFlowId}")
	public int updateCheckStatus(@Param("financeFlowId") Integer financeFlowId, @Param("checkStatus") Integer checkStatus,@Param("remark") String remark);

	@Update("update hqls_finance_flow set remark = #{remark} where order_no = #{orderNo} and store_id = #{storeId}")
	public int updateOrderRemark(@Param("storeId") Integer storeId, @Param("orderNo") String orderNo, @Param("remark") String remark);
	
}