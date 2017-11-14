package com.sinoauto.dto.c;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 * @author wuxiao
 *
 */
public class CustomerLoginDto {

	@ApiModelProperty("用户Id")
	private Integer customerId;
	@ApiModelProperty("用户昵称")
	private String customerName;
	@ApiModelProperty("用户头像")
	private String headUrl;
	@ApiModelProperty("默认车牌号")
	private String carNo;
	@ApiModelProperty("token")
	private String token;

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}
	

}
