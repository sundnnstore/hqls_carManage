package com.sinoauto.dto;

/**
 * 支付操作返回数据类
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-31 11:43:26
 */
public class PayReturnParamDto {

	private Integer storeId;
	private String orderNo;
	private Double money;
	private Integer changeType;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		if (storeId != null) {
			this.storeId = storeId;
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

	public Double getMoney() {
		return money;
	}

	public void setMoney(Double money) {
		if (money != null) {
			this.money = money;
		}
	}

	public Integer getChangeType() {
		return changeType;
	}

	public void setChangeType(Integer changeType) {
		if (changeType != null) {
			this.changeType = changeType;
		}
	}

}
