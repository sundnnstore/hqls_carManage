package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 用户表用户表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsUser implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("用户名称")
	private String userName;
	@ApiModelProperty("用户密码")
	private String password;
	@ApiModelProperty("全局用户ID")
	private Integer globalUserId;
	@ApiModelProperty("用户联系方式")
	private String mobile;
	@ApiModelProperty("是否启用（0,1）")
	private Integer isUseable;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	
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
	 * 设置用户名称
	 * @param userName 用户名称
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
	/**
	 * 获取用户名称
	 * @return 用户名称
	 */
	public String getUserName() {
		return this.userName;
	}
	/**
	 * 设置用户密码
	 * @param password 用户密码
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * 获取用户密码
	 * @return 用户密码
	 */
	public String getPassword() {
		return this.password;
	}
	/**
	 * 设置全局用户ID
	 * @param globalUserId 全局用户ID
	 */
	public void setGlobalUserId(Integer globalUserId) {
		this.globalUserId = globalUserId;
	}
	/**
	 * 获取全局用户ID
	 * @return 全局用户ID
	 */
	public Integer getGlobalUserId() {
		return this.globalUserId;
	}
	/**
	 * 设置用户联系方式
	 * @param mobile 用户联系方式
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取用户联系方式
	 * @return 用户联系方式
	 */
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置是否启用（0,1）
	 * @param isUseable 是否启用（0,1）
	 */
	public void setIsUseable(Integer isUseable) {
		this.isUseable = isUseable;
	}
	/**
	 * 获取是否启用（0,1）
	 * @return 是否启用（0,1）
	 */
	public Integer getIsUseable() {
		return this.isUseable;
	}
	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
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
	/**
	 * 设置操作时间
	 * @param dmlTime 操作时间
	 */
	public void setDmlTime(Date dmlTime) {
		this.dmlTime = dmlTime;
	}
	/**
	 * 获取操作时间
	 * @return 操作时间
	 */
	public Date getDmlTime() {
		return this.dmlTime;
	}

}

