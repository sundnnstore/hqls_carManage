package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 门店财务表门店财务表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsStoreFinance implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("门店财务ID")
	private Integer storeFinanceId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("余额")
	private Double balance;
	@ApiModelProperty("可用提现金额")
	private Double cashAble;
	@ApiModelProperty("不可用提现金额")
	private Double cashDisable;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;

	private String storeName;
	private String customerName;
	private String mobile;

	/**
	 * 设置门店财务ID
	 * @param storeFinanceId 门店财务ID
	 */
	public void setStoreFinanceId(Integer storeFinanceId) {
		this.storeFinanceId = storeFinanceId;
	}

	/**
	 * 获取门店财务ID
	 * @return 门店财务ID
	 */
	public Integer getStoreFinanceId() {
		return this.storeFinanceId;
	}

	/**
	 * 设置门店ID
	 * @param storeId 门店ID
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	/**
	 * 获取门店ID
	 * @return 门店ID
	 */
	public Integer getStoreId() {
		return this.storeId;
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
	 * 设置可用提现金额
	 * @param cashAble 可用提现金额
	 */
	public void setCashAble(Double cashAble) {
		this.cashAble = cashAble;
	}

	/**
	 * 获取可用提现金额
	 * @return 可用提现金额
	 */
	public Double getCashAble() {
		return this.cashAble;
	}

	/**
	 * 设置不可用提现金额
	 * @param cashDisable 不可用提现金额
	 */
	public void setCashDisable(Double cashDisable) {
		this.cashDisable = cashDisable;
	}

	/**
	 * 获取不可用提现金额
	 * @return 不可用提现金额
	 */
	public Double getCashDisable() {
		return this.cashDisable;
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

	/**
	 * 设置操作时间
	 * @param dmlTime 操作时间
	 */
	public void setDmlTime(Date dmlTime) {
		this.dmlTime = dmlTime;
	}

	/**
	 * 获取操作时间
	 * @return 操作时间
	 */
	public Date getDmlTime() {
		return this.dmlTime;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

}
