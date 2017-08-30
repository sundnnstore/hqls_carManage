package com.sinoauto.service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsCashBack;
import com.sinoauto.dao.mapper.CashBackMapper;
import com.sinoauto.dto.CashBackDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class CashBackService {

	@Autowired
	private CashBackMapper cashBackMapper;

	@Transactional
	public ResponseEntity<RestModel<Integer>> insertCashBack(HqlsCashBack cashBack) {
		try {
			cashBack.setCreateTime(new Date());
			cashBack.setIsUsable(1);
			cashBack.setOperateUserId(1);
			return RestModel.success(cashBackMapper.insert(cashBack));
		} catch (Exception e) {
			System.out.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "新增返利规则失败");
	}

	public ResponseEntity<RestModel<Page<CashBackDto>>> findFlowListByContidion(Integer returnType, String operateUserName, Date createTime,
			Integer pageIndex, Integer pageSize) {
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		if (operateUserName != null) {
			operateUserName = operateUserName.trim();
		}
		String createTimeStr = "";
		if (createTime != null) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			createTimeStr = sdf.format(createTime);
		}

		Page<CashBackDto> cashBackList = cashBackMapper.findCashBackList(returnType, operateUserName, createTimeStr);
		return RestModel.success(cashBackList, (int) cashBackList.getTotal());
	}

	public ResponseEntity<RestModel<Double>> getCashBackByMoney(Double money) {
		return RestModel.success(new BigDecimal(this.calcBackMoney(money)).setScale(2, RoundingMode.HALF_UP).doubleValue());
	}

	public Double calcBackMoney(Double money) {
		HqlsCashBack cashBack = this.cashBackMapper.getCashBackByMoney(money);
		Double backMoney = 0.00;
		if (null == cashBack) {
			return 0.00;
		}
		new BigDecimal(backMoney).setScale(2, RoundingMode.UP);
		if (1 == cashBack.getReturnType()) {
			backMoney = new BigDecimal(money).multiply(new BigDecimal(cashBack.getDiscount())).doubleValue();
		} else if (2 == cashBack.getReturnType()) {
			backMoney = cashBack.getReturnMoney();
		} else {
			return null;
		}
		return new BigDecimal(backMoney).setScale(2, RoundingMode.HALF_UP).doubleValue();
	}

}