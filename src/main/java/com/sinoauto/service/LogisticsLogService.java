package com.sinoauto.service;
import java.util.List;

import org.apache.http.HttpResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.bean.HqlsLogisticsLog;
import com.sinoauto.dao.mapper.LogisticsLogMapper;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;


@Service
public class LogisticsLogService {

	@Autowired
	private LogisticsLogMapper logisticsLogMapper; 
	
	/**
	 * 	
	 * 	@User liud
	 * 	@Date 2017年8月17日下午8:19:57
	 */
	public void findLogisticsLogs(Integer purchaseOrderId,HttpResponse response){
		List<HqlsLogisticsLog> hqlsLogisticsLogs =logisticsLogMapper.findLogisticsLogs(purchaseOrderId);
		StringBuilder logHtml = new StringBuilder();
		if(hqlsLogisticsLogs!=null){
			for (HqlsLogisticsLog hqlsLogisticsLog : hqlsLogisticsLogs) {
				logHtml.append(hqlsLogisticsLog+"\n");
			}
		}
	}
	
	/**
	 * 更新日志
	 * 	@User liud
	 * 	@Date 2017年8月17日下午8:17:11
	 * 	@param hqlsLogisticsLog
	 */
	public ResponseEntity<RestModel<Integer>> insert(HqlsLogisticsLog hqlsLogisticsLog){
		try {
			logisticsLogMapper.insert(hqlsLogisticsLog);
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"更新日志异常");
		}
		return RestModel.success();
	}
	
	
}