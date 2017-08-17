package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 角色权限表角色权限表<br>
 * @author tangwt
 * @version 1.0, 2017-08-17
 */

public class HqlsRoleAuthority implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("角色权限表主键Id")
	private Integer roleAuthorityId;
	@ApiModelProperty("角色Id")
	private Integer roleId;
	@ApiModelProperty("权限Id")
	private Integer authorityId;
	
	/**
	 * 设置角色权限表主键Id
	 * @param roleAuthorityId 角色权限表主键Id
	 */
	public void setRoleAuthorityId(Integer roleAuthorityId) {
		this.roleAuthorityId = roleAuthorityId;
	}
	/**
	 * 获取角色权限表主键Id
	 * @return 角色权限表主键Id
	 */
	public Integer getRoleAuthorityId() {
		return this.roleAuthorityId;
	}
	/**
	 * 设置角色Id
	 * @param roleId 角色Id
	 */
	public void setRoleId(Integer roleId) {
		this.roleId = roleId;
	}
	/**
	 * 获取角色Id
	 * @return 角色Id
	 */
	public Integer getRoleId() {
		return this.roleId;
	}
	/**
	 * 设置权限Id
	 * @param authorityId 权限Id
	 */
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	/**
	 * 获取权限Id
	 * @return 权限Id
	 */
	public Integer getAuthorityId() {
		return this.authorityId;
	}

}

