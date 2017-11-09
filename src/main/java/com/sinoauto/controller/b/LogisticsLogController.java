package com.sinoauto.controller.b;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.sinoauto.dao.bean.HqlsLogisticsLog;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.LogisticsLogService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "物流日志管理")
@RestController
public class LogisticsLogController {
	
	@Autowired
	private LogisticsLogService logisticsLogService;
	
	@GetMapping("findlogisticslog")
	@ApiOperation(value = "物流信息跟踪", notes = "liud")
	public ResponseEntity<RestModel<List<HqlsLogisticsLog>>> findLogisticsLogs(Integer purchaseOrderId){
		return logisticsLogService.findLogisticsLogs(purchaseOrderId);
	}
}
