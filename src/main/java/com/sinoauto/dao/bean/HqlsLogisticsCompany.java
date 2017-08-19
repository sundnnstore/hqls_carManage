package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 物流公司表物流公司表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsLogisticsCompany implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("物流公司")
	private Integer logisticsId;
	@ApiModelProperty("公司名称")
	private String logisticsName;
	@ApiModelProperty("公司编码")
	private String companyNo;
	@ApiModelProperty("公司电话")
	private String mobile;
	@ApiModelProperty("公司地址")
	private String address;
	
	/**
	 * 设置物流公司
	 * @param logisticsId 物流公司
	 */
	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}
	/**
	 * 获取物流公司
	 * @return 物流公司
	 */
	public Integer getLogisticsId() {
		return this.logisticsId;
	}
	/**
	 * 设置公司名称
	 * @param logisticsName 公司名称
	 */
	public void setLogisticsName(String logisticsName) {
		this.logisticsName = logisticsName;
	}
	/**
	 * 获取公司名称
	 * @return 公司名称
	 */
	public String getLogisticsName() {
		return this.logisticsName;
	}
	/**
	 * 设置公司电话
	 * @param mobile 公司电话
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取公司电话
	 * @return 公司电话
	 */
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置公司地址
	 * @param address 公司地址
	 */
	public void setAddress(String address) {
		this.address = address;
	}
	/**
	 * 获取公司地址
	 * @return 公司地址
	 */
	public String getAddress() {
		return this.address;
	}
	public String getCompanyNo() {
		return companyNo;
	}
	public void setCompanyNo(String companyNo) {
		if(companyNo != null) {
			this.companyNo = companyNo;
		}
		
	}

}

