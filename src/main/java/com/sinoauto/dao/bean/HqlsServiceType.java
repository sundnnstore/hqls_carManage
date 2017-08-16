package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 服务类型表服务类型表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsServiceType implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("服务类型ID")
	private Integer serviceTypeId;
	@ApiModelProperty("服务类型名称")
	private String serviceTypeName;
	@ApiModelProperty("服务价格")
	private Double serviceAmount;
	@ApiModelProperty("服务类型照片")
	private String url;
	@ApiModelProperty("是否启用(0,1)")
	private Integer isUsable;
	
	/**
	 * 设置服务类型ID
	 * @param serviceTypeId 服务类型ID
	 */
	public void setServiceTypeId(Integer serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}
	/**
	 * 获取服务类型ID
	 * @return 服务类型ID
	 */
	public Integer getServiceTypeId() {
		return this.serviceTypeId;
	}
	/**
	 * 设置服务类型名称
	 * @param serviceTypeName 服务类型名称
	 */
	public void setServiceTypeName(String serviceTypeName) {
		this.serviceTypeName = serviceTypeName;
	}
	/**
	 * 获取服务类型名称
	 * @return 服务类型名称
	 */
	public String getServiceTypeName() {
		return this.serviceTypeName;
	}
	/**
	 * 设置服务价格
	 * @param serviceAmount 服务价格
	 */
	public void setServiceAmount(Double serviceAmount) {
		this.serviceAmount = serviceAmount;
	}
	/**
	 * 获取服务价格
	 * @return 服务价格
	 */
	public Double getServiceAmount() {
		return this.serviceAmount;
	}
	/**
	 * 设置服务类型照片
	 * @param url 服务类型照片
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取服务类型照片
	 * @return 服务类型照片
	 */
	public String getUrl() {
		return this.url;
	}
	/**
	 * 设置是否启用(0,1)
	 * @param isUsable 是否启用(0,1)
	 */
	public void setIsUsable(Integer isUsable) {
		this.isUsable = isUsable;
	}
	/**
	 * 获取是否启用(0,1)
	 * @return 是否启用(0,1)
	 */
	public Integer getIsUsable() {
		return this.isUsable;
	}

}

