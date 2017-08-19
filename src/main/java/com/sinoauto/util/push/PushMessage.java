package com.sinoauto.util.push;

import java.util.List;

public class PushMessage {

	private List<PushAction> action;

	private PushInfo info;

	public List<PushAction> getAction() {
		return action;
	}

	public void setAction(List<PushAction> action) {
		if (action != null) {
			this.action = action;
		}
	}

	public PushInfo getInfo() {
		return info;
	}

	public void setInfo(PushInfo info) {
		if (info != null) {
			this.info = info;
		}
	}

}
