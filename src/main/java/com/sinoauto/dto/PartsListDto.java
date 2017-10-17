package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 采购分类--配件展示列表dto
 * @author Administrator
 *
 */
public class PartsListDto {

	@ApiModelProperty(name="配件Id")
	private Integer partsId;
	@ApiModelProperty(name="配件名称")
	private String partsName;
	@ApiModelProperty(name="配件价格")
	private Double partsPrice;
	@ApiModelProperty(name="配件图片")
	private String picUrl;
	@ApiModelProperty(name="配件类型Id")
	private Integer partsTypeId;
	
	public Integer getPartsId() {
		return partsId;
	}
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public Double getPartsPrice() {
		return partsPrice;
	}
	public void setPartsPrice(Double partsPrice) {
		this.partsPrice = partsPrice;
	}
	public String getPicUrl() {
		return picUrl;
	}
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}
	public Integer getPartsTypeId() {
		return partsTypeId;
	}
	public void setPartsTypeId(Integer partsTypeId) {
		this.partsTypeId = partsTypeId;
	}
	
}
