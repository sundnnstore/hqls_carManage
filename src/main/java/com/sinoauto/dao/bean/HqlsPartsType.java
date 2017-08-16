package com.sinoauto.dao.bean;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;



/**
 * 配件类别表配件类别表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsPartsType implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("类别ID")
	private Integer partsTypeId;
	@ApiModelProperty("类别名称")
	private String typeName;
	@ApiModelProperty("父级ID")
	private Integer pid;
	@ApiModelProperty("配件类型（1易损件 2车型件）")
	private Integer partsType;
	
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
	 * 设置类别名称
	 * @param typeName 类别名称
	 */
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	/**
	 * 获取类别名称
	 * @return 类别名称
	 */
	public String getTypeName() {
		return this.typeName;
	}
	/**
	 * 设置父级ID
	 * @param pid 父级ID
	 */
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	/**
	 * 获取父级ID
	 * @return 父级ID
	 */
	public Integer getPid() {
		return this.pid;
	}
	/**
	 * 设置配件类型（1易损件 2车型件）
	 * @param partsType 配件类型（1易损件 2车型件）
	 */
	public void setPartsType(Integer partsType) {
		this.partsType = partsType;
	}
	/**
	 * 获取配件类型（1易损件 2车型件）
	 * @return 配件类型（1易损件 2车型件）
	 */
	public Integer getPartsType() {
		return this.partsType;
	}

}

