package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 物流日志表物流日志表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsLogisticsLog implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("物流日志ID")
	private Integer logId;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("订单主键ID")
	private Integer purchaseOrderId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置物流日志ID
	 * @param logId 物流日志ID
	 */
	public void setLogId(Integer logId) {
		this.logId = logId;
	}
	/**
	 * 获取物流日志ID
	 * @return 物流日志ID
	 */
	public Integer getLogId() {
		return this.logId;
	}
	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 设置订单主键ID
	 * @param purchaseOrderId 订单主键ID
	 */
	public void setPurchaseOrderId(Integer purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}
	/**
	 * 获取订单主键ID
	 * @return 订单主键ID
	 */
	public Integer getPurchaseOrderId() {
		return this.purchaseOrderId;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		if (createTime != null) {
			this.createTime = createTime;
		}
	}
	

}

