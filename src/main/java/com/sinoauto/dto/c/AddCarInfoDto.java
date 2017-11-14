package com.sinoauto.dto.c;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 选车型/添加车辆信息
 * 
 * @author Wuxiao
 *
 */
public class AddCarInfoDto {

	@ApiModelProperty("车型Id")
	private Integer modelId;
	@ApiModelProperty("车牌号")
	private String carNo;
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

	public Integer getModelId() {
		return modelId;
	}

	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public Integer getMileage() {
		return mileage;
	}

	public void setMileage(Integer mileage) {
		this.mileage = mileage;
	}

	public String getVinNo() {
		return vinNo;
	}

	public void setVinNo(String vinNo) {
		this.vinNo = vinNo;
	}

	public Integer getInsuranceCompanyId() {
		return insuranceCompanyId;
	}

	public void setInsuranceCompanyId(Integer insuranceCompanyId) {
		this.insuranceCompanyId = insuranceCompanyId;
	}

	public Date getInsuranceExpirationTime() {
		return insuranceExpirationTime;
	}

	public void setInsuranceExpirationTime(Date insuranceExpirationTime) {
		this.insuranceExpirationTime = insuranceExpirationTime;
	}

	public Boolean getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Boolean isDefault) {
		this.isDefault = isDefault;
	}

}
