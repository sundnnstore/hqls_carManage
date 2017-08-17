package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 用户角色表用户角色表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsUserRole implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("用户角色ID")
	private Integer userRoleId;
	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("角色ID")
	private Integer roleId;
	
	/**
	 * 设置用户角色ID
	 * @param userRoleId 用户角色ID
	 */
	public void setUserRoleId(Integer userRoleId) {
		this.userRoleId = userRoleId;
	}
	/**
	 * 获取用户角色ID
	 * @return 用户角色ID
	 */
	public Integer getUserRoleId() {
		return this.userRoleId;
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
	 * 设置角色ID
	 * @param roleId 角色ID
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取角色ID
	 * @return 角色ID
	 */
	public Integer getRoleId() {
		return this.roleId;
	}

}

