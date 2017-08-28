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

	@ApiModelProperty("流水状态")
	private String flowStatus;

	@ApiModelProperty("付款方式")
	private String payType;

	@ApiModelProperty("商品说明")
	private String productDesc;

	@ApiModelProperty("商品订单号")
	private String orderNo;

	@ApiModelProperty("支付单号")
	private String transactionNo;

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

	public String getFlowStatus() {
		return flowStatus;
	}

	public void setFlowStatus(String flowStatus) {
		if (flowStatus != null) {
			this.flowStatus = flowStatus;
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

	public String getProductDesc() {
		return productDesc;
	}

	public void setProductDesc(String productDesc) {
		if (productDesc != null) {
			this.productDesc = productDesc;
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

	public String getTransactionNo() {
		return transactionNo;
	}

	public void setTransactionNo(String transactionNo) {
		if (transactionNo != null) {
			this.transactionNo = transactionNo;
		}
	}

}
