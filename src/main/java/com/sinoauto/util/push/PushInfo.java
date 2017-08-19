package com.sinoauto.util.push;

public class PushInfo {
	//模块名称
	private String module;
	//下标数量
	private Integer badge;
	//备注
	private String remark;
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		if (module != null) {
			this.module = module;
		}
	}
	public Integer getBadge() {
		return badge;
	}
	public void setBadge(Integer badge) {
		if (badge != null) {
			this.badge = badge;
		}
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		if (remark != null) {
			this.remark = remark;
		}
	}
	
	

}
