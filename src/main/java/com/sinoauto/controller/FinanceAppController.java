package com.sinoauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.FinanceLogDto;
import com.sinoauto.dto.FlowDetailDto;
import com.sinoauto.dto.FlowListDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.FinanceFlowService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author fujl
 * @version 1.0
 * @date 2017-08-22 15:55:18
 */

@Api(tags = "财务管理（APP）")
@RestController
public class FinanceAppController {

	@Autowired
	private FinanceFlowService financeFlowService;

	// @Autowired
	// private StoreFinanceService storeFinanceService;
	//
	// @Autowired
	// private CashBackService cashBackService;
	//
	// @InitBinder
	// public void initBinder(WebDataBinder binder) {
	// SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	// dateFormat.setLenient(false);
	// binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	// }
	//
	// @GetMapping("findflowlistbycontidion")
	// @ApiOperation(value = "根据条件查询流水", notes = "fujl")
	// @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "changeType", value = "金额变动类型（1充值；2提现；3采购；4汽车维护服务）", dataType = "Integer"),
	// @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
	// @ApiImplicitParam(paramType = "query", name = "customerName", value = "门店联系人", dataType = "String"),
	// @ApiImplicitParam(paramType = "query", name = "mobile", value = "联系人电话", dataType = "String"),
	// @ApiImplicitParam(paramType = "query", name = "createTime", value = "充值时间", dataType = "Date"),
	// @ApiImplicitParam(paramType = "query", name = "flowStatus", value = "流水状态(1成功；2失败)", dataType = "Integer"),
	// @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
	// @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	// public ResponseEntity<RestModel<List<RechargeDto>>> findFlowListByContidion(Integer changeType, Integer storeId, String customerName,
	// String mobile, Date createTime, Integer flowStatus, Integer pageIndex, Integer pageSize) {
	// return financeFlowService.findFlowListByContidion(changeType, storeId, customerName, mobile, createTime, flowStatus, pageIndex, pageSize);
	// }
	//
	// @GetMapping("findstorefinancelist")
	// @ApiOperation(value = "根据条件查询账户", notes = "fujl")
	// @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
	// @ApiImplicitParam(paramType = "query", name = "customerName", value = "门店联系人", dataType = "String"),
	// @ApiImplicitParam(paramType = "query", name = "mobile", value = "联系人电话", dataType = "String"),
	// @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
	// @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	// public ResponseEntity<RestModel<List<StoreFinanceDto>>> findStoreFinanceList(Integer storeId, String customerName, String mobile,
	// Integer pageIndex, Integer pageSize) {
	// return storeFinanceService.findFlowListByContidion(storeId, customerName, mobile, pageIndex, pageSize);
	// }
	//
	// @GetMapping("findcashbacklist")
	// @ApiOperation(value = "根据条件查询返利", notes = "fujl")
	// @ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "returnType", value = "返还类型（1-返利率, 2-返还金额）", dataType = "Integer"),
	// @ApiImplicitParam(paramType = "query", name = "operateUserName", value = "操作人", dataType = "String"),
	// @ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", dataType = "Date"),
	// @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
	// @ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	// public ResponseEntity<RestModel<Page<CashBackDto>>> findCashBackListByContidion(Integer returnType, String operateUserName, Date createTime,
	// Integer pageIndex, Integer pageSize) {
	// return cashBackService.findFlowListByContidion(returnType, operateUserName, createTime, pageIndex, pageSize);
	// }
	//
	// @ApiOperation(value = "新增返利规则", notes = "fujl")
	// @PostMapping("addcashback")
	// public ResponseEntity<RestModel<Integer>> addCashBack(@RequestBody HqlsCashBack cashBack) {
	// return this.cashBackService.insertCashBack(cashBack);
	// }

	@GetMapping("flow")
	@ApiOperation(value = "账单流水", notes = "fujl")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	public ResponseEntity<RestModel<FlowListDto>> flow(Integer storeId, Integer pageIndex, Integer pageSize) {
		return this.financeFlowService.findFlowByStoreId(storeId, pageIndex, pageSize);
	}

	@GetMapping("detail")
	@ApiOperation(value = "账单详情", notes = "fujl")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "financeFlowId", value = "流水ID", dataType = "Integer") })
	public ResponseEntity<RestModel<FlowDetailDto>> detail(Integer financeFlowId) {
		return this.financeFlowService.findFlowById(financeFlowId);
	}

	@ApiOperation(value = "APP提现", notes = "fujl")
	@PostMapping("withdraw")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "changeMoney", value = "提现金额", dataType = "Double"),
			@ApiImplicitParam(paramType = "query", name = "accountName", value = "户名", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "account", value = "账号", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "bank", value = "银行", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "openBank", value = "开户行", dataType = "String") })
	public ResponseEntity<RestModel<Integer>> withdraw(Integer storeId, Double changeMoney, String accountName, String account, String bank,
			String openBank) {
		this.financeFlowService.updateBalance(changeMoney, storeId);
		return this.financeFlowService.insertFlow(storeId, changeMoney, accountName, account, bank, openBank);
	}

	@ApiOperation(value = "汇款审核", notes = "fujl")
	@PostMapping("audit")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "changeMoney", value = "汇款金额", dataType = "Double"),
			@ApiImplicitParam(paramType = "query", name = "transactionNo", value = "汇款流水单号", dataType = "String") })
	public ResponseEntity<RestModel<Integer>> audit(Integer storeId, Double changeMoney, String transactionNo) {
		if (null != this.financeFlowService.findFlowByTransactionNo(transactionNo)) {
			return RestModel.error(HttpStatus.NOT_ACCEPTABLE, ErrorStatus.EXISTS_DATA, "该汇款单号已存在！");
		}
		return this.financeFlowService.insertRemitFlow(storeId, changeMoney, transactionNo);
	}

	@ApiOperation(value = "查询日流水", notes = "tangwt")
	@PostMapping("dailyflow")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "queryDate", value = "查询日期", dataType = "String") })
	public ResponseEntity<RestModel<FinanceLogDto>> dailyFlow(Integer storeId, String queryDate) {
		return this.financeFlowService.dailyFlow(storeId, queryDate);
	}

	@ApiOperation(value = "查询多日流水", notes = "tangwt")
	@PostMapping("nearflow")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "days", value = "查询天数", dataType = "Integer") })
	public ResponseEntity<RestModel<FinanceLogDto>> nearFlow(Integer storeId, Integer days) {
		return this.financeFlowService.nearFlow(storeId, days);
	}

}
