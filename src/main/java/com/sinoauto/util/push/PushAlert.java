package com.sinoauto.util.push;

public class PushAlert {

	// 标题
	private String title;
	// 子标题
	private String subtitle;
	// 内容
	private String body;

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		if (title != null) {
			this.title = title;
		}
	}

	public String getSubtitle() {
		return subtitle;
	}

	public void setSubtitle(String subtitle) {
		if (subtitle != null) {
			this.subtitle = subtitle;
		}
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		if (body != null) {
			this.body = body;
		}
	}

}
