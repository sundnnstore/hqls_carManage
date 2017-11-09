package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户车辆表客户车辆表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CCustomerCar implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户车辆ID")
	private Integer customerCarId;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("车牌号")
	private String carNo;
	@ApiModelProperty("车辆型号ID")
	private Integer carModelId;
	@ApiModelProperty("车辆颜色")
	private String carColor;
	@ApiModelProperty("行驶里程")
	private Integer mileage;
	@ApiModelProperty("车架号")
	private String vinNo;
	@ApiModelProperty("保险公司ID")
	private Integer insuranceCompanyId;
	@ApiModelProperty("保险到期时间")
	private Date insuranceExpirationTime;
	@ApiModelProperty("是否为默认车辆（0，否；1，是）")
	private Boolean isDefault;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置客户车辆ID
	 * @param customerCarId 客户车辆ID
	 */
	public void setCustomerCarId(Integer customerCarId) {
		this.customerCarId = customerCarId;
	}
	/**
	 * 获取客户车辆ID
	 * @return 客户车辆ID
	 */
	public Integer getCustomerCarId() {
		return this.customerCarId;
	}
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
	 * 设置车牌号
	 * @param carNo 车牌号
	 */
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}
	/**
	 * 获取车牌号
	 * @return 车牌号
	 */
	public String getCarNo() {
		return this.carNo;
	}
	/**
	 * 设置车辆型号ID
	 * @param carModelId 车辆型号ID
	 */
	public void setCarModelId(Integer carModelId) {
		this.carModelId = carModelId;
	}
	/**
	 * 获取车辆型号ID
	 * @return 车辆型号ID
	 */
	public Integer getCarModelId() {
		return this.carModelId;
	}
	/**
	 * 设置车辆颜色
	 * @param carColor 车辆颜色
	 */
	public void setCarColor(String carColor) {
		this.carColor = carColor;
	}
	/**
	 * 获取车辆颜色
	 * @return 车辆颜色
	 */
	public String getCarColor() {
		return this.carColor;
	}
	/**
	 * 设置行驶里程
	 * @param mileage 行驶里程
	 */
	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}
	/**
	 * 获取行驶里程
	 * @return 行驶里程
	 */
	public Integer getMileage() {
		return this.mileage;
	}
	/**
	 * 设置车架号
	 * @param vinNo 车架号
	 */
	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}
	/**
	 * 获取车架号
	 * @return 车架号
	 */
	public String getVinNo() {
		return this.vinNo;
	}
	/**
	 * 设置保险公司ID
	 * @param insuranceCompanyId 保险公司ID
	 */
	public void setInsuranceCompanyId(Integer insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}
	/**
	 * 获取保险公司ID
	 * @return 保险公司ID
	 */
	public Integer getInsuranceCompanyId() {
		return this.insuranceCompanyId;
	}
	/**
	 * 设置保险到期时间
	 * @param insuranceExpirationTime 保险到期时间
	 */
	public void setInsuranceExpirationTime(Date insuranceExpirationTime) {
		this.insuranceExpirationTime = insuranceExpirationTime;
	}
	/**
	 * 获取保险到期时间
	 * @return 保险到期时间
	 */
	public Date getInsuranceExpirationTime() {
		return this.insuranceExpirationTime;
	}
	/**
	 * 设置是否为默认车辆（0，否；1，是）
	 * @param isDefault 是否为默认车辆（0，否；1，是）
	 */
	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}
	/**
	 * 获取是否为默认车辆（0，否；1，是）
	 * @return 是否为默认车辆（0，否；1，是）
	 */
	public Boolean getIsDefault() {
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

}

