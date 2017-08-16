package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 采购订单表采购订单表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsPurchaseOrder implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("采购订单ID")
	private Integer purchaseOrderId;
	@ApiModelProperty("订单号")
	private String orderNo;
	@ApiModelProperty("订单状态（1.待支付；2.待发货；3.待收货；4.已完成）")
	private Integer orderStatus;
	@ApiModelProperty("收货地址ID")
	private Integer shipAddressId;
	@ApiModelProperty("支付方式（0.余额；1.支付宝；2.微信；3.银联卡；4.信用卡）")
	private Integer payType;
	@ApiModelProperty("物流单号")
	private String logisticsNo;
	@ApiModelProperty("物流公司ID")
	private Integer logisticsId;
	@ApiModelProperty("其他费用")
	private Double otherFee;
	@ApiModelProperty("合计总费用")
	private Double totalFee;
	@ApiModelProperty("支付订单号")
	private String payNo;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	@ApiModelProperty("优惠费用")
	private Double discountFee;
	@ApiModelProperty("支付金额")
	private Double payFee;
	@ApiModelProperty("下单门店ID")
	private Integer storeId;
	
	/**
	 * 设置采购订单ID
	 * @param purchaseOrderId 采购订单ID
	 */
	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	/**
	 * 获取采购订单ID
	 * @return 采购订单ID
	 */
	public Integer getPurchaseOrderId() {
		return this.purchaseOrderId;
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
	 * 设置订单状态（1.待支付；2.待发货；3.待收货；4.已完成）
	 * @param orderStatus 订单状态（1.待支付；2.待发货；3.待收货；4.已完成）
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取订单状态（1.待支付；2.待发货；3.待收货；4.已完成）
	 * @return 订单状态（1.待支付；2.待发货；3.待收货；4.已完成）
	 */
	public Integer getOrderStatus() {
		return this.orderStatus;
	}
	/**
	 * 设置收货地址ID
	 * @param shipAddressId 收货地址ID
	 */
	public void setShipAddressId(Integer shipAddressId) {
		this.shipAddressId = shipAddressId;
	}
	/**
	 * 获取收货地址ID
	 * @return 收货地址ID
	 */
	public Integer getShipAddressId() {
		return this.shipAddressId;
	}
	/**
	 * 设置支付方式（0.余额；1.支付宝；2.微信；3.银联卡；4.信用卡）
	 * @param payType 支付方式（0.余额；1.支付宝；2.微信；3.银联卡；4.信用卡）
	 */
	public void setPayType(Integer payType) {
		this.payType = payType;
	}
	/**
	 * 获取支付方式（0.余额；1.支付宝；2.微信；3.银联卡；4.信用卡）
	 * @return 支付方式（0.余额；1.支付宝；2.微信；3.银联卡；4.信用卡）
	 */
	public Integer getPayType() {
		return this.payType;
	}
	/**
	 * 设置物流单号
	 * @param logisticsNo 物流单号
	 */
	public void setLogisticsNo(String logisticsNo) {
		this.logisticsNo = logisticsNo;
	}
	/**
	 * 获取物流单号
	 * @return 物流单号
	 */
	public String getLogisticsNo() {
		return this.logisticsNo;
	}
	/**
	 * 设置物流公司ID
	 * @param logisticsId 物流公司ID
	 */
	public void setLogisticsId(Integer logisticsId) {
		this.logisticsId = logisticsId;
	}
	/**
	 * 获取物流公司ID
	 * @return 物流公司ID
	 */
	public Integer getLogisticsId() {
		return this.logisticsId;
	}
	/**
	 * 设置其他费用
	 * @param otherFee 其他费用
	 */
	public void setOtherFee(Double otherFee) {
		this.otherFee = otherFee;
	}
	/**
	 * 获取其他费用
	 * @return 其他费用
	 */
	public Double getOtherFee() {
		return this.otherFee;
	}
	/**
	 * 设置合计总费用
	 * @param totalFee 合计总费用
	 */
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
	/**
	 * 获取合计总费用
	 * @return 合计总费用
	 */
	public Double getTotalFee() {
		return this.totalFee;
	}
	/**
	 * 设置支付订单号
	 * @param payNo 支付订单号
	 */
	public void setPayNo(String payNo) {
		this.payNo = payNo;
	}
	/**
	 * 获取支付订单号
	 * @return 支付订单号
	 */
	public String getPayNo() {
		return this.payNo;
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
	/**
	 * 设置优惠费用
	 * @param discountFee 优惠费用
	 */
	public void setDiscountFee(Double discountFee) {
		this.discountFee = discountFee;
	}
	/**
	 * 获取优惠费用
	 * @return 优惠费用
	 */
	public Double getDiscountFee() {
		return this.discountFee;
	}
	/**
	 * 设置支付金额
	 * @param payFee 支付金额
	 */
	public void setPayFee(Double payFee) {
		this.payFee = payFee;
	}
	/**
	 * 获取支付金额
	 * @return 支付金额
	 */
	public Double getPayFee() {
		return this.payFee;
	}
	/**
	 * 设置下单门店ID
	 * @param storeId 下单门店ID
	 */
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	/**
	 * 获取下单门店ID
	 * @return 下单门店ID
	 */
	public Integer getStoreId() {
		return this.storeId;
	}

}

