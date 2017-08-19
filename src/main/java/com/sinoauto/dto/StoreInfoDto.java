package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class StoreInfoDto {
	
	@ApiModelProperty(value="门店名称")
	private String storeName;
	@ApiModelProperty(value="联系人")
	private String userName;
	@ApiModelProperty("联系人号码")
	private String mobile;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("门店电话")
	private String storeMobile;
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
	@ApiModelProperty("省名称")
	private String provinceName;
	@ApiModelProperty("市名称")
	private String cityName;
	@ApiModelProperty("区县名称")
	private String countyName;
	@ApiModelProperty("详细地址")
	private String address;
	@ApiModelProperty("背景图片URL")
	private String backUrl;
	@ApiModelProperty("是否启用")
	private Boolean isUseable;
	

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

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getStoreMobile() {
		return storeMobile;
	}

	public void setStoreMobile(String storeMobile) {
		this.storeMobile = storeMobile;
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

	public Boolean getIsUseable() {
		return isUseable;
	}

	public void setIsUseable(Boolean isUseable) {
		this.isUseable = isUseable;
	}



	
	
	
	
}
