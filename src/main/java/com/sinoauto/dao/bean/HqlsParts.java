package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 配件表配件表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsParts implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("配件ID")
	private Integer partsId;
	@ApiModelProperty("配件编码")
	private String partsCode;
	@ApiModelProperty("配件名称")
	private String partsName;
	@ApiModelProperty("型号")
	private String partsModel;
	@ApiModelProperty("单位")
	private String partsUnit;
	@ApiModelProperty("规格")
	private String partsSpec;
	@ApiModelProperty("配件品牌ID")
	private Integer partsBrandId;
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
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("上下架")
	private Integer isUsable;
	@ApiModelProperty("类别ID")
	private Integer partsTypeId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	
	/**
	 * 设置配件ID
	 * @param partsId 配件ID
	 */
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	/**
	 * 获取配件ID
	 * @return 配件ID
	 */
	public Integer getPartsId() {
		return this.partsId;
	}
	/**
	 * 设置配件编码
	 * @param partsCode 配件编码
	 */
	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}
	/**
	 * 获取配件编码
	 * @return 配件编码
	 */
	public String getPartsCode() {
		return this.partsCode;
	}
	/**
	 * 设置配件名称
	 * @param partsName 配件名称
	 */
	public void setPartsName(String partsName) {
		this.partsName = partsName;
	}
	/**
	 * 获取配件名称
	 * @return 配件名称
	 */
	public String getPartsName() {
		return this.partsName;
	}
	/**
	 * 设置型号
	 * @param partsModel 型号
	 */
	public void setPartsModel(String partsModel) {
		this.partsModel = partsModel;
	}
	/**
	 * 获取型号
	 * @return 型号
	 */
	public String getPartsModel() {
		return this.partsModel;
	}
	/**
	 * 设置单位
	 * @param partsUnit 单位
	 */
	public void setPartsUnit(String partsUnit) {
		this.partsUnit = partsUnit;
	}
	/**
	 * 获取单位
	 * @return 单位
	 */
	public String getPartsUnit() {
		return this.partsUnit;
	}
	/**
	 * 设置规格
	 * @param partsSpec 规格
	 */
	public void setPartsSpec(String partsSpec) {
		this.partsSpec = partsSpec;
	}
	/**
	 * 获取规格
	 * @return 规格
	 */
	public String getPartsSpec() {
		return this.partsSpec;
	}
	/**
	 * 设置配件品牌ID
	 * @param partsBrandId 配件品牌ID
	 */
	public void setPartsBrandId(Integer partsBrandId) {
		this.partsBrandId = partsBrandId;
	}
	/**
	 * 获取配件品牌ID
	 * @return 配件品牌ID
	 */
	public Integer getPartsBrandId() {
		return this.partsBrandId;
	}
	/**
	 * 设置厂家
	 * @param partsFactory 厂家
	 */
	public void setPartsFactory(String partsFactory) {
		this.partsFactory = partsFactory;
	}
	/**
	 * 获取厂家
	 * @return 厂家
	 */
	public String getPartsFactory() {
		return this.partsFactory;
	}
	/**
	 * 设置产地
	 * @param origin 产地
	 */
	public void setOrigin(String origin) {
		this.origin = origin;
	}
	/**
	 * 获取产地
	 * @return 产地
	 */
	public String getOrigin() {
		return this.origin;
	}
	/**
	 * 设置保质期
	 * @param shelfLife 保质期
	 */
	public void setShelfLife(String shelfLife) {
		this.shelfLife = shelfLife;
	}
	/**
	 * 获取保质期
	 * @return 保质期
	 */
	public String getShelfLife() {
		return this.shelfLife;
	}
	/**
	 * 设置价格
	 * @param price 价格
	 */
	public void setPrice(Double price) {
		this.price = price;
	}
	/**
	 * 获取价格
	 * @return 价格
	 */
	public Double getPrice() {
		return this.price;
	}
	/**
	 * 设置折扣
	 * @param discount 折扣
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/**
	 * 获取折扣
	 * @return 折扣
	 */
	public Double getDiscount() {
		return this.discount;
	}
	/**
	 * 设置现价
	 * @param curPrice 现价
	 */
	public void setCurPrice(Double curPrice) {
		this.curPrice = curPrice;
	}
	/**
	 * 获取现价
	 * @return 现价
	 */
	public Double getCurPrice() {
		return this.curPrice;
	}
	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 设置上下架
	 * @param isUsable 上下架
	 */
	public void setIsUsable(Integer isUsable) {
		this.isUsable = isUsable;
	}
	/**
	 * 获取上下架
	 * @return 上下架
	 */
	public Integer getIsUsable() {
		return this.isUsable;
	}
	/**
	 * 设置类别ID
	 * @param partsTypeId 类别ID
	 */
	public void setPartsTypeId(Integer partsTypeId) {
		this.partsTypeId = partsTypeId;
	}
	/**
	 * 获取类别ID
	 * @return 类别ID
	 */
	public Integer getPartsTypeId() {
		return this.partsTypeId;
	}
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * 设置操作时间
	 * @param dmlTime 操作时间
	 */
	public void setDmlTime(Date dmlTime) {
		this.dmlTime = dmlTime;
	}
	/**
	 * 获取操作时间
	 * @return 操作时间
	 */
	public Date getDmlTime() {
		return this.dmlTime;
	}

}

