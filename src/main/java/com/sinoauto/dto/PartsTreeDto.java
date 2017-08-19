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
	private Integer pid;
	@ApiModelProperty("配件父类名称")
	private String pname;
	@ApiModelProperty("下一级的集合")
	private List<CommonDto> children;
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
	public List<CommonDto> getChildren() {
		return children;
	}
	public void setChildren(List<CommonDto> children) {
		this.children = children;
	}
	
}
