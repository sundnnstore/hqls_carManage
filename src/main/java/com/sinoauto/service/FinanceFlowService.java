package com.sinoauto.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
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
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.ClientInfoMapper;
import com.sinoauto.dao.mapper.FinanceFlowMapper;
import com.sinoauto.dao.mapper.RebateMapper;
import com.sinoauto.dao.mapper.StoreFinanceMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.FinanceDetailDto;
import com.sinoauto.dto.FinanceLogDto;
import com.sinoauto.dto.FlowDetailDto;
import com.sinoauto.dto.FlowDto;
import com.sinoauto.dto.FlowListDto;
import com.sinoauto.dto.RechargeDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.util.DateUtil;
import com.sinoauto.util.push.GeTuiUtil;
import com.sinoauto.util.push.PushAction;
import com.sinoauto.util.push.PushParms;
import com.sinoauto.util.push.PushUtil;

@Service
public class FinanceFlowService {

	@Autowired
	private FinanceFlowMapper financeFlowMapper;

	@Autowired
	private StoreFinanceMapper storeFinanceMapper;

	@Autowired
	private RebateMapper rebateMapper;

	@Autowired
	private UserMapper userMapper;

	@Autowired
	private ClientInfoMapper clientInfoMapper;

	// @Autowired
	// private CashBackService cashBackService;

