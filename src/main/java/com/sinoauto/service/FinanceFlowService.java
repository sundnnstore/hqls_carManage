package com.sinoauto.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dto.RechargeDto;
import com.sinoauto.entity.RestModel;

@Service
public class FinanceFlowService {

	@Autowired
	private FinanceFlowMapper financeFlowMapper;

	public ResponseEntity<RestModel<List<RechargeDto>>> findFlowListByContidion(Integer changeType, Integer storeId, String customerName,
			String mobile, Date createTime, Integer flowStatus, Integer pageIndex, Integer pageSize) {
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		if (customerName != null) {
			customerName = customerName.trim();
		}
		if (mobile != null) {
			mobile = mobile.trim();
		}
		String createTimeStr = "";
		if (createTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			createTimeStr = sdf.format(createTime);
		}
		Page<RechargeDto> flowList = financeFlowMapper.findFlowListByContidion(changeType, storeId, customerName, mobile, createTimeStr, flowStatus);

		return RestModel.success(flowList, (int) flowList.getTotal());
	}
}