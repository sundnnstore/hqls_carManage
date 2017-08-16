package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户门店中间表客户门店中间表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsCustomerStore implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户门店ID")
	private Integer customerStoreId;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置客户门店ID
	 * @param customerStoreId 客户门店ID
	 */
	public void setCustomerStoreId(Integer customerStoreId) {
		this.customerStoreId = customerStoreId;
	}
	/**
	 * 获取客户门店ID
	 * @return 客户门店ID
	 */
	public Integer getCustomerStoreId() {
		return this.customerStoreId;
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

