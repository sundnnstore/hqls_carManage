package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户表客户表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class Ccustomer implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("昵称")
	private String customerName;
	@ApiModelProperty("全局用户ID")
	private Integer globalUserId;
	@ApiModelProperty("用户头像")
	private String headUrl;
	@ApiModelProperty("性别(1、男2、女3、不详)")
	private Integer gender;
	@ApiModelProperty("生日")
	private Date birthday;
	@ApiModelProperty("手机号")
	private String mobile;
	@ApiModelProperty("余额")
	private Double balance;
	@ApiModelProperty("是否激活（0，未激活；1，已激活）")
	private Boolean isActive;
	@ApiModelProperty("邀请人的ID")
	private Integer inviterUserId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置客户ID
	 * @param customerId 客户ID
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取客户ID
	 * @return 客户ID
	 */
	public Integer getCustomerId() {
		return this.customerId;
	}
	/**
	 * 设置昵称
	 * @param customerName 昵称
	 */
	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	/**
	 * 获取昵称
	 * @return 昵称
	 */
	public String getCustomerName() {
		return this.customerName;
	}
	/**
	 * 设置全局用户ID
	 * @param globalUserId 全局用户ID
	 */
	public void setGlobalUserId(Integer globalUserId) {
		this.globalUserId = globalUserId;
	}
	/**
	 * 获取全局用户ID
	 * @return 全局用户ID
	 */
	public Integer getGlobalUserId() {
		return this.globalUserId;
	}
	/**
	 * 设置用户头像
	 * @param headUrl 用户头像
	 */
	public void setHeadUrl(String headUrl) {
		this.headUrl = headUrl;
	}
	/**
	 * 获取用户头像
	 * @return 用户头像
	 */
	public String getHeadUrl() {
		return this.headUrl;
	}
	/**
	 * 设置性别(1、男2、女3、不详)
	 * @param gender 性别(1、男2、女3、不详)
	 */
	public void setGender(Integer gender) {
		this.gender = gender;
	}
	/**
	 * 获取性别(1、男2、女3、不详)
	 * @return 性别(1、男2、女3、不详)
	 */
	public Integer getGender() {
		return this.gender;
	}
	/**
	 * 设置生日
	 * @param birthday 生日
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}
	/**
	 * 获取生日
	 * @return 生日
	 */
	public Date getBirthday() {
		return this.birthday;
	}
	/**
	 * 设置手机号
	 * @param mobile 手机号
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	/**
	 * 获取手机号
	 * @return 手机号
	 */
	public String getMobile() {
		return this.mobile;
	}
	/**
	 * 设置余额
	 * @param balance 余额
	 */
	public void setBalance(Double balance) {
		this.balance = balance;
	}
	/**
	 * 获取余额
	 * @return 余额
	 */
	public Double getBalance() {
		return this.balance;
	}
	/**
	 * 设置是否激活（0，未激活；1，已激活）
	 * @param isActive 是否激活（0，未激活；1，已激活）
	 */
	public void setIsActive(Boolean isActive) {
		this.isActive = isActive;
	}
	/**
	 * 获取是否激活（0，未激活；1，已激活）
	 * @return 是否激活（0，未激活；1，已激活）
	 */
	public Boolean getIsActive() {
		return this.isActive;
	}
	/**
	 * 设置邀请人的ID
	 * @param inviterUserId 邀请人的ID
	 */
	public void setInviterUserId(Integer inviterUserId) {
		this.inviterUserId = inviterUserId;
	}
	/**
	 * 获取邀请人的ID
	 * @return 邀请人的ID
	 */
	public Integer getInviterUserId() {
		return this.inviterUserId;
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

