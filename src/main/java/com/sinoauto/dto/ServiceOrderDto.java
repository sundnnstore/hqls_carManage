package com.sinoauto.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

public class ServiceOrderDto {

	@ApiModelProperty("服务订单ID")
	private Integer serviceOrderId;
	@ApiModelProperty("订单号")
	private String orderNo;
	@ApiModelProperty("客户名称")
	private String customerName;
	@ApiModelProperty("客户手机号")
	private String customerMobile;
	@ApiModelProperty("服务类型")
	private String serviceType;
	@ApiModelProperty("订单状态（1待完成；2已完成）")
	private Integer orderStatus;
	@ApiModelProperty("车牌")
	private String carNo;
	@ApiModelProperty("车型")
	private String carModel;
	@ApiModelProperty("订单金额")
	private Double orderAmount;
	@ApiModelProperty("预计到店时间")
	private Date expectArriveTime;
	@ApiModelProperty("预计到店时间String")
	private String arriveTime;
	@ApiModelProperty("门店编码")
	private String storeCode;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("服务项目ID")
	private Integer serviceTypeId;
	@ApiModelProperty("门店ID")
	private Integer storeId;

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

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		if (customerName != null) {
			this.customerName = customerName;
		}
	}

	public String getCustomerMobile() {
		return customerMobile;
	}

	public void setCustomerMobile(String customerMobile) {
		if (customerMobile != null) {
			this.customerMobile = customerMobile;
		}
	}

	public String getServiceType() {
		return serviceType;
	}

	public void setServiceType(String serviceType) {
		if (serviceType != null) {
			this.serviceType = serviceType;
		}
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(Integer orderStatus) {
		if (orderStatus != null) {
			this.orderStatus = orderStatus;
		}
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		if (carNo != null) {
			this.carNo = carNo;
		}
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		if (carModel != null) {
			this.carModel = carModel;
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

	public Date getExpectArriveTime() {
		return expectArriveTime;
	}

	public void setExpectArriveTime(Date expectArriveTime) {
		if (expectArriveTime != null) {
			this.expectArriveTime = expectArriveTime;
		}
	}

	public String getStoreCode() {
		return storeCode;
	}

	public void setStoreCode(String storeCode) {
		if (storeCode != null) {
			this.storeCode = storeCode;
		}
	}

	public Integer getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Integer customerId) {
		if (customerId != null) {
			this.customerId = customerId;
		}
	}

	public Integer getServiceTypeId() {
		return serviceTypeId;
	}

	public void setServiceTypeId(Integer serviceTypeId) {
		if (serviceTypeId != null) {
			this.serviceTypeId = serviceTypeId;
		}
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		if (storeId != null) {
			this.storeId = storeId;
		}
	}

	public String getArriveTime() {
		return arriveTime;
	}

	public void setArriveTime(String arriveTime) {
		if (arriveTime != null) {
			this.arriveTime = arriveTime;
		}
	}

}
