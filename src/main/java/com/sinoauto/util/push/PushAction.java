package com.sinoauto.util.push;

/**
 * 推送时的动作
 * @author tangwt
 * @version 1.0
 * @date 2017-08-19 13:29:50
 */
public class PushAction {
	// 模块名称
	private String module;
	// 下标数量
	private Integer badge;
	// 是否需要打开
	private Boolean open;
	// 打开的地址链接
	private String url;

	PushAction() {

	}

	PushAction(String module, Integer badge, Boolean open, String url) {
		this.module = module;
		this.badge = badge;
		this.open = open;
		this.url = url;
	}

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

	public Boolean getOpen() {
		return open;
	}

	public void setOpen(Boolean open) {
		if (open != null) {
			this.open = open;
		}
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		if (url != null) {
			this.url = url;
		}
	}

}
