package com.sinoauto.dto.c;

import io.swagger.annotations.ApiModelProperty;

public class LastProjectDto {

	@ApiModelProperty("服务项目ID")
	private Integer serviceProjectId;
	@ApiModelProperty("服务项目名称")
	private String serviceProjectName;
	@ApiModelProperty("服务项目金额")
	private Double amount;
	@ApiModelProperty("服务项目是否需要选中")
	private Boolean isChecked = false;

	public Integer getServiceProjectId() {
		return serviceProjectId;
	}

	public void setServiceProjectId(Integer serviceProjectId) {
		if (serviceProjectId != null) {
			this.serviceProjectId = serviceProjectId;
		}
	}

	public String getServiceProjectName() {
		return serviceProjectName;
	}

	public void setServiceProjectName(String serviceProjectName) {
		if (serviceProjectName != null) {
			this.serviceProjectName = serviceProjectName;
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

	public Boolean getIsChecked() {
		return isChecked;
	}

	public void setIsChecked(Boolean isChecked) {
		if (isChecked != null) {
			this.isChecked = isChecked;
		}
	}

}
