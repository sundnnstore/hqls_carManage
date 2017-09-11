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
	@ApiModelProperty("订单类型 1服务订单 2 预约表单")
	private Integer orderType;
	@ApiModelProperty("车牌")
	private String carNo;
	@ApiModelProperty("车型")
	private String carModel;
	@ApiModelProperty("故障位置")
	private String faultDesc;
	@ApiModelProperty("订单金额")
	private Double orderAmount;
	@ApiModelProperty("预计到店时间")
	private Date expectArriveTime;
	@ApiModelProperty("预计到店时间String")
	private String arriveTime;
	@ApiModelProperty("门店编码")
	private String storeCode;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("服务项目ID")
	private Integer serviceTypeId;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	@ApiModelProperty("客户头像")
	private String avatarUrl;
	@ApiModelProperty("完成时间")
	private Date finishTime;
	@ApiModelProperty("核销码")
	private String code;
	@ApiModelProperty("是否使用年卡")
	private Boolean isCard;

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

	public Boolean getIsCard() {
		return isCard;
	}

	public void setIsCard(Boolean isCard) {
		if (isCard != null) {
			this.isCard = isCard;
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

	public String getAvatarUrl() {
		return avatarUrl;
	}

	public void setAvatarUrl(String avatarUrl) {
		if (avatarUrl != null) {
			this.avatarUrl = avatarUrl;
		}
	}

	public Date getFinishTime() {
		return finishTime;
	}

	public void setFinishTime(Date finishTime) {
		if (finishTime != null) {
			this.finishTime = finishTime;
		}
	}

	public Integer getOrderType() {
		return orderType;
	}

	public void setOrderType(Integer orderType) {
		this.orderType = orderType;
	}

	public String getFaultDesc() {
		return faultDesc;
	}

	public void setFaultDesc(String faultDesc) {
		if (faultDesc != null) {
			this.faultDesc = faultDesc;
		}
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		if (remark != null) {
			this.remark = remark;
		}
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		if (code != null) {
			this.code = code;
		}
	}

}
