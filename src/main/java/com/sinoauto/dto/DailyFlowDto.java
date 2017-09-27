package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class DailyFlowDto {

	@ApiModelProperty("服务次数")
	private Integer serviceCount;
	@ApiModelProperty("合计金额")
	private Double amount;
	@ApiModelProperty("服务项目描述")
	private String serviceTypeName;

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

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		if (serviceTypeName != null) {
			this.serviceTypeName = serviceTypeName;
		}
	}

}
