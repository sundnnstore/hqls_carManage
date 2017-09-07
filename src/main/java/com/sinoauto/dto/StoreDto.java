package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class StoreDto {
	
	@ApiModelProperty(value="管理员账号")
	private String userMobile;
	@ApiModelProperty("门店名称")
	private String storeName;
	@ApiModelProperty("门店电话")
	private String stoMobile;
	@ApiModelProperty("经度")
	private String longitude;
	@ApiModelProperty("维度")
	private String latitude;
	@ApiModelProperty("门店编码")
	private String storeCode;
	@ApiModelProperty("门店所在省ID")
	private Integer provinceId;
	@ApiModelProperty("市ID")
	private Integer cityId;
	@ApiModelProperty("区县ID")
	private Integer countyId;
	@ApiModelProperty("详细地址")
	private String address;
	@ApiModelProperty("背景图片URL")
	private String backUrl;
	@ApiModelProperty("是否启用")
	private Integer isUseable;
	@ApiModelProperty("门店id")
	private Integer storeId;
	@ApiModelProperty("省名称")
	private String provinceName;
	@ApiModelProperty("市名称")
	private String cityName;
	@ApiModelProperty("区县名称")
	private String countyName;
	@ApiModelProperty("门店联系人")
	private String userName;
	@ApiModelProperty("门店级别")
	private String storeLevel;
	@ApiModelProperty("门店分类")
	private String storeClass;
	
	

	public String getUserMobile() {
		return userMobile;
	}

	public void setUserMobile(String userMobile) {
		this.userMobile = userMobile;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}


	public String getStoMobile() {
		return stoMobile;
	}

	public void setStoMobile(String stoMobile) {
		this.stoMobile = stoMobile;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}

	public Integer getProvinceId() {
		return provinceId;
	}

	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}

	public Integer getCityId() {
		return cityId;
	}

	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}

	public Integer getCountyId() {
		return countyId;
	}

	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public String getProvinceName() {
		return provinceName;
	}

	public void setProvinceName(String provinceName) {
		this.provinceName = provinceName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getCountyName() {
		return countyName;
	}

	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getStoreLevel() {
		return storeLevel;
	}

	public void setStoreLevel(String storeLevel) {
		this.storeLevel = storeLevel;
	}

	public String getStoreClass() {
		return storeClass;
	}

	public void setStoreClass(String storeClass) {
		this.storeClass = storeClass;
	}

	

	
	

}
