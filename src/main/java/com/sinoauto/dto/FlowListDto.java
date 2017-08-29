package com.sinoauto.dto;

import java.util.ArrayList;
import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author fujl
 * @version 1.0
 * @date 2017-08-23 00:01:19
 */

public class FlowListDto {

	@ApiModelProperty("余额")
	private Double balance;

	@ApiModelProperty("可提现")
	private Double cashAble;

	@ApiModelProperty("流水ID")
	private List<FlowDto> flowList = new ArrayList<FlowDto>();

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		if (balance != null) {
			this.balance = balance;
		}
	}

	public Double getCashAble() {
		return cashAble;
	}

	public void setCashAble(Double cashAble) {
		if (cashAble != null) {
			this.cashAble = cashAble;
		}
	}

	public List<FlowDto> getFlowList() {
		return flowList;
	}

	public void setFlowList(List<FlowDto> flowList) {
		if (flowList != null) {
			this.flowList = flowList;
		}
	}

}
