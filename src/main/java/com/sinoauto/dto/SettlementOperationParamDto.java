package com.sinoauto.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

public class SettlementOperationParamDto {

	@ApiModelProperty("购买的商品列表")
	private List<ShopCartParamDto> partsList;
	@ApiModelProperty("下单门店Id")
	private Integer storeId;
	@ApiModelProperty("其他费用")
	private Double otherFee;
	

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

}
