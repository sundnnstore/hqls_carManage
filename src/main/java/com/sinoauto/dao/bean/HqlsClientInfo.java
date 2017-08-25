package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class HqlsClientInfo implements Serializable {

	private static final long serialVersionUID = 1L;
	@ApiModelProperty("CID")
	private String clientId;
	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("创建时间")
	private Date createTime;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		if (clientId != null) {
			this.clientId = clientId;
		}
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		if (userId != null) {
			this.userId = userId;
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		if (createTime != null) {
			this.createTime = createTime;
		}
	}

}