	public ResponseEntity<RestModel<List<RechargeDto>>> findFlowListByContidion(Integer changeType, Integer storeId, String customerName,
			String operPerson, String mobile, Date createTime, Integer flowStatus, Integer checkStatus, Integer payType, Integer pageIndex,
			Integer pageSize) {
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
		Page<RechargeDto> flowList = financeFlowMapper.findFlowListByContidion(changeType, storeId, customerName, operPerson, mobile, createTimeStr,
				flowStatus, checkStatus, payType);

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
			flow.setRemark("线上充值");
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
			} else if (orginal.getChangeType() == 5) {
				flowDto.setContent("消费返现");
			} else {
				flowDto.setContent("未知流水");
			}
			flowDtoList.add(flowDto);
		}
		FlowListDto flowListDto = new FlowListDto();
		flowListDto.setFlowList(flowDtoList);
		HqlsStoreFinance storeFinance = this.storeFinanceMapper.findStoreFinance(storeId);

		Double currentHaveReturned = this.rebateMapper.getCurrentReturnedMoney(storeId);
		Double sumReturned = this.rebateMapper.getHaveReturned(storeId);
		Double sumRemainingReturned = this.rebateMapper.getRemainingReturned(storeId);

		flowListDto.setCurrentReturned(currentHaveReturned == null ? 0.0 : currentHaveReturned);
		flowListDto.setSumReturned(sumReturned == null ? 0.0 : sumReturned);
		flowListDto.setRemainingReturned(sumRemainingReturned == null ? 0.0 : sumRemainingReturned);

		if (storeFinance == null) {
			flowListDto.setBalance(0.0);
		} else {
			flowListDto.setBalance(storeFinance.getBalance() == null ? 0.0 : storeFinance.getBalance());
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
			if (1 == hqlsFlow.getCheckStatus()) {
				flowStatusDesc = "待审核";
			} else if (2 == hqlsFlow.getCheckStatus()) {
				flowStatusDesc = "审核通过";
			} else if (2 == hqlsFlow.getCheckStatus()) {
				flowStatusDesc = "审核不通过";
			}
			flowDto.setFlowTypeDesc("提现".concat(flowStatusDesc));
		} else if (hqlsFlow.getChangeType() == 3) {
			flowDto.setFlowTypeDesc("采购交易".concat(flowStatusDesc));
		} else if (hqlsFlow.getChangeType() == 4) {
			flowDto.setFlowTypeDesc("服务订单交易".concat(flowStatusDesc));
		} else if (hqlsFlow.getChangeType() == 5) {
			flowDto.setFlowTypeDesc("消费返现".concat(flowStatusDesc));
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
		} else if (hqlsFlow.getPayType() == 0) {
			flowDto.setPayType("余额支付");
		} else {
			flowDto.setPayType("未知");
		}

		if (StringUtils.isNotBlank(hqlsFlow.getRemark())) {
			flowDto.setPayDesc(hqlsFlow.getRemark());
		} else {
			flowDto.setPayDesc("");
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

	/**
	 * 
	 * @param financeFlowId
	 * @param changeType 1充值，2提现
	 * @param checkStatus
	 * @param remark
	 * @return
	 */
	public ResponseEntity<RestModel<Integer>> updateCheckStatus(Integer financeFlowId, Integer changeType, Integer checkStatus, String remark,
			String operateUserName) {
		try {
			Integer affectRows = this.financeFlowMapper.updateCheckStatus(financeFlowId, checkStatus, remark, operateUserName);
			// 提现审核失败，返还给账户
			if (changeType == 2 && 3 == checkStatus) {
				HqlsFinanceFlow flow = this.financeFlowMapper.findFlow(financeFlowId);
				this.storeFinanceMapper.updateMoney(flow.getChangeMoney(), flow.getChangeMoney(), 0.0, flow.getStoreId());
			}
			// 线下充值审核通过，增加余额
			else if (changeType == 1 && 2 == checkStatus) {

				HqlsFinanceFlow flow = this.financeFlowMapper.findFlow(financeFlowId);
				this.updateBalance(flow.getChangeMoney(), flow.getTransactionNo());
				this.updateFlowStatus(flow.getTransactionNo(), 1);
				// 推送消息给B端
				HqlsUser user = userMapper.getUserByStoreId(flow.getStoreId());
				if (user != null) {
					PushAction pa = new PushAction("Finance", 0, true, "");
					List<PushAction> action = new ArrayList<>();
					action.add(pa);
					String text = "您有一笔线下充值通过审核";
					// 推送给IOSAPP端
					PushParms parms = PushUtil.comboPushParms(user.getMobile(), action, null, text, "", null, 0);
					PushUtil.push2IOSByAPNS(parms);
					// PushUtil.push2Andriod(parms);
					PushUtil.push2AndriodNotice(parms);
					String title = "线下充值";
					List<String> clientIds = clientInfoMapper.findAllCIdsByUserId(user.getUserId());
					// 推送给安卓APP端
					GeTuiUtil.pushToAndroid(clientIds, title, text, "finance", "线下充值");
				}
			}

			return RestModel.success(affectRows);
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "更新审核状态失败");
		}
	}

	public Integer updateBalance(Double changeMoney, String transactionNo) {
		/// Double backMoney = this.cashBackService.calcBackMoney(changeMoney);
		Integer storeId = this.financeFlowMapper.getStoreIdByTransactionNo(transactionNo);
		return this.storeFinanceMapper.updateMoney(changeMoney, changeMoney, 0.0, storeId);
	}

	public Integer updateBalance(Double changeMoney, Integer storeId) {
		return this.storeFinanceMapper.updateMoney(-changeMoney, -changeMoney, 0.0, storeId);
	}

	public HqlsFinanceFlow findFlowByTransactionNo(String transactionNo) {
		HqlsFinanceFlow hqlsFlow = this.financeFlowMapper.getFlowByTransactionNo(transactionNo);
		return hqlsFlow;
	}

	public ResponseEntity<RestModel<FinanceLogDto>> dailyFlow(Integer storeId, String queryDate) {
		List<HqlsFinanceFlow> finances = financeFlowMapper.findDailyFlowByStoreIdAndDate(storeId, queryDate);
		FinanceLogDto finance = new FinanceLogDto();
		if (finances != null && finances.size() > 0) {
			return RestModel.success(comboFlowDto(finances));
		}
		finance.setTotalExpenditure(0.0);
		finance.setTotalIncome(0.0);
		finance.setFlowList(new ArrayList<>());
		return RestModel.success(finance);
	}

	public FinanceLogDto comboFlowDto(List<HqlsFinanceFlow> finances) {
		FinanceLogDto finance = new FinanceLogDto();
		BigDecimal expenditure = new BigDecimal(0);
		BigDecimal income = new BigDecimal(0);
		List<FlowDto> flowList = new ArrayList<>();
		SimpleDateFormat dateSdf = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat timeSdf = new SimpleDateFormat("HH:mm");
		for (HqlsFinanceFlow orginal : finances) {
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
				expenditure = expenditure.add(new BigDecimal(orginal.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else if (orginal.getChangeType() == 4) {
				flowDto.setContent("服务订单");
				income = income.add(new BigDecimal(orginal.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else if (orginal.getChangeType() == 5) {
				flowDto.setContent("消费返现");
				income = income.add(new BigDecimal(orginal.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
			} else {
				flowDto.setContent("未知流水");
			}
			flowList.add(flowDto);
		}
		finance.setFlowList(flowList);
		finance.setTotalExpenditure(expenditure.doubleValue());
		finance.setTotalIncome(income.doubleValue());
		return finance;
	}

	public ResponseEntity<RestModel<FinanceLogDto>> nearFlow(Integer storeId, Integer days) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		List<HqlsFinanceFlow> finances = financeFlowMapper.findNearDaysFlows(storeId, days);
		BigDecimal totalExpenditure = new BigDecimal(0);
		BigDecimal totalIncome = new BigDecimal(0);
		List<FinanceDetailDto> financeDetails = new ArrayList<>();
		Map<String, BigDecimal> map = new HashMap<>();
		List<String> dayDesc = DateUtil.getDateList(days);
		for (String day : dayDesc) {// 加入日期
			map.put(day, new BigDecimal(0));
		}
		if (finances != null && finances.size() > 0) {
			for (HqlsFinanceFlow fina : finances) {
				String d = sdf.format(fina.getCreateTime());
				BigDecimal curMoney = map.get(d);
				if (fina.getChangeType() == 3) {// 采购
					totalExpenditure = totalExpenditure.add(new BigDecimal(fina.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
				} else if (fina.getChangeType() == 4) {// 服务订单
					totalIncome = totalIncome.add(new BigDecimal(fina.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
					curMoney = curMoney.add(new BigDecimal(fina.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
				} else if (fina.getChangeType() == 5) {// 返现
					totalIncome = totalIncome.add(new BigDecimal(fina.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
					curMoney = curMoney.add(new BigDecimal(fina.getChangeMoney())).setScale(2, BigDecimal.ROUND_HALF_UP);
				}
				map.put(d, curMoney);
			}
		}
		for (String day : dayDesc) {
			FinanceDetailDto f = new FinanceDetailDto();
			f.setFinanceDate(day.split("-")[1]+"."+day.split("-")[2]);
			f.setTotalIncome(map.get(day).doubleValue());
			financeDetails.add(f);
		}
		FinanceLogDto returnFinance = new FinanceLogDto();
		returnFinance.setTotalExpenditure(totalExpenditure.doubleValue());
		returnFinance.setTotalIncome(totalIncome.doubleValue());
		returnFinance.setFinances(financeDetails);
		return RestModel.success(returnFinance);
	}

}