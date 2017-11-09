package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户优惠卡表客户优惠卡表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CCustomerCouponCard implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("客户优惠卡ID")
	private Integer customerCouponCardId;
	@ApiModelProperty("优惠卡ID")
	private Integer couponCardId;
	@ApiModelProperty("客户车辆ID")
	private Integer customerCarId;
	@ApiModelProperty("有效期开始时间")
	private Date beginTime;
	@ApiModelProperty("endTime")
	private Date endTime;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置客户优惠卡ID
	 * @param customerCouponCardId 客户优惠卡ID
	 */
	public void setCustomerCouponCardId(Integer customerCouponCardId) {
		this.customerCouponCardId = customerCouponCardId;
	}
	/**
	 * 获取客户优惠卡ID
	 * @return 客户优惠卡ID
	 */
	public Integer getCustomerCouponCardId() {
		return this.customerCouponCardId;
	}
	/**
	 * 设置优惠卡ID
	 * @param couponCardId 优惠卡ID
	 */
	public void setCouponCardId(Integer couponCardId) {
		this.couponCardId = couponCardId;
	}
	/**
	 * 获取优惠卡ID
	 * @return 优惠卡ID
	 */
	public Integer getCouponCardId() {
		return this.couponCardId;
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
	 * 设置endTime
	 * @param endTime endTime
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取endTime
	 * @return endTime
	 */
	public Date getEndTime() {
		return this.endTime;
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

