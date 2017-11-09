package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 服务项目表服务项目表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CserviceProject implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("服务项目ID")
	private Integer serviceProjectId;
	@ApiModelProperty("服务项目名称")
	private String serviceProjectName;
	@ApiModelProperty("服务项目金额")
	private Double amount;
	@ApiModelProperty("项目logoURL")
	private String projectLogoUrl;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置服务项目ID
	 * @param serviceProjectId 服务项目ID
	 */
	public void setServiceProjectId(Integer serviceProjectId) {
		this.serviceProjectId = serviceProjectId;
	}
	/**
	 * 获取服务项目ID
	 * @return 服务项目ID
	 */
	public Integer getServiceProjectId() {
		return this.serviceProjectId;
	}
	/**
	 * 设置服务项目名称
	 * @param serviceProjectName 服务项目名称
	 */
	public void setServiceProjectName(String serviceProjectName) {
		this.serviceProjectName = serviceProjectName;
	}
	/**
	 * 获取服务项目名称
	 * @return 服务项目名称
	 */
	public String getServiceProjectName() {
		return this.serviceProjectName;
	}
	/**
	 * 设置服务项目金额
	 * @param amount 服务项目金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * 获取服务项目金额
	 * @return 服务项目金额
	 */
	public Double getAmount() {
		return this.amount;
	}
	/**
	 * 设置项目logoURL
	 * @param projectLogoUrl 项目logoURL
	 */
	public void setProjectLogoUrl(String projectLogoUrl) {
		this.projectLogoUrl = projectLogoUrl;
	}
	/**
	 * 获取项目logoURL
	 * @return 项目logoURL
	 */
	public String getProjectLogoUrl() {
		return this.projectLogoUrl;
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

