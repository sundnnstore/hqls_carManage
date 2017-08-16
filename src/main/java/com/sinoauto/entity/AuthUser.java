package com.sinoauto.entity;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel("用户实体")
public class AuthUser implements java.io.Serializable {

	private static final long serialVersionUID = 3958122758307572343L;
	@ApiModelProperty(value = "用户编号", hidden = true)
	private Integer userId;

	@ApiModelProperty(value = "用户名（登录名）", example = "zhangsan", readOnly = true)
	private String userName;

	@ApiModelProperty("真实姓名")
	private String realName;

	@ApiModelProperty("昵称")
	private String nickName;

	@ApiModelProperty(value = "密码", readOnly = true)
	private String password;

	@ApiModelProperty(value = "密码盐值", hidden = true)
	private String salt;

	@ApiModelProperty("地址")
	private String address;

	@ApiModelProperty("头像url")
	private String headUrl;

	@ApiModelProperty(value = "是否冻结（0-否，1-是）", hidden = true)
	private Boolean isFrozen;

	@ApiModelProperty("身份证号")
	private String idNumber;

	@ApiModelProperty("身份证正面url")
	private String idFrontUrl;

	@ApiModelProperty("身份证背面url")
	private String idBackUrl;

	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value = "生日", example = "1990-04-05")
	private Date birthday;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "创建时间", hidden = true)
	private Date createTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "修改时间", hidden = true)
	private Date updateTime;

	@ApiModelProperty(value = "操作标识（1-新增，2-修改，3-删除）", hidden = true)
	private Short dmlFlag;

	public AuthUser() {
	}

	public AuthUser(String userName, String realName, String nickName, String password, String salt, Date createTime, Date updateTime,
			Short dmlFlag) {
		this.userName = userName;
		this.realName = realName;
		this.nickName = nickName;
		this.password = password;
		this.salt = salt;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.dmlFlag = dmlFlag;
	}

	public AuthUser(String userName, String realName, String nickName, String password, String salt, String address, String headUrl, Boolean isFrozen,
			String idNumber, String idFrontUrl, String idBackUrl, Date birthday, Date createTime, Date updateTime, Short dmlFlag) {
		this.userName = userName;
		this.realName = realName;
		this.nickName = nickName;
		this.password = password;
		this.salt = salt;
		this.address = address;
		this.headUrl = headUrl;
		this.isFrozen = isFrozen;
		this.idNumber = idNumber;
		this.idFrontUrl = idFrontUrl;
		this.idBackUrl = idBackUrl;
		this.birthday = birthday;
		this.createTime = createTime;
		this.updateTime = updateTime;
		this.dmlFlag = dmlFlag;
	}

	public Integer getUserId() {
		return this.userId;
	}

	public void setUserId(Integer userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getRealName() {
		return this.realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public String getNickName() {
		return this.nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getSalt() {
		return this.salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}

	public String getAddress() {
		return this.address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getHeadUrl() {
		return this.headUrl;
	}

	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}

	public Boolean getIsFrozen() {
		return this.isFrozen;
	}

	public void setIsFrozen(Boolean isFrozen) {
		this.isFrozen = isFrozen;
	}

	public String getIdNumber() {
		return this.idNumber;
	}

	public void setIdNumber(String idNumber) {
		this.idNumber = idNumber;
	}

	public String getIdFrontUrl() {
		return this.idFrontUrl;
	}

	public void setIdFrontUrl(String idFrontUrl) {
		this.idFrontUrl = idFrontUrl;
	}

	public String getIdBackUrl() {
		return this.idBackUrl;
	}

	public void setIdBackUrl(String idBackUrl) {
		this.idBackUrl = idBackUrl;
	}

	public Date getBirthday() {
		return this.birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	public Date getCreateTime() {
		return this.createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public Short getDmlFlag() {
		return this.dmlFlag;
	}

	public void setDmlFlag(Short dmlFlag) {
		this.dmlFlag = dmlFlag;
	}

}