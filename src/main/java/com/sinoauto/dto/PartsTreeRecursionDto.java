package com.sinoauto.dto;

import java.util.List;
import io.swagger.annotations.ApiModelProperty;

/**
 *	配件树形菜单递归查询dto
 *	@author liud
 *	@Date2017年8月19日上午11:04:53
 *
 */
public class PartsTreeRecursionDto {
	@ApiModelProperty("配件ID")
	private Integer partsTypeId;
	@ApiModelProperty("配件名称")
	private String typeName;
	@ApiModelProperty("配件父类")
	private Integer pid;
	private List<PartsTreeRecursionDto> partsTrees;
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
	public List<PartsTreeRecursionDto> getPartsTrees() {
		return partsTrees;
	}
	public void setPartsTrees(List<PartsTreeRecursionDto> partsTrees) {
		this.partsTrees = partsTrees;
	}
}
