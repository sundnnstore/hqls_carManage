package com.sinoauto.service;
import java.util.List;
import javax.servlet.http.HttpServletResponse;
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
	public void findLogisticsLogs(Integer purchaseOrderId,HttpServletResponse response){
		try {
			List<HqlsLogisticsLog> hqlsLogisticsLogs =logisticsLogMapper.findLogisticsLogs(purchaseOrderId);
			String logHtml = "<html>";
			if(hqlsLogisticsLogs!=null){
				for (HqlsLogisticsLog hqlsLogisticsLog : hqlsLogisticsLogs) {
					logHtml+="<div><hr>"+System.currentTimeMillis()+"</hr>";
					logHtml+=""+hqlsLogisticsLog.getRemark()+"";
					logHtml+="</div><br>";
				}
				logHtml+="</html>";
			}
			  response.getWriter().write(logHtml.toString());//直接将完整的表单html输出到页面 
			  response.getWriter().flush(); 
			  response.getWriter().close();
		} catch (Exception e) {
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