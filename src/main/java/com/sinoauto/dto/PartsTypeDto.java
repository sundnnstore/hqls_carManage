package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 配件类型
 *@author liud
 *@Date2017年8月23日下午12:12:21
 *
 */
public class PartsTypeDto {
	@ApiModelProperty("配件ID")
	private Integer id;
	@ApiModelProperty("配件名称")
	private String name;
	@ApiModelProperty("父级ID")
	private Integer pId;
	
	private String iconOpen = "../../images/checkbox_0.gif";
	
	private String iconClose= "../../images/checkbox_1.gif";
	
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
	public String getIconOpen() {
		return iconOpen;
	}
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}
	public String getIconClose() {
		return iconClose;
	}
	public void setIconClose(String iconClose) {
		this.iconClose = iconClose;
	}
	
}
