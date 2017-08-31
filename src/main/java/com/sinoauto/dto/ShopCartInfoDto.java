package com.sinoauto.dto;

import java.util.List;

import com.sinoauto.dao.bean.HqlsShipAddress;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车信息
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-17 16:51:52
 */
public class ShopCartInfoDto {

	@ApiModelProperty("订单Id")
	private Integer purchaseOrderId;
	@ApiModelProperty("商品列表")
	private List<PartsDesListDto> partsDesList;
	@ApiModelProperty("门店Id")
	private Integer storeId;
	@ApiModelProperty("总价")
	private Double totalAmount;
	@ApiModelProperty("收货地址Id")
	private Integer shipAddressId;
	@ApiModelProperty("收货地址")
	private HqlsShipAddress address;
	@ApiModelProperty("余额是否足够")
	private boolean isEnough;
	@ApiModelProperty("余额")
	private Double balance;
	@ApiModelProperty("其他费用")
	private Double otherFee;

	public List<PartsDesListDto> getPartsDesList() {
		return partsDesList;
	}

	public void setPartsDesList(List<PartsDesListDto> partsDesList) {
		if (partsDesList != null) {
			this.partsDesList = partsDesList;
		}
	}

	public Double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(Double totalAmount) {
		if (totalAmount != null) {
			this.totalAmount = totalAmount;
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

	public HqlsShipAddress getAddress() {
		return address;
	}

	public void setAddress(HqlsShipAddress address) {
		if(address != null) {
			this.address = address;
		}
		
	}

	public boolean getIsEnough() {
		return isEnough;
	}

	public void setIsEnough(boolean isEnough) {
		this.isEnough = isEnough;
	}

	public Integer getPurchaseOrderId() {
		return purchaseOrderId;
	}

	public void setPurchaseOrderId(Integer purchaseOrderId) {
		if(purchaseOrderId != null) {
			this.purchaseOrderId = purchaseOrderId;
		}
		
	}

	public Integer getShipAddressId() {
		return shipAddressId;
	}

	public void setShipAddressId(Integer shipAddressId) {
		if(shipAddressId != null) {
			this.shipAddressId = shipAddressId;
		}
		
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		if(balance != null) {
			this.balance = balance;
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

}
