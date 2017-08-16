package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 角色表角色表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsRole implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("角色ID")
	private Integer roleId;
	@ApiModelProperty("角色名称")
	private String roleName;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
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
	/**
	 * 设置角色名称
	 * @param roleName 角色名称
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
	/**
	 * 获取角色名称
	 * @return 角色名称
	 */
	public String getRoleName() {
		return this.roleName;
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

}

