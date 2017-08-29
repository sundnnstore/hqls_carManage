package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * @author fujl
 * @version 1.0
 * @date 2017-08-23 00:01:19
 */

public class FlowDetailDto {
	@ApiModelProperty("流水类型")
	private String flowType;

	@ApiModelProperty("金额")
	private String money;

	@ApiModelProperty("付款方式")
	private String payType;

	@ApiModelProperty("支付说明")
	private String payDesc;

	@ApiModelProperty("商品订单号")
	private String orderNo;

	@ApiModelProperty("支付订单号")
	private String payNo;

	public String getFlowType() {
		return flowType;
	}

	public void setFlowType(String flowType) {
		if (flowType != null) {
			this.flowType = flowType;
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

	public String getPayType() {
		return payType;
	}

	public void setPayType(String payType) {
		if (payType != null) {
			this.payType = payType;
		}
	}

	public String getPayDesc() {
		return payDesc;
	}

	public void setPayDesc(String payDesc) {
		if (payDesc != null) {
			this.payDesc = payDesc;
		}
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		if (orderNo != null) {
			this.orderNo = orderNo;
		}
	}

	public String getPayNo() {
		return payNo;
	}

	public void setPayNo(String payNo) {
		if (payNo != null) {
			this.payNo = payNo;
		}
	}

}
