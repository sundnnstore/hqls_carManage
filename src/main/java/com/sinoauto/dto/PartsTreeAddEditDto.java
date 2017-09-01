package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 *	配件树形菜单,点击查询dto
 *	@author liud
 *	@Date2017年8月19日上午11:04:53
 *
 */
public class PartsTreeAddEditDto {
	@ApiModelProperty("配件类型ID")
	private Integer id;
	@ApiModelProperty("配件类型名称")
	private String name;
	@ApiModelProperty("配件类型父类")
	private Integer pId;
	@ApiModelProperty("是否展开")
	private boolean open; 
	
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
	public Integer getpId() {
		return pId;
	}
	public void setpId(Integer pId) {
		this.pId = pId;
	}
	public boolean isOpen() {
		return open;
	}
	public void setOpen(boolean open) {
		this.open = open;
	}
	
	
}
