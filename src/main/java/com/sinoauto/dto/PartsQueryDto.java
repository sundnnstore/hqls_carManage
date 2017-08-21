package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 配件查询条件
 * @author 刘东
 *
 */

public class PartsQueryDto {

	@ApiModelProperty(name="配件编码")
	private String partsCode;
	@ApiModelProperty(name="配件类型ID")
	private Integer partsTypeId;
	@ApiModelProperty(name="配件名称")
	private String partsName;
	@ApiModelProperty(name="配件大分类 1.易损件 2.通用件 ID",notes="Integer")
	private Integer partsTopTypeId;
	public String getPartsCode() {
		return partsCode;
	}
	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}
	public Integer getPartsTypeId() {
		return partsTypeId;
	}
	public void setPartsTypeId(Integer partsTypeId) {
		this.partsTypeId = partsTypeId;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	public Integer getPartsTopTypeId() {
		return partsTopTypeId;
	}
	public void setPartsTopTypeId(Integer partsTopTypeId) {
		this.partsTopTypeId = partsTopTypeId;
	}
}
