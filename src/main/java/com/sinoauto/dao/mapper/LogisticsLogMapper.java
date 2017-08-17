package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import com.sinoauto.dao.bean.HqlsLogisticsLog;

@Mapper
public interface LogisticsLogMapper {

	/**
	 * 更新物流日志信息
	 * 	@User liud
	 * 	@Date 2017年8月17日下午7:10:24
	 * 	@param hqlsLogisticsLog
	 */
	@Update("update hqls_logistics_log set remark=#{remark} where log_id=#{logId}")
	public void updateLogisticsLog(@Param("remark")String remark,@Param("logId") Integer logId);
	
	@Select("select * from hqls_logistics_log where log_id=#{logId}")
	public HqlsLogisticsLog findLogisticsLog(@Param("purchaseOrderId")Integer purchaseOrderId);
}