package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 订单详情表订单详情表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsOrderDetail implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("订单详情ID")
	private Integer orderDetailId;
	@ApiModelProperty("采购订单ID")
	private Integer purchaseOrderId;
	@ApiModelProperty("配件ID")
	private Integer partsId;
	@ApiModelProperty("下单时价格")
	private Double buyPrice;
	@ApiModelProperty("下单时数量")
	private Integer buyCount;
	@ApiModelProperty("优惠价格")
	private Double discountPrice;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置订单详情ID
	 * @param orderDetailId 订单详情ID
	 */
	public void setOrderDetailId(Integer orderDetailId) {
		this.orderDetailId = orderDetailId;
	}
	/**
	 * 获取订单详情ID
	 * @return 订单详情ID
	 */
	public Integer getOrderDetailId() {
		return this.orderDetailId;
	}
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
	 * 设置配件ID
	 * @param partsId 配件ID
	 */
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	/**
	 * 获取配件ID
	 * @return 配件ID
	 */
	public Integer getPartsId() {
		return this.partsId;
	}
	/**
	 * 设置下单时价格
	 * @param buyPrice 下单时价格
	 */
	public void setBuyPrice(Double buyPrice) {
		this.buyPrice = buyPrice;
	}
	/**
	 * 获取下单时价格
	 * @return 下单时价格
	 */
	public Double getBuyPrice() {
		return this.buyPrice;
	}
	/**
	 * 设置下单时数量
	 * @param buyCount 下单时数量
	 */
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	/**
	 * 获取下单时数量
	 * @return 下单时数量
	 */
	public Integer getBuyCount() {
		return this.buyCount;
	}
	/**
	 * 设置优惠价格
	 * @param discountPrice 优惠价格
	 */
	public void setDiscountPrice(Double discountPrice) {
		this.discountPrice = discountPrice;
	}
	/**
	 * 获取优惠价格
	 * @return 优惠价格
	 */
	public Double getDiscountPrice() {
		return this.discountPrice;
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

