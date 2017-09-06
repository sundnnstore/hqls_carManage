package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 配件描述列表Dto
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-16 18:15:31
 */
public class PartsDesListDto {

	@ApiModelProperty("配件ID")
	private Integer partsId;
	@ApiModelProperty("配件名称")
	private String partsName;
	@ApiModelProperty("型号")
	private String partsModel;
	@ApiModelProperty("单位")
	private String partsUnit;
	@ApiModelProperty("规格")
	private String partsSpec;
	@ApiModelProperty("配件品牌名称")
	private String partsBrandName;
	@ApiModelProperty("现价")
	private Double curPrice;
	@ApiModelProperty("原价")
	private Double price;
	@ApiModelProperty("封面图")
	private String partsPic;
	@ApiModelProperty("下单时价格")
	private Double buyPrice;
	@ApiModelProperty("购买数量")
	private Integer purchaseNum;
	@ApiModelProperty("上下架")
	private Integer isUsable;

	public Integer getPartsId() {
		return partsId;
	}

	public void setPartsId(Integer partsId) {
		if (partsId != null) {
			this.partsId = partsId;
		}
	}

	public String getPartsName() {
		return partsName;
	}

	public void setPartsName(String partsName) {
		if (partsName != null) {
			this.partsName = partsName;
		}
	}

	public String getPartsModel() {
		return partsModel;
	}

	public void setPartsModel(String partsModel) {
		if (partsModel != null) {
			this.partsModel = partsModel;
		}
	}

	public String getPartsUnit() {
		return partsUnit;
	}

	public void setPartsUnit(String partsUnit) {
		if (partsUnit != null) {
			this.partsUnit = partsUnit;
		}
	}

	public String getPartsSpec() {
		return partsSpec;
	}

	public void setPartsSpec(String partsSpec) {
		if (partsSpec != null) {
			this.partsSpec = partsSpec;
		}
	}

	public Double getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(Double curPrice) {
		if (curPrice != null) {
			this.curPrice = curPrice;
		}
	}

	public String getPartsPic() {
		return partsPic;
	}

	public void setPartsPic(String partsPic) {
		if (partsPic != null) {
			this.partsPic = partsPic;
		}
	}

	public Integer getPurchaseNum() {
		return purchaseNum;
	}

	public void setPurchaseNum(Integer purchaseNum) {
		if (purchaseNum != null) {
			this.purchaseNum = purchaseNum;
		}
	}

	public Double getBuyPrice() {
		return buyPrice;
	}

	public void setBuyPrice(Double buyPrice) {
		if (buyPrice != null) {
			this.buyPrice = buyPrice;
		}
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		if (price != null) {
			this.price = price;
		}
	}

	public Integer getIsUsable() {
		return isUsable;
	}

	public void setIsUsable(Integer isUsable) {
		if (isUsable != null) {
			this.isUsable = isUsable;
		}
	}

	public String getPartsBrandName() {
		return partsBrandName;
	}

	public void setPartsBrandName(String partsBrandName) {
		if(partsBrandName != null) {
			this.partsBrandName = partsBrandName;
		}
		
	}
	
}
