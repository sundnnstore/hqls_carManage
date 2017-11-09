package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 门店推荐服务项目表门店推荐服务项目表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CstoreServiceProject implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("门店推荐服务ID")
	private Integer storeServiceProjectId;
	@ApiModelProperty("服务项目ID")
	private Integer serviceProjectId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置门店推荐服务ID
	 * @param storeServiceProjectId 门店推荐服务ID
	 */
	public void setStoreServiceProjectId(Integer storeServiceProjectId) {
		this.storeServiceProjectId = storeServiceProjectId;
	}
	/**
	 * 获取门店推荐服务ID
	 * @return 门店推荐服务ID
	 */
	public Integer getStoreServiceProjectId() {
		return this.storeServiceProjectId;
	}
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

