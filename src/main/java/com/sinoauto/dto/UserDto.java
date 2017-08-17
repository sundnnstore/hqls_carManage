package com.sinoauto.dto;

import java.util.List;

import com.sinoauto.dao.bean.HqlsRole;

import io.swagger.annotations.ApiModelProperty;

public class UserDto {
	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("用户姓名")
	private String userName;
	@ApiModelProperty("用户手机号")
	private String mobile;
	@ApiModelProperty("用户角色集合")
	private List<HqlsRole> roles;
	@ApiModelProperty("是否禁用")
	private Boolean isUseable;

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		if (userId != null) {
			this.userId = userId;
		}
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if (userName != null) {
			this.userName = userName;
		}
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		if (mobile != null) {
			this.mobile = mobile;
		}
	}

	public List<HqlsRole> getRoles() {
		return roles;
	}

	public void setRoles(List<HqlsRole> roles) {
		if (roles != null) {
			this.roles = roles;
		}
	}

	public Boolean getIsUseable() {
		return isUseable;
	}

	public void setIsUseable(Boolean isUseable) {
		if (isUseable != null) {
			this.isUseable = isUseable;
		}
	}

}
