package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 配件属性扩展表配件属性扩展表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsPartsAttrExtr implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("配件属性扩展ID")
	private Integer partsAttrExtrId;
	@ApiModelProperty("配件ID")
	private Integer partsId;
	@ApiModelProperty("属性名")
	private String attrKey;
	@ApiModelProperty("属性值")
	private String attrValue;
	
	/**
	 * 设置配件属性扩展ID
	 * @param partsAttrExtrId 配件属性扩展ID
	 */
	public void setPartsAttrExtrId(Integer partsAttrExtrId) {
		this.partsAttrExtrId = partsAttrExtrId;
	}
	/**
	 * 获取配件属性扩展ID
	 * @return 配件属性扩展ID
	 */
	public Integer getPartsAttrExtrId() {
		return this.partsAttrExtrId;
	}
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
	 * 设置属性名
	 * @param attrKey 属性名
	 */
	public void setAttrKey(String attrKey) {
		this.attrKey = attrKey;
	}
	/**
	 * 获取属性名
	 * @return 属性名
	 */
	public String getAttrKey() {
		return this.attrKey;
	}
	/**
	 * 设置属性值
	 * @param attrValue 属性值
	 */
	public void setAttrValue(String attrValue) {
		this.attrValue = attrValue;
	}
	/**
	 * 获取属性值
	 * @return 属性值
	 */
	public String getAttrValue() {
		return this.attrValue;
	}

}

