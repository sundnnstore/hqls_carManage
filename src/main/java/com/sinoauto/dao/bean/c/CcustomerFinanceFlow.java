package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户资金流水表客户资金流水表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CcustomerFinanceFlow implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("资金流水ID")
	private Integer financeFlowId;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("收支类型（1、收入；2、支出）")
	private Integer balanceType;
	@ApiModelProperty("支付类型(1、支付宝2、微信3、银联）")
	private Integer payType;
	@ApiModelProperty("变动类型（1、充值2、服务订单消费3、增项消费）")
	private Integer financeType;
	@ApiModelProperty("变动金额")
	private Double amount;
	@ApiModelProperty("订单号")
	private String orderNo;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置资金流水ID
	 * @param financeFlowId 资金流水ID
	 */
	public void setFinanceFlowId(Integer financeFlowId) {
		this.financeFlowId = financeFlowId;
	}
	/**
	 * 获取资金流水ID
	 * @return 资金流水ID
	 */
	public Integer getFinanceFlowId() {
		return this.financeFlowId;
	}
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
	 * 设置收支类型（1、收入；2、支出）
	 * @param balanceType 收支类型（1、收入；2、支出）
	 */
	public void setBalanceType(Integer balanceType) {
		this.balanceType = balanceType;
	}
	/**
	 * 获取收支类型（1、收入；2、支出）
	 * @return 收支类型（1、收入；2、支出）
	 */
	public Integer getBalanceType() {
		return this.balanceType;
	}
	/**
	 * 设置支付类型(1、支付宝2、微信3、银联）
	 * @param payType 支付类型(1、支付宝2、微信3、银联）
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取支付类型(1、支付宝2、微信3、银联）
	 * @return 支付类型(1、支付宝2、微信3、银联）
	 */
	public Integer getPayType() {
		return this.payType;
	}
	/**
	 * 设置变动类型（1、充值2、服务订单消费3、增项消费）
	 * @param financeType 变动类型（1、充值2、服务订单消费3、增项消费）
	 */
	public void setFinanceType(Integer financeType) {
		this.financeType = financeType;
	}
	/**
	 * 获取变动类型（1、充值2、服务订单消费3、增项消费）
	 * @return 变动类型（1、充值2、服务订单消费3、增项消费）
	 */
	public Integer getFinanceType() {
		return this.financeType;
	}
	/**
	 * 设置变动金额
	 * @param amount 变动金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * 获取变动金额
	 * @return 变动金额
	 */
	public Double getAmount() {
		return this.amount;
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

