package com.sinoauto.util.push;

import java.util.ArrayList;
import java.util.List;

import com.gexin.rp.sdk.base.IPushResult;
import com.gexin.rp.sdk.base.impl.ListMessage;
import com.gexin.rp.sdk.base.impl.Target;
import com.gexin.rp.sdk.base.payload.APNPayload;
import com.gexin.rp.sdk.http.IGtPush;
import com.gexin.rp.sdk.template.APNTemplate;
import com.gexin.rp.sdk.template.NotificationTemplate;
import com.gexin.rp.sdk.template.style.Style0;

public class GeTuiUtil {
	// 采用"Java SDK 快速入门"， "第二步 获取访问凭证 "中获得的应用配置，用户可以自行替换
	private static String appId = "3EmRltoE7S8rTSsjk7Bgp1";
	private static String appKey = "uNjzX0vLry7X9i8GrKYXg4";
	private static String masterSecret = "ZlM4lsaHb97z17d283kYU2";

	static String CID = "746bda891cb49e45a51b34895c36d94f";
	// 别名推送方式
	// static String Alias = "";
	static String host = "http://sdk.open.api.igexin.com/apiex.htm";

	/*
	 * public static void main(String[] args) throws Exception {
	 * IGtPush push = new IGtPush(host, appKey, masterSecret);
	 * LinkTemplate template = linkTemplateDemo();
	 * SingleMessage message = new SingleMessage();
	 * message.setOffline(true);
	 * // 离线有效时间，单位为毫秒，可选
	 * message.setOfflineExpireTime(24 * 3600 * 1000);
	 * message.setData(template);
	 * // 可选，1为wifi，0为不限制网络环境。根据手机处于的网络情况，决定是否下发
	 * message.setPushNetWorkType(0);
	 * Target target = new Target();
	 * target.setAppId(appId);
	 * target.setClientId(CID);
	 * //target.setAlias(Alias);
	 * IPushResult ret = null;
	 * try {
	 * ret = push.pushMessageToSingle(message, target);
	 * } catch (RequestException e) {
	 * e.printStackTrace();
	 * ret = push.pushMessageToSingle(message, target, e.getRequestId());
	 * }
	 * if (ret != null) {
	 * System.out.println(ret.getResponse().toString());
	 * } else {
	 * System.out.println("服务器响应异常");
	 * }
	 * }
	 */

	public String pushToIos() {
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		APNTemplate t = new APNTemplate();
		APNPayload apnpayload = new APNPayload();
		apnpayload.setSound("");
		APNPayload.DictionaryAlertMsg alertMsg = new APNPayload.DictionaryAlertMsg();
		// 通知文本消息标题
		alertMsg.setTitle("aaaaaa");
		// 通知文本消息字符串
		alertMsg.setBody("bbbb");
		// 对于标题指定执行按钮所使用的Localizable.strings,仅支持IOS8.2以上版本
		alertMsg.setTitleLocKey("ccccc");
		// 指定执行按钮所使用的Localizable.strings
		alertMsg.setActionLocKey("ddddd");
		apnpayload.setAlertMsg(alertMsg);

		t.setAPNInfo(apnpayload);
		ListMessage message = new ListMessage();
		message.setData(t);
		String contentId = push.getAPNContentId(appId, message);
		System.out.println(contentId);
		List<String> dtl = new ArrayList<String>();
		/* dtl.add(token); */
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IPushResult ret = push.pushAPNMessageToList(appId, contentId, dtl);
		System.out.println(ret.getResponse());
		return ret.getResponse().toString();
	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public static String pushToAndroid(List<String> clientIds, String title, String text, String payload) {
		// 配置返回每个用户返回用户状态，可选
		System.setProperty("gexin.rp.sdk.pushlist.needDetails", "true");
		IGtPush push = new IGtPush(host, appKey, masterSecret);
		// 通知透传模板
		NotificationTemplate template = temp(title, text, payload);
		ListMessage message = new ListMessage();
		message.setData(template);
		// 设置消息离线，并设置离线时间
		message.setOffline(true);
		// 离线有效时间，单位为毫秒，可选
		message.setOfflineExpireTime(24 * 1000 * 3600);
		// 配置推送目标
		List targets = new ArrayList();
		for (String clientId : clientIds) {
			Target target = new Target();
			target.setAppId(appId);
			target.setClientId(clientId);
			targets.add(target);
		}
		try {
			// taskId用于在推送时去查找对应的message
			String taskId = push.getContentId(message);
			IPushResult ret = push.pushMessageToList(taskId, targets);
			System.out.println(ret.getResponse().toString());
			return ret.getResponse().toString();
		} catch (Exception e) {
			e.printStackTrace();
			return "";
		}
	}

	public static NotificationTemplate temp(String title, String text, String payload) {
		NotificationTemplate template = new NotificationTemplate();
		// 设置APPID与APPKEY
		template.setAppId(appId);
		template.setAppkey(appKey);
		Style0 style = new Style0();
		// 设置通知栏标题与内容
		style.setTitle(title);
		style.setText(text);
		// 配置通知栏图标
		style.setLogo("icon.png");
		// 配置通知栏网络图标
		style.setLogoUrl("");
		// 设置通知是否响铃，震动，或者可清除
		style.setRing(true);
		style.setVibrate(true);
		style.setClearable(true);
		template.setStyle(style);
		// 透传消息设置，1为强制启动应用，客户端接收到消息后就会立即启动应用；2为等待应用启动
		template.setTransmissionType(1);
		template.setTransmissionContent("{'a':'安卓透传标题','b':'安卓透传内容','payload':'" + payload + "'}");
		return template;
	}

}
