package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dto.QueryFlowDto;
import com.sinoauto.dto.RechargeDto;

@Mapper
public interface FinanceFlowMapper {

	public int insert(HqlsFinanceFlow financeFlow);

	public Page<RechargeDto> findFlowListByContidion(@Param("changeType") Integer changeType, @Param("storeId") Integer storeId,
			@Param("customerName") String customerName, @Param("operPerson") String operPerson, @Param("mobile") String mobile,
			@Param("createTime") String createTime, @Param("flowStatus") Integer flowStatus, @Param("checkStatus") Integer checkStatus,
			@Param("payType") Integer payType);

	public Page<HqlsFinanceFlow> findFlowList(@Param("storeId") Integer storeId);

	public HqlsFinanceFlow findFlow(@Param("financeFlowId") Integer financeFlowId);

	@Update("update hqls_finance_flow set flow_status = #{flowStatus} where transaction_no = #{transactionNo} and flow_status != #{flowStatus} ")
	public int updateFlowStatus(@Param("transactionNo") String transactionNo, @Param("flowStatus") Integer flowStatus);

	@Select("select store_id from hqls_finance_flow where transaction_no = #{transactionNo} ")
	public int getStoreIdByTransactionNo(@Param("transactionNo") String transactionNo);

	@Select("select * from hqls_finance_flow where transaction_no = #{transactionNo} ")
	public HqlsFinanceFlow getFlowByTransactionNo(@Param("transactionNo") String transactionNo);

	@Update("update hqls_finance_flow set check_status = #{checkStatus},remark = #{remark},oper_person = #{operPerson} where finance_flow_id = #{financeFlowId}")
	public int updateCheckStatus(@Param("financeFlowId") Integer financeFlowId, @Param("checkStatus") Integer checkStatus,
			@Param("remark") String remark, @Param("operPerson") String operPerson);

	@Update("update hqls_finance_flow set remark = #{remark} where order_no = #{orderNo} and store_id = #{storeId}")
	public int updateOrderRemark(@Param("storeId") Integer storeId, @Param("orderNo") String orderNo, @Param("remark") String remark);
	
	@Select("SELECT * FROM hqls_finance_flow WHERE store_id = #{storeId} AND create_time >= CONCAT(DATE_SUB(#{queryDate}, INTERVAL 1 DAY),' 17:00:00')  AND create_time < CONCAT( #{queryDate},' 17:00:00')  AND flow_status = 1 AND change_type != 1 AND change_type != 2")
	public List<HqlsFinanceFlow> findDailyFlowByStoreIdAndDate(@Param("storeId")Integer storeId,@Param("queryDate")String queryDate);
	
	@Select("SELECT * FROM hqls_finance_flow where DATE_SUB(DATE_FORMAT( CURDATE( ) , '%Y-%m-%d' ), INTERVAL #{days} DAY) < DATE_FORMAT( create_time, '%Y-%m-%d' ) AND store_id = #{storeId} AND flow_status = 1 AND change_type != 1 AND change_type != 2")
	public List<HqlsFinanceFlow> findNearDaysFlows(@Param("storeId")Integer storeId,@Param("days")Integer days);
	
	@Select("SELECT hso.order_amount,hst.service_type_name from hqls_service_order hso "+
			"LEFT JOIN "+
			"hqls_service_type hst "+
			"ON hso.service_type_id = hst.service_type_id "+
			"WHERE hso.store_id = #{storeId} AND hso.finish_time >=CONCAT(DATE_SUB(#{queryDate}, INTERVAL 1 DAY),' 17:00:00') AND hso.finish_time <  CONCAT( #{queryDate},' 17:00:00')")
	public List<QueryFlowDto> findQueryFlows(@Param("storeId")Integer storeId,@Param("queryDate")String queryDate);
	
	@Select("SELECT heo.order_amount,heo.extra_project_desc as serviceTypeName FROM hqls_extra_order heo "+
			"LEFT JOIN hqls_service_order hso "+
			"ON heo.service_order_id = hso.service_order_id "+
			"WHERE heo.pay_status = 2 AND hso.store_id = #{storeId} "+
			"AND heo.dml_time >=CONCAT(DATE_SUB(#{queryDate}, INTERVAL 1 DAY),' 17:00:00') AND heo.dml_time <  CONCAT( #{queryDate},' 17:00:00')")
	public List<QueryFlowDto> findExtraFlows(@Param("storeId")Integer storeId,@Param("queryDate")String queryDate);

}