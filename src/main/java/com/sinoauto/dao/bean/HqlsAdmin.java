package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 后台管理人员表后台管理人员表<br>
 * @author tangwt
 * @version 1.0, 2017-08-17
 */

public class HqlsAdmin implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("管理人员ID")
	private Integer userId;
	@ApiModelProperty("globalUserId")
	private Integer globalUserId;
	@ApiModelProperty("管理员名称")
	private String userName;
	@ApiModelProperty("手机号")
	private String mobile;
	@ApiModelProperty("是否启用")
	private Boolean isUseable;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置管理人员ID
	 * @param userId 管理人员ID
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取管理人员ID
	 * @return 管理人员ID
	 */
	public Integer getUserId() {
		return this.userId;
	}
	/**
	 * 设置globalUserId
	 * @param globalUserId globalUserId
	 */
	public void setGlobalUserId(Integer globalUserId) {
		this.globalUserId = globalUserId;
	}
	/**
	 * 获取globalUserId
	 * @return globalUserId
	 */
	public Integer getGlobalUserId() {
		return this.globalUserId;
	}
	/**
	 * 设置管理员名称
	 * @param userName 管理员名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取管理员名称
	 * @return 管理员名称
	 */
	public String getUserName() {
		return this.userName;
	}
	/**
	 * 设置手机号
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取手机号
	 * @return 手机号
	 */
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置是否启用
	 * @param isUseable 是否启用
	 */
	public void setIsUseable(Boolean isUseable) {
		this.isUseable = isUseable;
	}
	/**
	 * 获取是否启用
	 * @return 是否启用
	 */
	public Boolean getIsUseable() {
		return this.isUseable;
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

