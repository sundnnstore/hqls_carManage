package com.sinoauto.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车信息
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-17 16:51:52
 */
public class ShopCartInfoDto {

	@ApiModelProperty("商品列表")
	private List<PartsDesListDto> partsDesList;
	@ApiModelProperty("总价")
	private Double totalAmount;

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

}
