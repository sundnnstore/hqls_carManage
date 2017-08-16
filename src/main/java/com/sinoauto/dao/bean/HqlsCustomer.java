package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户表
客户表
<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsCustomer implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("客户名称")
	private String customerName;
	@ApiModelProperty("客户电话")
	private String mobile;
	@ApiModelProperty("座机")
	private String landline;
	@ApiModelProperty("头像url")
	private String avatarUrl;
	@ApiModelProperty("性别（1男 2女 3不详）")
	private Integer gender;
	@ApiModelProperty("省ID")
	private Integer provinceId;
	@ApiModelProperty("市ID")
	private Integer cityId;
	@ApiModelProperty("区县ID")
	private Integer countyId;
	@ApiModelProperty("地址")
	private String address;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	
	/**
	 * 设置客户ID
	 * @param customerId 客户ID
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取客户ID
	 * @return 客户ID
	 */
	public Integer getCustomerId() {
		return this.customerId;
	}
	/**
	 * 设置客户名称
	 * @param customerName 客户名称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取客户名称
	 * @return 客户名称
	 */
	public String getCustomerName() {
		return this.customerName;
	}
	/**
	 * 设置客户电话
	 * @param mobile 客户电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取客户电话
	 * @return 客户电话
	 */
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置座机
	 * @param landline 座机
	 */
	public void setLandline(String landline) {
		this.landline = landline;
	}
	/**
	 * 获取座机
	 * @return 座机
	 */
	public String getLandline() {
		return this.landline;
	}
	/**
	 * 设置头像url
	 * @param avatarUrl 头像url
	 */
	public void setAvatarUrl(String avatarUrl) {
		this.avatarUrl = avatarUrl;
	}
	/**
	 * 获取头像url
	 * @return 头像url
	 */
	public String getAvatarUrl() {
		return this.avatarUrl;
	}
	/**
	 * 设置性别（1男 2女 3不详）
	 * @param gender 性别（1男 2女 3不详）
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取性别（1男 2女 3不详）
	 * @return 性别（1男 2女 3不详）
	 */
	public Integer getGender() {
		return this.gender;
	}
	/**
	 * 设置省ID
	 * @param provinceId 省ID
	 */
	public void setProvinceId(Integer provinceId) {
		this.provinceId = provinceId;
	}
	/**
	 * 获取省ID
	 * @return 省ID
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
	 * 设置地址
	 * @param address 地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取地址
	 * @return 地址
	 */
	public String getAddress() {
		return this.address;
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

}

