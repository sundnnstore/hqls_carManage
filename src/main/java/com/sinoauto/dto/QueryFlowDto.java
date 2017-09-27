package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class QueryFlowDto {

	@ApiModelProperty("服务项目名称")
	private String serviceTypeName;
	@ApiModelProperty("订单金额")
	private Double orderAmount;

	public String getServiceTypeName() {
		return serviceTypeName;
	}

	public void setServiceTypeName(String serviceTypeName) {
		if (serviceTypeName != null) {
			this.serviceTypeName = serviceTypeName;
		}
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		if (orderAmount != null) {
			this.orderAmount = orderAmount;
		}
	}

}
