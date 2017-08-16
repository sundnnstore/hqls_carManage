package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 权限表权限表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsAuthority implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("权限ID")
	private Integer authorityId;
	@ApiModelProperty("权限名称")
	private String authorityName;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置权限ID
	 * @param authorityId 权限ID
	 */
	public void setAuthorityId(Integer authorityId) {
		this.authorityId = authorityId;
	}
	/**
	 * 获取权限ID
	 * @return 权限ID
	 */
	public Integer getAuthorityId() {
		return this.authorityId;
	}
	/**
	 * 设置权限名称
	 * @param authorityName 权限名称
	 */
	public void setAuthorityName(String authorityName) {
		this.authorityName = authorityName;
	}
	/**
	 * 获取权限名称
	 * @return 权限名称
	 */
	public String getAuthorityName() {
		return this.authorityName;
	}
	/**
	 * 设置父级ID
	 * @param pid 父级ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取父级ID
	 * @return 父级ID
	 */
	public Integer getPid() {
		return this.pid;
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

