package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 服务订单优惠表服务订单优惠表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CserviceOrderCoupon implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("服务订单优惠ID")
	private Integer orderCouponId;
	@ApiModelProperty("订单ID")
	private Integer orderId;
	@ApiModelProperty("优惠类型（1、优惠券2、优惠卡）")
	private Integer couponType;
	@ApiModelProperty("优惠券/卡ID")
	private Integer couponId;
	@ApiModelProperty("优惠金额")
	private Double discountAmount;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置服务订单优惠ID
	 * @param orderCouponId 服务订单优惠ID
	 */
	public void setOrderCouponId(Integer orderCouponId) {
		this.orderCouponId = orderCouponId;
	}
	/**
	 * 获取服务订单优惠ID
	 * @return 服务订单优惠ID
	 */
	public Integer getOrderCouponId() {
		return this.orderCouponId;
	}
	/**
	 * 设置订单ID
	 * @param orderId 订单ID
	 */
	public void setOrderId(Integer orderId) {
		this.orderId = orderId;
	}
	/**
	 * 获取订单ID
	 * @return 订单ID
	 */
	public Integer getOrderId() {
		return this.orderId;
	}
	/**
	 * 设置优惠类型（1、优惠券2、优惠卡）
	 * @param couponType 优惠类型（1、优惠券2、优惠卡）
	 */
	public void setCouponType(Integer couponType) {
		this.couponType = couponType;
	}
	/**
	 * 获取优惠类型（1、优惠券2、优惠卡）
	 * @return 优惠类型（1、优惠券2、优惠卡）
	 */
	public Integer getCouponType() {
		return this.couponType;
	}
	/**
	 * 设置优惠券/卡ID
	 * @param couponId 优惠券/卡ID
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取优惠券/卡ID
	 * @return 优惠券/卡ID
	 */
	public Integer getCouponId() {
		return this.couponId;
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

