package com.sinoauto.util.push;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.gson.Gson;
import com.sinoauto.util.Constant;

import cn.jiguang.common.ClientConfig;
import cn.jiguang.common.resp.APIConnectionException;
import cn.jiguang.common.resp.APIRequestException;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.SMS;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

public class PushUtil {
	public static Logger LOG = LoggerFactory.getLogger(PushUtil.class);
	
	
	 public static PushResult push2Andriod(PushParms parms) {
	        JPushClient jpushClient = new JPushClient(Constant.MASTER_SECRET, Constant.APP_KEY);
	        try {
	            SMS sms = SMS.content(new Gson().toJson(parms.getMessage()), 10);
	            PushResult result = jpushClient.sendAndroidMessageWithAlias(parms.getAlert().getTitle(), new Gson().toJson(parms.getMessage()), sms, parms.getAccount());
	            LOG.info("Got result - " + result);
	            return result;
	        } catch (APIConnectionException e) {
	            LOG.error("Connection error. Should retry later. ", e);
	            return null;
	        } catch (APIRequestException e) {
	            LOG.error("Error response from JPush server. Should review and fix it. ", e);
	            LOG.info("HTTP Status: " + e.getStatus());
	            LOG.info("Error Code: " + e.getErrorCode());
	            LOG.info("Error Message: " + e.getErrorMessage());
	            return null;
	        }
	    }

	public static PushResult push2IOSByAPNS(PushParms parms) {
		JPushClient jpushClient = new JPushClient(Constant.MASTER_SECRET, Constant.APP_KEY, null, ClientConfig.getInstance());
		PushPayload payload = getPushPayload(parms);
		try {
			PushResult result = jpushClient.sendPush(payload);
			LOG.info("Got result - " , result);
			return result;
		} catch (APIConnectionException e) {
			LOG.error("Connection error, should retry later" , e);
			return null;
		} catch (APIRequestException e) {
			LOG.error("Should review the error, and fix the request" , e);
			LOG.error("HTTP Status: " , e.getStatus());
			LOG.error("Error Code: " , e.getErrorCode());
			LOG.error("Error Message: " , e.getErrorMessage());
			return null;
		}
	}

	/**
	 * APNS推送给IOS端
	 * @param parms
	 * @return
	 */
	public static PushPayload getPushPayload(PushParms parms) {
		return PushPayload.newBuilder()
						.setPlatform(Platform.ios())
						.setAudience(Audience.alias(parms.getAccount()))
						.setNotification(Notification.newBuilder()
						.addPlatformNotification(IosNotification.newBuilder()
						.setAlert(parms.getAlert().getTitle())
						.setBadge(parms.getBadge())
						.setSound(parms.getSound())
						.addExtra("from", "HQLS")
						.build())
						.build())
						.setMessage(Message.content(new Gson().toJson(parms.getMessage())))
						.setOptions(Options.newBuilder()
											.setApnsProduction(Constant.APNS_PRODUCTION)
											.build()).build();
	}
	/**
	 * 组装推送内容
	 * @return
	 */
	public static PushParms comboPushParms(String account,List<PushAction> action,PushInfo info,String title,String subtitle,String body,Integer badge){
		PushParms parms = new PushParms();
		parms.setAccount(account);
		PushAlert alert = new PushAlert();
		alert.setBody(body);
		alert.setTitle(title);
		alert.setSubtitle(subtitle);
		parms.setAlert(alert);
		parms.setBadge(badge);
		PushMessage message = new PushMessage();
		message.setAction(action);
		message.setInfo(info);
		parms.setMessage(message);
		return parms;
	}

}
