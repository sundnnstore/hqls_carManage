package com.sinoauto.dto;

import java.util.List;

import com.sinoauto.dao.bean.HqlsStore;

import io.swagger.annotations.ApiModelProperty;

public class StoreDto {
	
	@ApiModelProperty(value="管理员账号")
	private String mobile;
	
	private List<HqlsStore> hqlsStore;

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public List<HqlsStore> getHqlsStore() {
		return hqlsStore;
	}

	public void setHqlsStore(List<HqlsStore> hqlsStore) {
		this.hqlsStore = hqlsStore;
	}

	
	

}
