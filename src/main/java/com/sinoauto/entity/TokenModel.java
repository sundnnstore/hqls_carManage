package com.sinoauto.entity;

import org.apache.commons.lang3.StringUtils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * token模型
 * @author fujl
 * @version 1.0
 * @date 2017-04-14 10:43:47
 */
@ApiModel("token模型")
public class TokenModel {

	@ApiModelProperty("用户ID")
	private Integer userId;
	@ApiModelProperty("项目名称")
	private String programName;
	@ApiModelProperty("客户端类型")
	private String clientType;
	@ApiModelProperty("系统版本")
	private String osVersion;
	@ApiModelProperty("设备ID")
	private String deviceId;
	@ApiModelProperty("设备类型")
	private String deviceType;
	@ApiModelProperty("令牌")
	private String token;

	public TokenModel() {
		super();
	}

	public TokenModel(Integer userId, String programName, String clientType, String osVersion, String deviceId, String token) {
		super();
		this.userId = userId;
		this.programName = programName;
		this.osVersion = osVersion;
		this.deviceId = deviceId;
		this.token = token;
		this.clientType = clientType;
		this.deviceType = StringUtils.equals("web", clientType) ? "pc" : "mt";
	}

	public Integer getUserId() {
		return userId;
	}

	public void setUserId(Integer userId) {
		if (userId != null) {
			this.userId = userId;
		}
	}

	public String getProgramName() {
		return programName;
	}

	public void setProgramName(String programName) {
		if (programName != null) {
			this.programName = programName;
		}
	}

	public String getClientType() {
		return clientType;
	}

	public void setClientType(String clientType) {
		if (clientType != null) {
			this.clientType = clientType;
			if (StringUtils.equals("an", clientType) || StringUtils.equals("ios", clientType)) {
				this.deviceType = "mt";
			} else {
				this.deviceType = "pc";
			}
		}
	}

	public String getDeviceId() {
		return deviceId;
	}

	public void setDeviceId(String deviceId) {
		if (deviceId != null) {
			this.deviceId = deviceId;
		}
	}

	public String getDeviceType() {
		return deviceType;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		if (token != null) {
			this.token = token;
		}
	}

	public String getOsVersion() {
		return osVersion;
	}

	public void setOsVersion(String osVersion) {
		if (osVersion != null) {
			this.osVersion = osVersion;
		}
	}

}
