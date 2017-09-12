package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class CustomerInfoDto {

	@ApiModelProperty("客户名称")
	private String customerName;
	@ApiModelProperty("车型")
	private String carModel;
	@ApiModelProperty("车牌")
	private String carNo;
	@ApiModelProperty("累计到店次数")
	private Integer totalServiceCount;
	@ApiModelProperty("最近三次服务")
	private String lastService;
	@ApiModelProperty("头像")
	private String avatarUrl;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("门店ID")
	private Integer storeId;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		if (customerName != null) {
			this.customerName = customerName;
		}
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		if (carModel != null) {
			this.carModel = carModel;
		}
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		if (carNo != null) {
			this.carNo = carNo;
		}
	}

	public Integer getTotalServiceCount() {
		return totalServiceCount;
	}

	public void setTotalServiceCount(Integer totalServiceCount) {
		if (totalServiceCount != null) {
			this.totalServiceCount = totalServiceCount;
		}
	}

	public String getLastService() {
		return lastService;
	}

	public void setLastService(String lastService) {
		if (lastService != null) {
			this.lastService = lastService;
		}
	}

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		if (avatarUrl != null) {
			this.avatarUrl = avatarUrl;
		}
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		if (customerId != null) {
			this.customerId = customerId;
		}
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		if (storeId != null) {
			this.storeId = storeId;
		}
	}

}
