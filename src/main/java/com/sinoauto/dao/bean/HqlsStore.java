package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 门店表门店表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsStore implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("门店名称")
	private String storeName;
	@ApiModelProperty("门店电话")
	private String mobile;
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
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	
	/**
	 * 设置门店ID
	 * @param storeId 门店ID
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取门店ID
	 * @return 门店ID
	 */
	public Integer getStoreId() {
		return this.storeId;
	}
	/**
	 * 设置父级ID
	 * @param pid 父级ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取父级ID
	 * @return 父级ID
	 */
	public Integer getPid() {
		return this.pid;
	}
	/**
	 * 设置门店名称
	 * @param storeName 门店名称
	 */
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	/**
	 * 获取门店名称
	 * @return 门店名称
	 */
	public String getStoreName() {
		return this.storeName;
	}
	/**
	 * 设置门店电话
	 * @param mobile 门店电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取门店电话
	 * @return 门店电话
	 */
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置经度
	 * @param longitude 经度
	 */
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	/**
	 * 获取经度
	 * @return 经度
	 */
	public String getLongitude() {
		return this.longitude;
	}
	/**
	 * 设置维度
	 * @param latitude 维度
	 */
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	/**
	 * 获取维度
	 * @return 维度
	 */
	public String getLatitude() {
		return this.latitude;
	}
	/**
	 * 设置门店编码
	 * @param storeCode 门店编码
	 */
	public void setStoreCode(String storeCode) {
		this.storeCode = storeCode;
	}
	/**
	 * 获取门店编码
	 * @return 门店编码
	 */
	public String getStoreCode() {
		return this.storeCode;
	}
	/**
	 * 设置门店所在省ID
	 * @param provinceId 门店所在省ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取门店所在省ID
	 * @return 门店所在省ID
	 */
	public Integer getProvinceId() {
		return this.provinceId;
	}
	/**
	 * 设置市ID
	 * @param cityId 市ID
	 */
	public void setCityId(Integer cityId) {
		this.cityId = cityId;
	}
	/**
	 * 获取市ID
	 * @return 市ID
	 */
	public Integer getCityId() {
		return this.cityId;
	}
	/**
	 * 设置区县ID
	 * @param countyId 区县ID
	 */
	public void setCountyId(Integer countyId) {
		this.countyId = countyId;
	}
	/**
	 * 获取区县ID
	 * @return 区县ID
	 */
	public Integer getCountyId() {
		return this.countyId;
	}
	/**
	 * 设置详细地址
	 * @param address 详细地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取详细地址
	 * @return 详细地址
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * 设置背景图片URL
	 * @param backUrl 背景图片URL
	 */
	public void setBackUrl(String backUrl) {
		this.backUrl = backUrl;
	}
	/**
	 * 获取背景图片URL
	 * @return 背景图片URL
	 */
	public String getBackUrl() {
		return this.backUrl;
	}
	/**
	 * 设置是否启用
	 * @param isUseable 是否启用
	 */
	public void setIsUseable(Boolean isUseable) {
		this.isUseable = isUseable;
	}
	/**
	 * 获取是否启用
	 * @return 是否启用
	 */
	public Boolean getIsUseable() {
		return this.isUseable;
	}
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * 设置操作时间
	 * @param dmlTime 操作时间
	 */
	public void setDmlTime(Date dmlTime) {
		this.dmlTime = dmlTime;
	}
	/**
	 * 获取操作时间
	 * @return 操作时间
	 */
	public Date getDmlTime() {
		return this.dmlTime;
	}
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		if (provinceName != null) {
			this.provinceName = provinceName;
		}
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		if (cityName != null) {
			this.cityName = cityName;
		}
	}
	public String getCountyName() {
		return countyName;
	}
	public void setCountyName(String countyName) {
		this.countyName = countyName;
	}
	
}

