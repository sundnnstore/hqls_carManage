package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 订单查询条件
 *@author liud
 *@Date2017年8月18日下午4:59:31
 *
 */
public class PurchaseOrderQueryDto {

	@ApiModelProperty("订单状态")
	private Integer orderStatus;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("联系人姓名")
	private String userName;
	@ApiModelProperty("联系人电话")
	private String mobile;
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
}
