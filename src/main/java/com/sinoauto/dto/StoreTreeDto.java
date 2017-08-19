package com.sinoauto.dto;

import java.util.List;

public class StoreTreeDto {

	private Integer storeId;
	private Integer pid;
	private String name;
	private List<StoreTreeDto> children;

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}

	public Integer getPid() {
		return pid;
	}

	public void setPid(Integer pid) {
		this.pid = pid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<StoreTreeDto> getChildren() {
		return children;
	}

	public void setChildren(List<StoreTreeDto> children) {
		this.children = children;
	}

}
