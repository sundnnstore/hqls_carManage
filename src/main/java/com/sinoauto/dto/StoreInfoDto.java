package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class StoreInfoDto {
	
	@ApiModelProperty(value="门店名称")
	private String storeName;
	
	@ApiModelProperty(value="联系人")
	private String userName;
	
	@ApiModelProperty("联系人号码")
	private String mobile;
	
	@ApiModelProperty(value="地址")
	private String address;
	
	@ApiModelProperty(value="图片")
	private String backUrl;
	
	@ApiModelProperty("是否启用")
	private Integer isUseable;

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getBackUrl() {
		return backUrl;
	}

	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}

	public Integer getIsUseable() {
		return isUseable;
	}

	public void setIsUseable(Integer isUseable) {
		this.isUseable = isUseable;
	}

	
}
