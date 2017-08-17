package com.sinoauto.dto;

import java.util.List;

public class StoreTreeDto {

	private Integer storeId;
	private Integer pid;
	private String storeName;
	private List<StoreTreeDto> storeTreeList;

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

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public List<StoreTreeDto> getStoreTreeList() {
		return storeTreeList;
	}

	public void setStoreTreeList(List<StoreTreeDto> storeTreeList) {
		this.storeTreeList = storeTreeList;
	}

}
