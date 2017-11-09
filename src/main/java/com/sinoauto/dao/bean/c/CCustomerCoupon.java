package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户优惠券表客户优惠券表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CCustomerCoupon implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户优惠券ID")
	private Integer customerCouponId;
	@ApiModelProperty("优惠券ID")
	private Integer couponId;
	@ApiModelProperty("客户车辆ID")
	private Integer customerCarId;
	@ApiModelProperty("优惠券金额")
	private Double amount;
	@ApiModelProperty("核销码")
	private String qrCode;
	@ApiModelProperty("核销二维码url")
	private String qrCodeUrl;
	@ApiModelProperty("有效期开始时间")
	private Date beginTime;
	@ApiModelProperty("有效期结束时间")
	private Date endTime;
	@ApiModelProperty("是否已使用（0，未；1，已）")
	private Boolean isUsed;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置客户优惠券ID
	 * @param customerCouponId 客户优惠券ID
	 */
	public void setCustomerCouponId(Integer customerCouponId) {
		this.customerCouponId = customerCouponId;
	}
	/**
	 * 获取客户优惠券ID
	 * @return 客户优惠券ID
	 */
	public Integer getCustomerCouponId() {
		return this.customerCouponId;
	}
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
	 * 设置客户车辆ID
	 * @param customerCarId 客户车辆ID
	 */
	public void setCustomerCarId(Integer customerCarId) {
		this.customerCarId = customerCarId;
	}
	/**
	 * 获取客户车辆ID
	 * @return 客户车辆ID
	 */
	public Integer getCustomerCarId() {
		return this.customerCarId;
	}
	/**
	 * 设置优惠券金额
	 * @param amount 优惠券金额
	 */
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	/**
	 * 获取优惠券金额
	 * @return 优惠券金额
	 */
	public Double getAmount() {
		return this.amount;
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
	 * 设置核销二维码url
	 * @param qrCodeUrl 核销二维码url
	 */
	public void setQrCodeUrl(String qrCodeUrl) {
		this.qrCodeUrl = qrCodeUrl;
	}
	/**
	 * 获取核销二维码url
	 * @return 核销二维码url
	 */
	public String getQrCodeUrl() {
		return this.qrCodeUrl;
	}
	/**
	 * 设置有效期开始时间
	 * @param beginTime 有效期开始时间
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取有效期开始时间
	 * @return 有效期开始时间
	 */
	public Date getBeginTime() {
		return this.beginTime;
	}
	/**
	 * 设置有效期结束时间
	 * @param endTime 有效期结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取有效期结束时间
	 * @return 有效期结束时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}
	/**
	 * 设置是否已使用（0，未；1，已）
	 * @param isUsed 是否已使用（0，未；1，已）
	 */
	public void setIsUsed(Boolean isUsed) {
		this.isUsed = isUsed;
	}
	/**
	 * 获取是否已使用（0，未；1，已）
	 * @return 是否已使用（0，未；1，已）
	 */
	public Boolean getIsUsed() {
		return this.isUsed;
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

