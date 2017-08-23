package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 查询等级
 *@author liud
 *@Date2017年8月23日下午4:04:58
 *
 */
public class PartsLevelDto {
	
	@ApiModelProperty("第一等级名称")
	private String oneLevelName;
	
	@ApiModelProperty("第二等级名称")
	private String twoLevelName;
	
	@ApiModelProperty("第三等级名称")
	private String threeLevelName;

	public String getOneLevelName() {
		return oneLevelName;
	}

	public void setOneLevelName(String oneLevelName) {
		this.oneLevelName = oneLevelName;
	}

	public String getTwoLevelName() {
		return twoLevelName;
	}

	public void setTwoLevelName(String twoLevelName) {
		this.twoLevelName = twoLevelName;
	}

	public String getThreeLevelName() {
		return threeLevelName;
	}

	public void setThreeLevelName(String threeLevelName) {
		this.threeLevelName = threeLevelName;
	}
}
