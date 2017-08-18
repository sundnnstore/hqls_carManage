package com.sinoauto.service;

import java.util.ArrayList;
import java.util.List;
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
	 * 	物流信息
	 * 	@User liud
	 * 	@Date 2017年8月17日下午8:19:57
	 */
	public ResponseEntity<RestModel<List<HqlsLogisticsLog>>> findLogisticsLogs(Integer purchaseOrderId) {
		List<HqlsLogisticsLog> hqlsLogisticsLogs =null;
		try {
			// String logHtml = "<html>";
			// logHtml+="<html>";
			// logHtml+="<head>";
			// logHtml+="<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\">";
			// logHtml+="<title>Insert title here</title>";
			// logHtml+="</head>";
			// logHtml+="<body>";
			// logHtml+="<a href=\"http://www.baidu.com\">Hello,World,My name is 李东</a>";
			// logHtml+="</body>";
			// logHtml+="</html>";
			// response.getWriter().write(logHtml.toString());//直接将完整的表单html输出到页面
			// response.getWriter().flush();
			// response.getWriter().close();
			hqlsLogisticsLogs = logisticsLogMapper.findLogisticsLogs(purchaseOrderId);
			if (hqlsLogisticsLogs == null) {
				hqlsLogisticsLogs = new ArrayList<>();
			}

		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "查看日志异常");
		}
		return RestModel.success(hqlsLogisticsLogs);

	}

	/**
	 * 更新日志
	 * 	@User liud
	 * 	@Date 2017年8月17日下午8:17:11
	 * 	@param hqlsLogisticsLog
	 */
	public ResponseEntity<RestModel<Integer>> insert(HqlsLogisticsLog hqlsLogisticsLog) {
		try {
			logisticsLogMapper.insert(hqlsLogisticsLog);
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "更新日志异常");
		}
		return RestModel.success();
	}

}