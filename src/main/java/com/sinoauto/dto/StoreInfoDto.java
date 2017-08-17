package com.sinoauto.dto;

import com.sinoauto.dao.bean.HqlsStore;

import io.swagger.annotations.ApiModelProperty;

public class StoreInfoDto {
	
	@ApiModelProperty(value="门店名称")
	private String storeName;
	
	@ApiModelProperty(value="联系人")
	private String userName;
	
	@ApiModelProperty("联系人号码")
	private String mobile;
	
	private HqlsStore store;
	

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

	public HqlsStore getStore() {
		return store;
	}

	public void setStore(HqlsStore store) {
		this.store = store;
	}

	
	
	
	
}
