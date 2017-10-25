package com.sinoauto.dto;

import java.util.List;
import com.sinoauto.dao.bean.HqlsPartsAttrExtr;
import com.sinoauto.dao.bean.HqlsPartsPic;
import io.swagger.annotations.ApiModelProperty;

/**
 * 配件新增
 * 配件编辑
 * 配件上下架
 *@author liud
 *@Date2017年8月17日下午12:58:20
 *
 */
public class PartsOperDto {
	/**
	 * 配件基础属性
	 */
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
	
	/**
	 * 配件类别
	 */
	@ApiModelProperty("配件类型ID")
	private Integer partsTypeId;
	@ApiModelProperty("配件类型（1易损件 2通用件）")
	private Integer partsType;
	@ApiModelProperty("类别名称")
	private String typeName;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("父级名称")
	private String pname;
	
	/**
	 * 配件图片
	 */
	@ApiModelProperty("配件图片集合")
	List<HqlsPartsPic> partsPics;
	
	/**
	 * 配件配置参数
	 */
	@ApiModelProperty("配件扩展参数集合")
	List<HqlsPartsAttrExtr> partsAttrExtrs;
	
	/**
	 * 配件对应车型
	 */
	@ApiModelProperty("配件对应车型")
	List<CommonDto> carModels;
	
	public Integer getPartsId() {
		return partsId;
	}
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	public String getPartsCode() {
		return partsCode;
	}
	public void setPartsCode(String partsCode) {
		this.partsCode = partsCode;
	}
	public String getPartsName() {
		return partsName;
	}
	public void setPartsName(String partsName) {
		this.partsName = partsName;
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
	public Integer getPartsBrandId() {
		return partsBrandId;
	}
	public void setPartsBrandId(Integer partsBrandId) {
		this.partsBrandId = partsBrandId;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Integer getIsUsable() {
		return isUsable;
	}
	public void setIsUsable(Integer isUsable) {
		this.isUsable = isUsable;
	}

	public Integer getPartsType() {
		return partsType;
	}
	public void setPartsType(Integer partsType) {
		this.partsType = partsType;
	}
	public List<HqlsPartsPic> getPartsPics() {
		return partsPics;
	}
	public void setPartsPics(List<HqlsPartsPic> partsPics) {
		this.partsPics = partsPics;
	}
	public List<HqlsPartsAttrExtr> getPartsAttrExtrs() {
		return partsAttrExtrs;
	}
	public void setPartsAttrExtrs(List<HqlsPartsAttrExtr> partsAttrExtrs) {
		this.partsAttrExtrs = partsAttrExtrs;
	}
	public Integer getPartsTypeId() {
		return partsTypeId;
	}
	public void setPartsTypeId(Integer partsTypeId) {
		this.partsTypeId = partsTypeId;
	}
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public Integer getPid() {
		return pid;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public List<CommonDto> getCarModels() {
		return carModels;
	}
	public void setCarModels(List<CommonDto> carModels) {
		this.carModels = carModels;
	}
	
}
