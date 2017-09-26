package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class DailyFlowDto {

	@ApiModelProperty("服务次数")
	private Integer serviceCount;
	@ApiModelProperty("合计金额")
	private Double amount;

	public Integer getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(Integer serviceCount) {
		if (serviceCount != null) {
			this.serviceCount = serviceCount;
		}
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		if (amount != null) {
			this.amount = amount;
		}
	}

}
