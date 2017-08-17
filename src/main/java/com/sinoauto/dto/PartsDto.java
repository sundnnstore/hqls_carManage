package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *@author liud
 *@Date2017年8月17日上午10:57:39
 *
 *	1.查询条件 
 *	3.返回的集合
 *		共用属性
 *
 */
public class PartsDto {
	/**
	 * 查询条件,返回对象共用属性
	 */
	@ApiModelProperty(name="配件编码")
	private String partsCode;
	@ApiModelProperty(name="配件分类ID")
	private Integer partsTypeId;
	@ApiModelProperty(name="配件名称")
	private String partsName;
	@ApiModelProperty("型号")
	private String partsModel;
	@ApiModelProperty("单位")
	private String partsUnit;
	@ApiModelProperty("规格")
	private String partsSpec;
	@ApiModelProperty("厂家")
	private String partsFactory;
	@ApiModelProperty("产地")
	private String origin;
	@ApiModelProperty("保质期")
	private String shelfLife;
	@ApiModelProperty("价格")
	private Double price;
	@ApiModelProperty("折扣")
	private Double discount;
	@ApiModelProperty("现价")
	private Double curPrice;
	@ApiModelProperty(name="配件大分类 1.易损件 2.通用件 ID",notes="Integer")
	private Integer partsTopTypeId;
	@ApiModelProperty(name="配件大分类名称")
	private String partsTopType;
	/**
	 * 返回对象属性
	 */
	@ApiModelProperty(name="配件父类ID")
	private Integer pid;
	@ApiModelProperty(name="配件父类名称")
	private String pName;
	@ApiModelProperty(name="配件ID")
	private Integer partsId;
	/**
	 * 保留字段
	 */
	@ApiModelProperty(name="配件父类拼接字符串 例如 一级+二级+三级")
	private String partsParentStr;

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
	
	public String getPartsTopType() {
		return partsTopType;
	}

	public void setPartsTopType(String partsTopType) {
		this.partsTopType = partsTopType;
	}

	public Integer getPartsId() {
		return partsId;
	}

	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}

	public String getPartsParentStr() {
		return partsParentStr;
	}

	public void setPartsParentStr(String partsParentStr) {
		this.partsParentStr = partsParentStr;
	}

	public String getPartsModel() {
		return partsModel;
	}

	public void setPartsModel(String partsModel) {
		this.partsModel = partsModel;
	}

	public String getPartsUnit() {
		return partsUnit;
	}

	public void setPartsUnit(String partsUnit) {
		this.partsUnit = partsUnit;
	}

	public String getPartsSpec() {
		return partsSpec;
	}

	public void setPartsSpec(String partsSpec) {
		this.partsSpec = partsSpec;
	}

	public String getPartsFactory() {
		return partsFactory;
	}

	public void setPartsFactory(String partsFactory) {
		this.partsFactory = partsFactory;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		this.origin = origin;
	}

	public String getShelfLife() {
		return shelfLife;
	}

	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	public Double getCurPrice() {
		return curPrice;
	}

	public void setCurPrice(Double curPrice) {
		this.curPrice = curPrice;
	}

	public Integer getPartsTopTypeId() {
		return partsTopTypeId;
	}

	public void setPartsTopTypeId(Integer partsTopTypeId) {
		this.partsTopTypeId = partsTopTypeId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getpName() {
		return pName;
	}

	public void setpName(String pName) {
		this.pName = pName;
	}
}
