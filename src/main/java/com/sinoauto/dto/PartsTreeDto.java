package com.sinoauto.dto;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;

/**
 *	配件树形菜单,点击查询dto
 *	@author liud
 *	@Date2017年8月19日上午11:04:53
 *
 */
public class PartsTreeDto {
	@ApiModelProperty("配件父类ID")
	private Integer id;
	@ApiModelProperty("配件父类名称")
	private String name;
	@ApiModelProperty("下一级的集合")
	private List<CommonDto> children;
	
	public List<CommonDto> getChildren() {
		return children;
	}
	public void setChildren(List<CommonDto> children) {
		this.children = children;
	}
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
