package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 收货地址表收货地址表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsShipAddress implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("收货地址ID")
	private Integer shipAddressId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("收件人")
	private String recipient;
	@ApiModelProperty("联系方式")
	private String mobile;
	@ApiModelProperty("省ID")
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
	private String countName;
	@ApiModelProperty("收货地址")
	private String address;
	@ApiModelProperty("是否是默认地址(0,1)")
	private Integer isDefault;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置收货地址ID
	 * @param shipAddressId 收货地址ID
	 */
	public void setShipAddressId(Integer shipAddressId) {
		this.shipAddressId = shipAddressId;
	}
	/**
	 * 获取收货地址ID
	 * @return 收货地址ID
	 */
	public Integer getShipAddressId() {
		return this.shipAddressId;
	}
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
	 * 设置收件人
	 * @param recipient 收件人
	 */
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	/**
	 * 获取收件人
	 * @return 收件人
	 */
	public String getRecipient() {
		return this.recipient;
	}
	/**
	 * 设置联系方式
	 * @param mobile 联系方式
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取联系方式
	 * @return 联系方式
	 */
	public String getMobile() {
		return this.mobile;
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
	 * 设置收货地址
	 * @param address 收货地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取收货地址
	 * @return 收货地址
	 */
	public String getAddress() {
		return this.address;
	}
	/**
	 * 设置是否是默认地址(0,1)
	 * @param isDefault 是否是默认地址(0,1)
	 */
	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取是否是默认地址(0,1)
	 * @return 是否是默认地址(0,1)
	 */
	public Integer getIsDefault() {
		return this.isDefault;
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
	public String getProvinceName() {
		return provinceName;
	}
	public void setProvinceName(String provinceName) {
		if(provinceName != null) {
			this.provinceName = provinceName;
		}
		
	}
	public String getCityName() {
		return cityName;
	}
	public void setCityName(String cityName) {
		if(cityName != null) {
			this.cityName = cityName;
		}
		
	}
	public String getCountName() {
		return countName;
	}
	public void setCountName(String countName) {
		if(countName != null) {
			this.countName = countName;
		}
		
	}

}

