package com.sinoauto.util.push;

public class PushParms {
	// 推送的内容
	private PushMessage message;
	// 推送的账号
	private String account;
	// 推送展示的标题
	private PushAlert alert;
	//下标
	private Integer badge;
	//声音
	private String sound ="default";

	public PushMessage getMessage() {
		return message;
	}

	public void setMessage(PushMessage message) {
		if (message != null) {
			this.message = message;
		}
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		if (account != null) {
			this.account = account;
		}
	}

	public PushAlert getAlert() {
		return alert;
	}

	public void setAlert(PushAlert alert) {
		if (alert != null) {
			this.alert = alert;
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

	public String getSound() {
		return sound;
	}

	public void setSound(String sound) {
		if (sound != null) {
			this.sound = sound;
		}
	}

}
