package com.sinoauto.dto;

import java.util.List;

/**
 * 按条件（车型名称和配件名称）查询配件类型和对应的配件列表
 * 
 * @author Wuxiao
 *
 */
public class PartsTypeAndPartsListDto {

	private Integer partsTypeId;
	private String typeName;
	private String icon;
	private List<PartsListDto> partsList;

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

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public List<PartsListDto> getPartsList() {
		return partsList;
	}

	public void setPartsList(List<PartsListDto> partsList) {
		this.partsList = partsList;
	}

}
