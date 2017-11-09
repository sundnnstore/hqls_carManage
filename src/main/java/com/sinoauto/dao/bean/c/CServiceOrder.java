package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 服务订单表服务订单表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CServiceOrder implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("服务订单ID")
	private Integer orderId;
	@ApiModelProperty("服务订单号")
	private String orderNo;
	@ApiModelProperty("订单状态（1、待支付2、待服务3、预约待服务4、已完成5、已取消）")
	private Integer orderStatus;
	@ApiModelProperty("订单类型（1、服务订单2、预约订单）")
	private Integer orderType;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("联系人")
	private String contacts;
	@ApiModelProperty("联系人电话")
	private String contactsMobile;
	@ApiModelProperty("顾客ID")
	private Integer customerId;
	@ApiModelProperty("顾客车辆ID")
	private Integer customerCarId;
	@ApiModelProperty("服务时间（11月08日 上午）")
	private String serviceTime;
	@ApiModelProperty("车辆问题描述（维修预约）")
	private String faultDesc;
	@ApiModelProperty("服务项目ID")
	private Integer serviceProjectId;
	@ApiModelProperty("orderTotalAmount")
	private Double orderTotalAmount;
	@ApiModelProperty("优惠金额")
	private Double discountAmount;
	@ApiModelProperty("实际支付金额")
	private Double actualPaymentAmount;
	@ApiModelProperty("核销码")
	private String qrCode;
	@ApiModelProperty("二维码url")
	private String qrCodeUrl;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置服务订单ID
	 * @param orderId 服务订单ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取服务订单ID
	 * @return 服务订单ID
	 */
	public Integer getOrderId() {
		return this.orderId;
	}
	/**
	 * 设置服务订单号
	 * @param orderNo 服务订单号
	 */
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	/**
	 * 获取服务订单号
	 * @return 服务订单号
	 */
	public String getOrderNo() {
		return this.orderNo;
	}
	/**
	 * 设置订单状态（1、待支付2、待服务3、预约待服务4、已完成5、已取消）
	 * @param orderStatus 订单状态（1、待支付2、待服务3、预约待服务4、已完成5、已取消）
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	/**
	 * 获取订单状态（1、待支付2、待服务3、预约待服务4、已完成5、已取消）
	 * @return 订单状态（1、待支付2、待服务3、预约待服务4、已完成5、已取消）
	 */
	public Integer getOrderStatus() {
		return this.orderStatus;
	}
	/**
	 * 设置订单类型（1、服务订单2、预约订单）
	 * @param orderType 订单类型（1、服务订单2、预约订单）
	 */
	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}
	/**
	 * 获取订单类型（1、服务订单2、预约订单）
	 * @return 订单类型（1、服务订单2、预约订单）
	 */
	public Integer getOrderType() {
		return this.orderType;
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
	 * 设置联系人
	 * @param contacts 联系人
	 */
	public void setContacts(String contacts) {
		this.contacts = contacts;
	}
	/**
	 * 获取联系人
	 * @return 联系人
	 */
	public String getContacts() {
		return this.contacts;
	}
	/**
	 * 设置联系人电话
	 * @param contactsMobile 联系人电话
	 */
	public void setContactsMobile(String contactsMobile) {
		this.contactsMobile = contactsMobile;
	}
	/**
	 * 获取联系人电话
	 * @return 联系人电话
	 */
	public String getContactsMobile() {
		return this.contactsMobile;
	}
	/**
	 * 设置顾客ID
	 * @param customerId 顾客ID
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取顾客ID
	 * @return 顾客ID
	 */
	public Integer getCustomerId() {
		return this.customerId;
	}
	/**
	 * 设置顾客车辆ID
	 * @param customerCarId 顾客车辆ID
	 */
	public void setCustomerCarId(Integer customerCarId) {
		this.customerCarId = customerCarId;
	}
	/**
	 * 获取顾客车辆ID
	 * @return 顾客车辆ID
	 */
	public Integer getCustomerCarId() {
		return this.customerCarId;
	}
	/**
	 * 设置服务时间（11月08日 上午）
	 * @param serviceTime 服务时间（11月08日 上午）
	 */
	public void setServiceTime(String serviceTime) {
		this.serviceTime = serviceTime;
	}
	/**
	 * 获取服务时间（11月08日 上午）
	 * @return 服务时间（11月08日 上午）
	 */
	public String getServiceTime() {
		return this.serviceTime;
	}
	/**
	 * 设置车辆问题描述（维修预约）
	 * @param faultDesc 车辆问题描述（维修预约）
	 */
	public void setFaultDesc(String faultDesc) {
		this.faultDesc = faultDesc;
	}
	/**
	 * 获取车辆问题描述（维修预约）
	 * @return 车辆问题描述（维修预约）
	 */
	public String getFaultDesc() {
		return this.faultDesc;
	}
	/**
	 * 设置服务项目ID
	 * @param serviceProjectId 服务项目ID
	 */
	public void setServiceProjectId(Integer serviceProjectId) {
		this.serviceProjectId = serviceProjectId;
	}
	/**
	 * 获取服务项目ID
	 * @return 服务项目ID
	 */
	public Integer getServiceProjectId() {
		return this.serviceProjectId;
	}
	/**
	 * 设置orderTotalAmount
	 * @param orderTotalAmount orderTotalAmount
	 */
	public void setOrderTotalAmount(Double orderTotalAmount) {
		this.orderTotalAmount = orderTotalAmount;
	}
	/**
	 * 获取orderTotalAmount
	 * @return orderTotalAmount
	 */
	public Double getOrderTotalAmount() {
		return this.orderTotalAmount;
	}
	/**
	 * 设置优惠金额
	 * @param discountAmount 优惠金额
	 */
	public void setDiscountAmount(Double discountAmount) {
		this.discountAmount = discountAmount;
	}
	/**
	 * 获取优惠金额
	 * @return 优惠金额
	 */
	public Double getDiscountAmount() {
		return this.discountAmount;
	}
	/**
	 * 设置实际支付金额
	 * @param actualPaymentAmount 实际支付金额
	 */
	public void setActualPaymentAmount(Double actualPaymentAmount) {
		this.actualPaymentAmount = actualPaymentAmount;
	}
	/**
	 * 获取实际支付金额
	 * @return 实际支付金额
	 */
	public Double getActualPaymentAmount() {
		return this.actualPaymentAmount;
	}
	/**
	 * 设置核销码
	 * @param qrCode 核销码
	 */
	public void setQrCode(String qrCode) {
		this.qrCode = qrCode;
	}
	/**
	 * 获取核销码
	 * @return 核销码
	 */
	public String getQrCode() {
		return this.qrCode;
	}
	/**
	 * 设置二维码url
	 * @param qrCodeUrl 二维码url
	 */
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	/**
	 * 获取二维码url
	 * @return 二维码url
	 */
	public String getQrCodeUrl() {
		return this.qrCodeUrl;
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

