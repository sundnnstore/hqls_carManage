package com.sinoauto.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsCashBack;
import com.sinoauto.dto.CashBackDto;
import com.sinoauto.dto.RechargeDto;
import com.sinoauto.dto.StoreFinanceDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.CashBackService;
import com.sinoauto.service.FinanceFlowService;
import com.sinoauto.service.StoreFinanceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

/**
 * @author fujl
 * @version 1.0
 * @date 2017-08-22 15:55:18
 */

@Api(tags = "财务管理")
@RestController
public class FinanceController {

	@Autowired
	private FinanceFlowService financeFlowService;

	@Autowired
	private StoreFinanceService storeFinanceService;

	@Autowired
	private CashBackService cashBackService;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));
	}

	@GetMapping("findflowlistbycontidion")
	@ApiOperation(value = "根据条件查询流水", notes = "fujl")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "changeType", value = "金额变动类型（1充值；2提现；3采购；4汽车维护服务）", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "customerName", value = "门店联系人", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "mobile", value = "联系人电话", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "充值时间", dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "flowStatus", value = "流水状态(1成功；2失败)", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	public ResponseEntity<RestModel<List<RechargeDto>>> findFlowListByContidion(Integer changeType, Integer storeId, String customerName,
			String mobile, Date createTime, Integer flowStatus, Integer pageIndex, Integer pageSize) {
		return financeFlowService.findFlowListByContidion(changeType, storeId, customerName, mobile, createTime, flowStatus, pageIndex, pageSize);
	}

	@GetMapping("findstorefinancelist")
	@ApiOperation(value = "根据条件查询账户", notes = "fujl")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "storeId", value = "门店ID", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "customerName", value = "门店联系人", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "mobile", value = "联系人电话", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	public ResponseEntity<RestModel<List<StoreFinanceDto>>> findStoreFinanceList(Integer storeId, String customerName, String mobile,
			Integer pageIndex, Integer pageSize) {
		return storeFinanceService.findFlowListByContidion(storeId, customerName, mobile, pageIndex, pageSize);
	}

	@GetMapping("findcashbacklist")
	@ApiOperation(value = "根据条件查询返利", notes = "fujl")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "returnType", value = "返还类型（1-返利率, 2-返还金额）", dataType = "Integer"),
			@ApiImplicitParam(paramType = "query", name = "operateUserName", value = "操作人", dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "createTime", value = "创建时间", dataType = "Date"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页面索引", dataType = "Integer", required = true),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "页面大小", dataType = "Integer", required = true) })
	public ResponseEntity<RestModel<Page<CashBackDto>>> findCashBackListByContidion(Integer returnType, String operateUserName, Date createTime,
			Integer pageIndex, Integer pageSize) {
		return cashBackService.findFlowListByContidion(returnType, operateUserName, createTime, pageIndex, pageSize);
	}

	@ApiOperation(value = "新增返利规则", notes = "fujl")
	@PostMapping("addcashback")
	public ResponseEntity<RestModel<Integer>> addCashBack(@RequestBody HqlsCashBack cashBack) {
		return this.cashBackService.insertCashBack(cashBack);
	}

	@ApiOperation(value = "根据充值金额获取返利金额", notes = "fujl")
	@GetMapping("backmoney")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "money", value = "充值金额", dataType = "Double") })
	public ResponseEntity<RestModel<Double>> getBackMoney(Double money) {
		return this.cashBackService.getCashBackByMoney(money);
	}

}
