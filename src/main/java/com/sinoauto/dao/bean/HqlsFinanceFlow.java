package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 财务流水表财务流水表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsFinanceFlow implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("财务流水ID")
	private Integer financeFlowId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("交易单号")
	private String transactionNo;
	@ApiModelProperty("金额变动类型（1充值；2提现；3采购；4汽车维护服务）")
	private Integer changeType;
	@ApiModelProperty("变动金额")
	private Double changeMoney;
	@ApiModelProperty("1收入 2支出")
	private Integer chargeType;
	@ApiModelProperty("充值方式(1支付宝 2微信 3线下)")
	private Integer payType;
	@ApiModelProperty("提现审核状态（1待审核；2审核通过；3审核不通过）")
	private Integer checkStatus;
	@ApiModelProperty("订单号")
	private String orderNo;
	@ApiModelProperty("操作人")
	private String operPerson;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	@ApiModelProperty("收款人姓名")
	private String accountName;
	@ApiModelProperty("账号")
	private String account;
	@ApiModelProperty("银行")
	private String bank;
	@ApiModelProperty("开户行")
	private String openBank;
	@ApiModelProperty("是否删除(0,1)")
	private Integer isDelete;
	
	/**
	 * 设置财务流水ID
	 * @param financeFlowId 财务流水ID
	 */
	public void setFinanceFlowId(Integer financeFlowId) {
		this.financeFlowId = financeFlowId;
	}
	/**
	 * 获取财务流水ID
	 * @return 财务流水ID
	 */
	public Integer getFinanceFlowId() {
		return this.financeFlowId;
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
	 * 设置交易单号
	 * @param transactionNo 交易单号
	 */
	public void setTransactionNo(String transactionNo) {
		this.transactionNo = transactionNo;
	}
	/**
	 * 获取交易单号
	 * @return 交易单号
	 */
	public String getTransactionNo() {
		return this.transactionNo;
	}
	/**
	 * 设置金额变动类型（1充值；2提现；3采购；4汽车维护服务）
	 * @param changeType 金额变动类型（1充值；2提现；3采购；4汽车维护服务）
	 */
	public void setChangeType(Integer changeType) {
		this.changeType = changeType;
	}
	/**
	 * 获取金额变动类型（1充值；2提现；3采购；4汽车维护服务）
	 * @return 金额变动类型（1充值；2提现；3采购；4汽车维护服务）
	 */
	public Integer getChangeType() {
		return this.changeType;
	}
	/**
	 * 设置变动金额
	 * @param changeMoney 变动金额
	 */
	public void setChangeMoney(Double changeMoney) {
		this.changeMoney = changeMoney;
	}
	/**
	 * 获取变动金额
	 * @return 变动金额
	 */
	public Double getChangeMoney() {
		return this.changeMoney;
	}
	/**
	 * 设置1收入 2支出
	 * @param chargeType 1收入 2支出
	 */
	public void setChargeType(Integer chargeType) {
		this.chargeType = chargeType;
	}
	/**
	 * 获取1收入 2支出
	 * @return 1收入 2支出
	 */
	public Integer getChargeType() {
		return this.chargeType;
	}
	/**
	 * 设置充值方式(1支付宝 2微信 3线下)
	 * @param payType 充值方式(1支付宝 2微信 3线下)
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取充值方式(1支付宝 2微信 3线下)
	 * @return 充值方式(1支付宝 2微信 3线下)
	 */
	public Integer getPayType() {
		return this.payType;
	}
	/**
	 * 设置提现审核状态（1待审核；2审核通过；3审核不通过）
	 * @param checkStatus 提现审核状态（1待审核；2审核通过；3审核不通过）
	 */
	public void setCheckStatus(Integer checkStatus) {
		this.checkStatus = checkStatus;
	}
	/**
	 * 获取提现审核状态（1待审核；2审核通过；3审核不通过）
	 * @return 提现审核状态（1待审核；2审核通过；3审核不通过）
	 */
	public Integer getCheckStatus() {
		return this.checkStatus;
	}
	/**
	 * 设置订单号
	 * @param orderNo 订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取订单号
	 * @return 订单号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}
	/**
	 * 设置操作人
	 * @param operPerson 操作人
	 */
	public void setOperPerson(String operPerson) {
		this.operPerson = operPerson;
	}
	/**
	 * 获取操作人
	 * @return 操作人
	 */
	public String getOperPerson() {
		return this.operPerson;
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
	/**
	 * 设置收款人姓名
	 * @param accountName 收款人姓名
	 */
	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}
	/**
	 * 获取收款人姓名
	 * @return 收款人姓名
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
	 * 设置是否删除(0,1)
	 * @param isDelete 是否删除(0,1)
	 */
	public void setIsDelete(Integer isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取是否删除(0,1)
	 * @return 是否删除(0,1)
	 */
	public Integer getIsDelete() {
		return this.isDelete;
	}

}

