package com.sinoauto.dto.c;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 更新用户信息
 * 
 * @author Wuxiao
 *
 */
public class CustomerUpdateInfoDto {

	@ApiModelProperty("昵称")
	private String customerName;
	@ApiModelProperty("性别（1，男；2，女；3，不详）")
	private Integer gender;
	@ApiModelProperty("昵称")
	private String headUrl;
	@ApiModelProperty("昵称")
	private Date birthday;

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Integer getGender() {
		return gender;
	}

	public void setGender(Integer gender) {
		this.gender = gender;
	}

	public String getHeadUrl() {
		return headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

}
