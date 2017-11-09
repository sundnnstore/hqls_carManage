package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 优惠券优惠券<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CCoupon implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("优惠券ID")
	private Integer couponId;
	@ApiModelProperty("优惠券名称")
	private String couponName;
	@ApiModelProperty("优惠券描述")
	private String couponDesc;
	@ApiModelProperty("优惠券logo图片")
	private String logoUrl;
	@ApiModelProperty("是否启用（0，否；1，是）")
	private Boolean isEnable;
	@ApiModelProperty("是否需要绑定车辆")
	private Boolean isNeedCar;
	@ApiModelProperty("是否抵扣金额")
	private Boolean isDiscount;
	@ApiModelProperty("创建人ID")
	private Integer createUserId;
	@ApiModelProperty("优惠券金额")
	private Double value;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置优惠券ID
	 * @param couponId 优惠券ID
	 */
	public void setCouponId(Integer couponId) {
		this.couponId = couponId;
	}
	/**
	 * 获取优惠券ID
	 * @return 优惠券ID
	 */
	public Integer getCouponId() {
		return this.couponId;
	}
	/**
	 * 设置优惠券名称
	 * @param couponName 优惠券名称
	 */
	public void setCouponName(String couponName) {
		this.couponName = couponName;
	}
	/**
	 * 获取优惠券名称
	 * @return 优惠券名称
	 */
	public String getCouponName() {
		return this.couponName;
	}
	/**
	 * 设置优惠券描述
	 * @param couponDesc 优惠券描述
	 */
	public void setCouponDesc(String couponDesc) {
		this.couponDesc = couponDesc;
	}
	/**
	 * 获取优惠券描述
	 * @return 优惠券描述
	 */
	public String getCouponDesc() {
		return this.couponDesc;
	}
	/**
	 * 设置优惠券logo图片
	 * @param logoUrl 优惠券logo图片
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取优惠券logo图片
	 * @return 优惠券logo图片
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}
	/**
	 * 设置是否启用（0，否；1，是）
	 * @param isEnable 是否启用（0，否；1，是）
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * 获取是否启用（0，否；1，是）
	 * @return 是否启用（0，否；1，是）
	 */
	public Boolean getIsEnable() {
		return this.isEnable;
	}
	/**
	 * 设置是否需要绑定车辆
	 * @param isNeedCar 是否需要绑定车辆
	 */
	public void setIsNeedCar(Boolean isNeedCar) {
		this.isNeedCar = isNeedCar;
	}
	/**
	 * 获取是否需要绑定车辆
	 * @return 是否需要绑定车辆
	 */
	public Boolean getIsNeedCar() {
		return this.isNeedCar;
	}
	/**
	 * 设置是否抵扣金额
	 * @param isDiscount 是否抵扣金额
	 */
	public void setIsDiscount(Boolean isDiscount) {
		this.isDiscount = isDiscount;
	}
	/**
	 * 获取是否抵扣金额
	 * @return 是否抵扣金额
	 */
	public Boolean getIsDiscount() {
		return this.isDiscount;
	}
	/**
	 * 设置创建人ID
	 * @param createUserId 创建人ID
	 */
	public void setCreateUserId(Integer createUserId) {
		this.createUserId = createUserId;
	}
	/**
	 * 获取创建人ID
	 * @return 创建人ID
	 */
	public Integer getCreateUserId() {
		return this.createUserId;
	}
	/**
	 * 设置优惠券金额
	 * @param value 优惠券金额
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	/**
	 * 获取优惠券金额
	 * @return 优惠券金额
	 */
	public Double getValue() {
		return this.value;
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

