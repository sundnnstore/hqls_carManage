package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 服务订单表服务订单表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsServiceOrder implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("服务订单ID")
	private Integer serviceOrderId;
	@ApiModelProperty("订单号")
	private String orderNo;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("服务类型ID")
	private Integer serviceTypeId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("订单状态（1待完成；2已完成）")
	private Integer orderStatus;
	@ApiModelProperty("车牌")
	private String carNo;
	@ApiModelProperty("车型")
	private String carModel;
	@ApiModelProperty("订单金额")
	private Double orderAmount;
	@ApiModelProperty("预计到店时间")
	private Date expectArriveTime;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;

	/**
	 * 设置服务订单ID
	 * @param serviceOrderId 服务订单ID
	 */
	public void setServiceOrderId(Integer serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}

	/**
	 * 获取服务订单ID
	 * @return 服务订单ID
	 */
	public Integer getServiceOrderId() {
		return this.serviceOrderId;
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
	 * 设置服务类型ID
	 * @param serviceTypeId 服务类型ID
	 */
	public void setServiceTypeId(Integer serviceTypeId) {
		this.serviceTypeId = serviceTypeId;
	}

	/**
	 * 获取服务类型ID
	 * @return 服务类型ID
	 */
	public Integer getServiceTypeId() {
		return this.serviceTypeId;
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
	 * 设置订单状态（1待完成；2已完成）
	 * @param orderStatus 订单状态（1待完成；2已完成）
	 */
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}

	/**
	 * 获取订单状态（1待完成；2已完成）
	 * @return 订单状态（1待完成；2已完成）
	 */
	public Integer getOrderStatus() {
		return this.orderStatus;
	}

	/**
	 * 设置车牌
	 * @param carNo 车牌
	 */
	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	/**
	 * 获取车牌
	 * @return 车牌
	 */
	public String getCarNo() {
		return this.carNo;
	}

	/**
	 * 设置车型
	 * @param carModel 车型
	 */
	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	/**
	 * 获取车型
	 * @return 车型
	 */
	public String getCarModel() {
		return this.carModel;
	}

	/**
	 * 设置订单金额
	 * @param orderAmount 订单金额
	 */
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}

	/**
	 * 获取订单金额
	 * @return 订单金额
	 */
	public Double getOrderAmount() {
		return this.orderAmount;
	}

	public Date getExpectArriveTime() {
		return expectArriveTime;
	}

	public void setExpectArriveTime(Date expectArriveTime) {
		if (expectArriveTime != null) {
			this.expectArriveTime = expectArriveTime;
		}
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

}
