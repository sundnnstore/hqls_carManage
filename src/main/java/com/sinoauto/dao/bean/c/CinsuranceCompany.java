package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 保险公司表保险公司表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CinsuranceCompany implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("保险公司ID")
	private Integer insuranceCompanyId;
	@ApiModelProperty("保险公司名称")
	private String insuranceCompanyName;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
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
	 * 设置保险公司名称
	 * @param insuranceCompanyName 保险公司名称
	 */
	public void setInsuranceCompanyName(String insuranceCompanyName) {
		this.insuranceCompanyName = insuranceCompanyName;
	}
	/**
	 * 获取保险公司名称
	 * @return 保险公司名称
	 */
	public String getInsuranceCompanyName() {
		return this.insuranceCompanyName;
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

