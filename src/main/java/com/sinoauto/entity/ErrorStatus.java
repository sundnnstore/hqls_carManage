package com.sinoauto.entity;

/**
 * 自定义请求状态码
 * @author fujl
 * @version 1.0
 * @date 2017-07-03 14:16:50
 */
public enum ErrorStatus {
	/** 缺少参数 */
	PARAM_NOT_NULL(40001, "缺少参数"),
	/** 参数异常 */
	INVALID_DATA(40002, "数据类型不合法"),
	/** 系统异常，请联系开发人员！ */
	SYSTEM_EXCEPTION(50001, "系统异常，请联系开发人员！"),
	/** 数据不存在 */
	DATA_NOT_EXIST(40004, "数据不存在"),
	/** 用户名或密码错误 */
	USERNAME_OR_PASSWORD_ERROR(40005, "账户或密码错误"),
	/** 会话过程中 */
	INVALID_LOGIN(40086, "当前账号正在视频中！"),
	/** 非管理员登录 */
	NO_PERMISSION(40088, "该用户不是管理员！"),
	/** 未认证 */
	UNAUTHORIZED(40003, "未认证"),
	/** 无效的token */
	INVALID_TOKEN(40008, "无效的token"),
	/** 失效的token */
	EXPIRED_TOKEN(40009, "失效的token");

	/**
	 * 返回码
	 */
	private int errcode;

	/**
	 * 返回结果描述
	 */
	private String errmsg;

	public int getErrcode() {
		return errcode;
	}

	public void setErrcode(int errcode) {
		this.errcode = errcode;
	}

	public String getErrmsg() {
		return errmsg;
	}

	public void setErrmsg(String errmsg) {
		if (errmsg != null) {
			this.errmsg = errmsg;
		}
	}

	private ErrorStatus(int errcode, String errmsg) {
		this.errcode = errcode;
		this.errmsg = errmsg;
	}

}
