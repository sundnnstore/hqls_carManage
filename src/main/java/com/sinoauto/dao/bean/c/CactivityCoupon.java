package com.sinoauto.dao.bean.c;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 活动优惠表活动优惠表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CactivityCoupon implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("活动优惠ID")
	private Integer activityCouponId;
	@ApiModelProperty("活动ID")
	private Integer activityId;
	@ApiModelProperty("优惠类型（1，优惠卡；2，优惠券）")
	private Integer activityCouponType;
	@ApiModelProperty("优惠卡/券ID")
	private Integer couponId;
	
	/**
	 * 设置活动优惠ID
	 * @param activityCouponId 活动优惠ID
	 */
	public void setActivityCouponId(Integer activityCouponId) {
		this.activityCouponId = activityCouponId;
	}
	/**
	 * 获取活动优惠ID
	 * @return 活动优惠ID
	 */
	public Integer getActivityCouponId() {
		return this.activityCouponId;
	}
	/**
	 * 设置活动ID
	 * @param activityId 活动ID
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取活动ID
	 * @return 活动ID
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * 设置优惠类型（1，优惠卡；2，优惠券）
	 * @param activityCouponType 优惠类型（1，优惠卡；2，优惠券）
	 */
	public void setActivityCouponType(Integer activityCouponType) {
		this.activityCouponType = activityCouponType;
	}
	/**
	 * 获取优惠类型（1，优惠卡；2，优惠券）
	 * @return 优惠类型（1，优惠卡；2，优惠券）
	 */
	public Integer getActivityCouponType() {
		return this.activityCouponType;
	}
	/**
	 * 设置优惠卡/券ID
	 * @param couponId 优惠卡/券ID
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取优惠卡/券ID
	 * @return 优惠卡/券ID
	 */
	public Integer getCouponId() {
		return this.couponId;
	}

}

