package com.sinoauto.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsFinanceFlow;
import com.sinoauto.dao.bean.HqlsStoreFinance;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dto.FlowDetailDto;
import com.sinoauto.dto.FlowDto;
import com.sinoauto.dto.FlowListDto;
import com.sinoauto.dto.RechargeDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class FinanceFlowService {

	@Autowired
	private FinanceFlowMapper financeFlowMapper;

	@Autowired
	private StoreFinanceMapper storeFinanceMapper;

	@Autowired
	private CashBackService cashBackService;

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

	@Transactional
	public ResponseEntity<RestModel<Integer>> insertFlow(Integer storeId, Double changeMoney, String accountName, String account, String bank,
			String openBank) {

		try {
			HqlsFinanceFlow flow = new HqlsFinanceFlow();
			flow.setStoreId(storeId);
			flow.setTransactionNo(generateNo("TX", storeId));
			flow.setChangeType(2);// 提现
			flow.setChangeMoney(changeMoney);
			flow.setChargeType(2);// 支出
			flow.setFlowStatus(2);
			flow.setCheckStatus(1);
			flow.setOperPerson("");
			flow.setCreateTime(new Date());
			flow.setRemark("remark");
			flow.setDmlTime(new Date());
			flow.setAccountName(accountName);
			flow.setAccount(account);
			flow.setBank(bank);
			flow.setOpenBank("");
			flow.setIsDelete(0);
			flow.setOpenBank(openBank);
			return RestModel.success(financeFlowMapper.insert(flow));
		} catch (Exception e) {
			System.out.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "新增流水记录失败");
	}

	@Transactional
	public ResponseEntity<RestModel<Integer>> insertRechargeFlow(Integer storeId, Double changeMoney, Integer payType, String transactionNo) {

		try {
			HqlsFinanceFlow flow = new HqlsFinanceFlow();
			flow.setStoreId(storeId);
			flow.setTransactionNo(transactionNo);
			flow.setChangeType(payType);// 充值
			flow.setChangeMoney(changeMoney);
			flow.setChargeType(1);// 收入
			if (3 == payType) {
				flow.setChargeType(2);
			}
			flow.setFlowStatus(2);
			flow.setCheckStatus(1);
			flow.setOperPerson("");
			flow.setPayType(1);// 支付宝
			flow.setCreateTime(new Date());
			flow.setRemark("remark");
			flow.setDmlTime(new Date());
			flow.setIsDelete(0);
			return RestModel.success(financeFlowMapper.insert(flow));
		} catch (Exception e) {
			System.out.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "添加提现流水失败");
	}

	@Transactional
	public ResponseEntity<RestModel<Integer>> insertRemitFlow(Integer storeId, Double changeMoney, String transactionNo) {

		try {
			HqlsFinanceFlow flow = new HqlsFinanceFlow();
			flow.setStoreId(storeId);
			flow.setTransactionNo(transactionNo);
			flow.setChangeType(1);// 充值
			flow.setChangeMoney(changeMoney);
			flow.setChargeType(1);// 收入
			flow.setPayType(3);
			flow.setFlowStatus(2);
			flow.setCheckStatus(1);
			flow.setOperPerson("");
			flow.setCreateTime(new Date());
			flow.setRemark("线下汇款");
			flow.setDmlTime(new Date());
			flow.setIsDelete(0);
			return RestModel.success(financeFlowMapper.insert(flow));
		} catch (Exception e) {
			System.out.println(e);
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "新增汇款记录失败");
	}

	private String generateNo(String business, Integer storeId) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddhhmmssSSS");
		String time = sdf.format(new Date());
		return String.format("%s%d%s", business, storeId, time);
	}

	public ResponseEntity<RestModel<FlowListDto>> findFlowByStoreId(Integer storeId, Integer pageIndex, Integer pageSize) {
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<HqlsFinanceFlow> orginalList = this.financeFlowMapper.findFlowList(storeId);
		List<FlowDto> flowDtoList = new ArrayList<>();
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
		for (HqlsFinanceFlow orginal : orginalList) {
			FlowDto flowDto = new FlowDto();
			flowDto.setFlowStatus(orginal.getFlowStatus());
			flowDto.setDate(dateSdf.format(orginal.getCreateTime()));
			flowDto.setTime(timeSdf.format(orginal.getCreateTime()));
			flowDto.setFinanceFlowId(orginal.getFinanceFlowId());
			// 收入
			if (orginal.getChargeType() == 1) {
				flowDto.setMoney("+" + orginal.getChangeMoney());
			}
			// 支出
			if (orginal.getChargeType() == 2) {
				flowDto.setMoney("-" + orginal.getChangeMoney());
			}

			if (orginal.getChangeType() == 1) {
				flowDto.setContent("充值");
			} else if (orginal.getChangeType() == 2) {
				flowDto.setContent("提现服务");
			} else if (orginal.getChangeType() == 3) {
				flowDto.setContent("采购");
			} else if (orginal.getChangeType() == 4) {
				flowDto.setContent("服务订单");
			} else {
				flowDto.setContent("未知流水");
			}
			flowDtoList.add(flowDto);
		}
		FlowListDto flowListDto = new FlowListDto();
		flowListDto.setFlowList(flowDtoList);
		HqlsStoreFinance storeFinance = this.storeFinanceMapper.findStoreFinance(storeId);
		if (storeFinance == null) {
			flowListDto.setBalance(0.0);
			flowListDto.setCashAble(0.0);
		} else {
			flowListDto.setBalance(storeFinance.getBalance() == null ? 0.0 : storeFinance.getBalance());
			flowListDto.setCashAble(storeFinance.getCashAble() == null ? 0.0 : storeFinance.getCashAble());
		}
		return RestModel.success(flowListDto, (int) orginalList.getTotal());
	}

	public ResponseEntity<RestModel<FlowDetailDto>> findFlowById(Integer financeFlowId) {
		HqlsFinanceFlow hqlsFlow = this.financeFlowMapper.findFlow(financeFlowId);
		if (null == hqlsFlow) {
			return RestModel.success(null);
		}
		FlowDetailDto flowDto = new FlowDetailDto();
		flowDto.setFlowStatus(hqlsFlow.getFlowStatus());
		flowDto.setFlowType(hqlsFlow.getChangeType());
		String flowStatusDesc = "失败";
		if (1 == hqlsFlow.getFlowStatus()) {
			flowStatusDesc = "成功";
		}
		if (hqlsFlow.getChangeType() == 1) {
			flowDto.setFlowTypeDesc("充值".concat(flowStatusDesc));
		} else if (hqlsFlow.getChangeType() == 2) {
			flowDto.setFlowTypeDesc("提现".concat(flowStatusDesc));
		} else if (hqlsFlow.getChangeType() == 3) {
			flowDto.setFlowTypeDesc("采购交易".concat(flowStatusDesc));
		} else if (hqlsFlow.getChangeType() == 4) {
			flowDto.setFlowTypeDesc("服务订单交易".concat(flowStatusDesc));
		} else {
			flowDto.setFlowTypeDesc("未知");
		}
		// 收入
		if (hqlsFlow.getChargeType() == 1) {
			flowDto.setMoney("+" + hqlsFlow.getChangeMoney());
		}
		// 支出
		if (hqlsFlow.getChargeType() == 2) {
			flowDto.setMoney("-" + hqlsFlow.getChangeMoney());
		}
		flowDto.setOrderNo(hqlsFlow.getOrderNo());

		if (hqlsFlow.getPayType() == null) {
			flowDto.setPayType("暂无说明");
		} else if (hqlsFlow.getPayType() == 1) {
			flowDto.setPayType("支付宝");
		} else if (hqlsFlow.getPayType() == 2) {
			flowDto.setPayType("微信");
		} else if (hqlsFlow.getPayType() == 3) {
			flowDto.setPayType("线下");
		} else {
			flowDto.setPayType("未知");
		}
		flowDto.setPayNo(hqlsFlow.getTransactionNo());

		return RestModel.success(flowDto);
	}

	public Integer updateFlowStatus(String transactionNo, Integer flowStatus) {
		try {
			return this.financeFlowMapper.updateFlowStatus(transactionNo, flowStatus);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	public Integer updateBalance(Double changeMoney, String transactionNo) {
		Double backMoney = this.cashBackService.calcBackMoney(changeMoney);
		Integer storeId = this.financeFlowMapper.getStoreIdByTransactionNo(transactionNo);
		return this.storeFinanceMapper.updateMoney(changeMoney + backMoney, backMoney, changeMoney, storeId);
	}

	public HqlsFinanceFlow findFlowByTransactionNo(String transactionNo) {
		HqlsFinanceFlow hqlsFlow = this.financeFlowMapper.getFlowByTransactionNo(transactionNo);
		return hqlsFlow;
	}

}