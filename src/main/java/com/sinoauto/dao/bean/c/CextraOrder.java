package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 增项订单表增项订单表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CextraOrder implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("增项订单ID")
	private Integer extraOrderId;
	@ApiModelProperty("订单ID")
	private Integer orderId;
	@ApiModelProperty("增项订单号")
	private String extraOrderNo;
	@ApiModelProperty("增项订单描述")
	private String extraProjectDesc;
	@ApiModelProperty("增项订单金额")
	private Double orderAmount;
	@ApiModelProperty("增项支付状态（0，未支付；1，已支付）")
	private Boolean payStatus;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	
	/**
	 * 设置增项订单ID
	 * @param extraOrderId 增项订单ID
	 */
	public void setExtraOrderId(Integer extraOrderId) {
		this.extraOrderId = extraOrderId;
	}
	/**
	 * 获取增项订单ID
	 * @return 增项订单ID
	 */
	public Integer getExtraOrderId() {
		return this.extraOrderId;
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
	 * 设置增项订单号
	 * @param extraOrderNo 增项订单号
	 */
	public void setExtraOrderNo(String extraOrderNo) {
		this.extraOrderNo = extraOrderNo;
	}
	/**
	 * 获取增项订单号
	 * @return 增项订单号
	 */
	public String getExtraOrderNo() {
		return this.extraOrderNo;
	}
	/**
	 * 设置增项订单描述
	 * @param extraProjectDesc 增项订单描述
	 */
	public void setExtraProjectDesc(String extraProjectDesc) {
		this.extraProjectDesc = extraProjectDesc;
	}
	/**
	 * 获取增项订单描述
	 * @return 增项订单描述
	 */
	public String getExtraProjectDesc() {
		return this.extraProjectDesc;
	}
	/**
	 * 设置增项订单金额
	 * @param orderAmount 增项订单金额
	 */
	public void setOrderAmount(Double orderAmount) {
		this.orderAmount = orderAmount;
	}
	/**
	 * 获取增项订单金额
	 * @return 增项订单金额
	 */
	public Double getOrderAmount() {
		return this.orderAmount;
	}
	/**
	 * 设置增项支付状态（0，未支付；1，已支付）
	 * @param payStatus 增项支付状态（0，未支付；1，已支付）
	 */
	public void setPayStatus(Boolean payStatus) {
		this.payStatus = payStatus;
	}
	/**
	 * 获取增项支付状态（0，未支付；1，已支付）
	 * @return 增项支付状态（0，未支付；1，已支付）
	 */
	public Boolean getPayStatus() {
		return this.payStatus;
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

