package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户门店中间表用户门店中间表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsUserStore implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("门店用户ID")
	private Integer userStoreId;
	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("是否为联系人")
	private Boolean isContact;

	/**
	 * 设置门店用户ID
	 * @param userStoreId 门店用户ID
	 */
	public void setUserStoreId(Integer userStoreId) {
		this.userStoreId = userStoreId;
	}

	/**
	 * 获取门店用户ID
	 * @return 门店用户ID
	 */
	public Integer getUserStoreId() {
		return this.userStoreId;
	}

	/**
	 * 设置用户ID
	 * @param userId 用户ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	/**
	 * 获取用户ID
	 * @return 用户ID
	 */
	public Integer getUserId() {
		return this.userId;
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

	public Boolean getIsContact() {
		return isContact;
	}

	public void setIsContact(Boolean isContact) {
		if (isContact != null) {
			this.isContact = isContact;
		}
	}

}
