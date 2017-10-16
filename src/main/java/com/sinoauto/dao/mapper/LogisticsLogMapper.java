package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsLogisticsLog;

@Mapper
public interface LogisticsLogMapper {

	
	/**
	 * 	更新物流日志信息
	 * 	@User liud
	 * 	@Date 2017年8月17日下午7:10:24
	 * 	@param hqlsLogisticsLog
	 */
	public void insert(HqlsLogisticsLog hqlsLogisticsLog);
	
	/**
	 * 	查询商品的物流信息,输出页面
	 * 	@User liud
	 * 	@Date 2017年8月17日下午8:01:10
	 * 	@param purchaseOrderId
	 * 	@return
	 */
	@Select("select * from hqls_logistics_log where purchase_order_id=#{purchaseOrderId} order by create_time desc")
	public List<HqlsLogisticsLog> findLogisticsLogs(@Param("purchaseOrderId") Integer purchaseOrderId);
}