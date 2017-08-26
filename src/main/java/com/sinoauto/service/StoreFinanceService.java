package com.sinoauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dto.StoreFinanceDto;
import com.sinoauto.entity.RestModel;

@Service
public class StoreFinanceService {

	@Autowired
	private StoreFinanceMapper storeFinanceMapper;

	public ResponseEntity<RestModel<List<StoreFinanceDto>>> findFlowListByContidion(Integer storeId, String customerName, String mobile,
			Integer pageIndex, Integer pageSize) {
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		if (customerName != null) {
			customerName = customerName.trim();
		}
		if (mobile != null) {
			mobile = mobile.trim();
		}
		Page<StoreFinanceDto> flowList = storeFinanceMapper.findStoreFinanceistByContidion(storeId, customerName, mobile);

		return RestModel.success(flowList, (int) flowList.getTotal());
	}

}