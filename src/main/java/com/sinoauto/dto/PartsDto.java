package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 
 *@author liud
 *@Date2017年8月17日上午10:57:39
 *
 *查询条件 和返回的集合共用属性
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
	@ApiModelProperty(name="配件大分类 1.易损件 2.通用件 ID")
	private String partsTopTypeId;
	@ApiModelProperty(name="配件大分类名称")
	private String partsTopType;
	/**
	 * 返回对象属性
	 */
	@ApiModelProperty(name="配件父类ID")
	private Integer partsParentId;
	@ApiModelProperty(name="配件ID")
	private Integer partsId; 
	
	/**
	 * 保留字段
	 */
	@ApiModelProperty(name="配件父类拼接字符串 例如 一级+二级+三级")
	private String partsParentStr;
}
