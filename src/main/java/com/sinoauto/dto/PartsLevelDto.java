package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询等级
 *@author liud
 *@Date2017年8月23日下午4:04:58
 *
 */
public class PartsLevelDto {
	
	@ApiModelProperty("存储当前等级的名称")
	private Integer id;
	
	@ApiModelProperty("存储当前等级的名称")
	private String name;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}	
}
