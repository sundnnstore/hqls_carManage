package com.sinoauto.dto;

public class UserLoginDto {

	private String userName;
	private String token;
	private String mobile;

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		if (userName != null) {
			this.userName = userName;
		}
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		if (token != null) {
			this.token = token;
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

}
