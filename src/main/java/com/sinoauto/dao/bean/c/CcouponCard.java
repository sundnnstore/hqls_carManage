package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 优惠卡优惠卡<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CcouponCard implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("优惠卡ID")
	private Integer couponCardId;
	@ApiModelProperty("优惠卡名称")
	private String couponCardName;
	@ApiModelProperty("优惠卡描述")
	private String couponCardDesc;
	@ApiModelProperty("优惠卡logo图片")
	private String logoUrl;
	@ApiModelProperty("是否启用")
	private Boolean isEnable;
	@ApiModelProperty("是否需要绑定车辆")
	private Boolean isNeedCar;
	@ApiModelProperty("创建人ID")
	private Integer createUserId;
	@ApiModelProperty("优惠卡价值金额")
	private Double value;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
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
	 * 设置优惠卡名称
	 * @param couponCardName 优惠卡名称
	 */
	public void setCouponCardName(String couponCardName) {
		this.couponCardName = couponCardName;
	}
	/**
	 * 获取优惠卡名称
	 * @return 优惠卡名称
	 */
	public String getCouponCardName() {
		return this.couponCardName;
	}
	/**
	 * 设置优惠卡描述
	 * @param couponCardDesc 优惠卡描述
	 */
	public void setCouponCardDesc(String couponCardDesc) {
		this.couponCardDesc = couponCardDesc;
	}
	/**
	 * 获取优惠卡描述
	 * @return 优惠卡描述
	 */
	public String getCouponCardDesc() {
		return this.couponCardDesc;
	}
	/**
	 * 设置优惠卡logo图片
	 * @param logoUrl 优惠卡logo图片
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取优惠卡logo图片
	 * @return 优惠卡logo图片
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}
	/**
	 * 设置是否启用
	 * @param isEnable 是否启用
	 */
	public void setIsEnable(Boolean isEnable) {
		this.isEnable = isEnable;
	}
	/**
	 * 获取是否启用
	 * @return 是否启用
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
	 * 设置优惠卡价值金额
	 * @param value 优惠卡价值金额
	 */
	public void setValue(Double value) {
		this.value = value;
	}
	/**
	 * 获取优惠卡价值金额
	 * @return 优惠卡价值金额
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

