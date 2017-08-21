package com.sinoauto.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class RoleDto {

	@ApiModelProperty("角色ID")
	private Integer roleId;
	@ApiModelProperty("权限ID集合")
	private List<Integer> authorityIds;
	@ApiModelProperty("角色名称")
	private String roleName;

	public Integer getRoleId() {
		return roleId;
	}

	public void setRoleId(Integer roleId) {
		if (roleId != null) {
			this.roleId = roleId;
		}
	}

	public List<Integer> getAuthorityIds() {
		return authorityIds;
	}

	public void setAuthorityIds(List<Integer> authorityIds) {
		if (authorityIds != null) {
			this.authorityIds = authorityIds;
		}
	}

	public String getRoleName() {
		return roleName;
	}

	public void setRoleName(String roleName) {
		if (roleName != null) {
			this.roleName = roleName;
		}
	}

}
