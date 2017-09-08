package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

public class HqlsExtraOrder implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer extraOrderId;
	private Integer serviceOrderId;
	private String orderNo;
	private String extraOrderNo;
	private String extraProjectDesc;
	private Double orderAmount;
	private Integer payStatus;
	private Date createTime;
	private Date dmlTime;

	public Integer getExtraOrderId() {
		return extraOrderId;
	}

	public void setExtraOrderId(Integer extraOrderId) {
		if (extraOrderId != null) {
			this.extraOrderId = extraOrderId;
		}
	}

	public Integer getServiceOrderId() {
		return serviceOrderId;
	}

	public void setServiceOrderId(Integer serviceOrderId) {
		if (serviceOrderId != null) {
			this.serviceOrderId = serviceOrderId;
		}
	}

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		if (orderNo != null) {
			this.orderNo = orderNo;
		}
	}

	public String getExtraOrderNo() {
		return extraOrderNo;
	}

	public void setExtraOrderNo(String extraOrderNo) {
		if (extraOrderNo != null) {
			this.extraOrderNo = extraOrderNo;
		}
	}

	public String getExtraProjectDesc() {
		return extraProjectDesc;
	}

	public void setExtraProjectDesc(String extraProjectDesc) {
		if (extraProjectDesc != null) {
			this.extraProjectDesc = extraProjectDesc;
		}
	}

	public Double getOrderAmount() {
		return orderAmount;
	}

	public void setOrderAmount(Double orderAmount) {
		if (orderAmount != null) {
			this.orderAmount = orderAmount;
		}
	}

	public Integer getPayStatus() {
		return payStatus;
	}

	public void setPayStatus(Integer payStatus) {
		if (payStatus != null) {
			this.payStatus = payStatus;
		}
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		if (createTime != null) {
			this.createTime = createTime;
		}
	}

	public Date getDmlTime() {
		return dmlTime;
	}

	public void setDmlTime(Date dmlTime) {
		if (dmlTime != null) {
			this.dmlTime = dmlTime;
		}
	}

}
