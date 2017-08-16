package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 门店银行卡信息表门店银行卡信息表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsStoreCard implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("门店银行卡ID")
	private Integer storeCardId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("收款人户名")
	private String accountName;
	@ApiModelProperty("账号")
	private String account;
	@ApiModelProperty("银行")
	private String bank;
	@ApiModelProperty("开户行")
	private String openBank;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置门店银行卡ID
	 * @param storeCardId 门店银行卡ID
	 */
	public void setStoreCardId(Integer storeCardId) {
		this.storeCardId = storeCardId;
	}
	/**
	 * 获取门店银行卡ID
	 * @return 门店银行卡ID
	 */
	public Integer getStoreCardId() {
		return this.storeCardId;
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
	 * 设置收款人户名
	 * @param accountName 收款人户名
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * 获取收款人户名
	 * @return 收款人户名
	 */
	public String getAccountName() {
		return this.accountName;
	}
	/**
	 * 设置账号
	 * @param account 账号
	 */
	public void setAccount(String account) {
		this.account = account;
	}
	/**
	 * 获取账号
	 * @return 账号
	 */
	public String getAccount() {
		return this.account;
	}
	/**
	 * 设置银行
	 * @param bank 银行
	 */
	public void setBank(String bank) {
		this.bank = bank;
	}
	/**
	 * 获取银行
	 * @return 银行
	 */
	public String getBank() {
		return this.bank;
	}
	/**
	 * 设置开户行
	 * @param openBank 开户行
	 */
	public void setOpenBank(String openBank) {
		this.openBank = openBank;
	}
	/**
	 * 获取开户行
	 * @return 开户行
	 */
	public String getOpenBank() {
		return this.openBank;
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

