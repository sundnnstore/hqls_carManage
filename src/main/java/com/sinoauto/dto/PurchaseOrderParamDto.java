package com.sinoauto.dto;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 生成订单参数Dto
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-17 14:18:17
 */
public class PurchaseOrderParamDto {

	@ApiModelProperty("下单门店Id")
	private Integer storeId;
	@ApiModelProperty("购买商品集合")
	private List<PartsDesListDto> partsList;
	@ApiModelProperty("收货地址")
	private Integer shipAddressId;
	@ApiModelProperty("其他费用")
	private Double otherFee;
	@ApiModelProperty("备注")
	private String remark;

	public List<PartsDesListDto> getPartsList() {
		return partsList;
	}

	public void setPartsList(List<PartsDesListDto> partsList) {
		if (partsList != null) {
			this.partsList = partsList;
		}
	}

	public Integer getShipAddressId() {
		return shipAddressId;
	}

	public void setShipAddressId(Integer shipAddressId) {
		if (shipAddressId != null) {
			this.shipAddressId = shipAddressId;
		}
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		if(storeId != null) {
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

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		if(remark != null) {
			this.remark = remark;
		}
		
	}
	

}
