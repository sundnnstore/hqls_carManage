package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author fujl
 * @version 1.0
 * @date 2017-08-23 00:01:19
 */

public class FlowDto {

	@ApiModelProperty("流水ID")
	private Integer financeFlowId;
	@ApiModelProperty("日期")
	private String date;
	@ApiModelProperty("时间")
	private String time;
	@ApiModelProperty("内容")
	private String content;
	@ApiModelProperty("金额")
	private String money;
	@ApiModelProperty("交易状态：1-成功，2-失败")
	private Integer flowStatus;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		if (date != null) {
			this.date = date;
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		if (time != null) {
			this.time = time;
		}
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		if (content != null) {
			this.content = content;
		}
	}

	public String getMoney() {
		return money;
	}

	public void setMoney(String money) {
		if (money != null) {
			this.money = money;
		}
	}

	public Integer getFinanceFlowId() {
		return financeFlowId;
	}

	public void setFinanceFlowId(Integer financeFlowId) {
		if (financeFlowId != null) {
			this.financeFlowId = financeFlowId;
		}
	}

	public Integer getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(Integer flowStatus) {
		if (flowStatus != null) {
			this.flowStatus = flowStatus;
		}
	}

}
