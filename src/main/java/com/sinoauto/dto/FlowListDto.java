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
	private Double balance = 0.00;

	@ApiModelProperty("本月已为您返现")
	private Double currentReturned;

	@ApiModelProperty("累计已返现")
	private Double sumReturned;

	@ApiModelProperty("剩余返现额")
	private Double remainingReturned;

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

	public List<FlowDto> getFlowList() {
		return flowList;
	}

	public void setFlowList(List<FlowDto> flowList) {
		if (flowList != null) {
			this.flowList = flowList;
		}
	}

	public Double getCurrentReturned() {
		return currentReturned;
	}

	public void setCurrentReturned(Double currentReturned) {
		if (currentReturned != null) {
			this.currentReturned = currentReturned;
		}
	}

	public Double getSumReturned() {
		return sumReturned;
	}

	public void setSumReturned(Double sumReturned) {
		if (sumReturned != null) {
			this.sumReturned = sumReturned;
		}
	}

	public Double getRemainingReturned() {
		return remainingReturned;
	}

	public void setRemainingReturned(Double remainingReturned) {
		if (remainingReturned != null) {
			this.remainingReturned = remainingReturned;
		}
	}

}
