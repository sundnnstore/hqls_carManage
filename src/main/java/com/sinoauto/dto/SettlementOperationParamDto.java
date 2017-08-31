package com.sinoauto.dto;

import java.util.List;

public class SettlementOperationParamDto {

	private Integer orderId;
	private List<ShopCartParamDto> partsList;
	private Integer storeId;
	private Double otherFee;
	private Integer payType;
	private Double payMoney;
	private Integer addressId;

	public List<ShopCartParamDto> getPartsList() {
		return partsList;
	}

	public void setPartsList(List<ShopCartParamDto> partsList) {
		if (partsList != null) {
			this.partsList = partsList;
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

	public Double getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(Double otherFee) {
		if (otherFee != null) {
			this.otherFee = otherFee;
		}

	}

	public Integer getPayType() {
		return payType;
	}

	public void setPayType(Integer payType) {
		if(payType != null) {
			this.payType = payType;
		}
		
	}

	public Double getPayMoney() {
		return payMoney;
	}

	public void setPayMoney(Double payMoney) {
		if(payMoney != null) {
			this.payMoney = payMoney;
		}
		
	}

	public Integer getAddressId() {
		return addressId;
	}

	public void setAddressId(Integer addressId) {
		if(addressId != null) {
			this.addressId = addressId;
		}
		
	}

	public Integer getOrderId() {
		return orderId;
	}

	public void setOrderId(Integer orderId) {
		if(orderId != null) {
			this.orderId = orderId;
		}
		
	}

}
