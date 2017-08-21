package com.sinoauto.service;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sinoauto.entity.AuthUser;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RespEntity;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;
import com.sinoauto.util.HttpUtil;

/**
 * @author fujl
 * @version 1.0
 * @date 2017-03-05 00:24:37
 */
@Service
public class AuthService {

	Logger logger = LoggerFactory.getLogger(AuthService.class);
	// @Value("${auth.service.url}")
	private String AUTH_SERVICE_URL = "http://42.159.202.20:11111/";

	/**
	 * 获取token
	 * @param username
	 * @param password
	 * @param projectName 项目名称 （bc-蓝领驿家, uc-二手车）
	 * @param clientType 客户端类型 （ios, an, web）
	 * @param osVesion
	 * @param deviceId
	 * @return
	 */
	public RestModel<TokenModel> getToken(String username, String password, String projectName, String clientType, String osVesion, String deviceId) {
		Map<String, Object> params = new HashMap<>();
		params.put("username", username);
		params.put("password", password);
		Map<String, Object> headers = new HashMap<>();
		headers.put("projectName", projectName);
		headers.put("clientType", clientType);
		headers.put("osVersion", osVesion);
		headers.put("deviceId", deviceId);
		RespEntity respEntity = HttpUtil.request("POST", String.format("%s%s", AUTH_SERVICE_URL, "token"), headers, params, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new Gson();
		RestModel<TokenModel> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<TokenModel>>() {
			private static final long serialVersionUID = 8006629767934399058L;
		}.getType());
		if (0 != model.getErrcode()) {
			return new RestModel<>(model.getErrcode(), model.getErrmsg());
		}
		return model;
	}

	/**
	 * token验证
	 * @param token
	 * @return
	 */
	public RestModel<TokenModel> validToken(String token) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		RespEntity respEntity = HttpUtil.request("GET", String.format("%s%s", AUTH_SERVICE_URL, "token/valid"), headers, null, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new Gson();
		RestModel<TokenModel> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<TokenModel>>() {
			private static final long serialVersionUID = -4720993135558299973L;
		}.getType());
		return model;
	}

	/**
	 * 根据token获取用户信息
	 * @param token
	 * @return
	 */
	public RestModel<AuthUser> getUserInfoByToken(String token) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);

		RespEntity respEntity = HttpUtil.request("GET", String.format("%s%s", AUTH_SERVICE_URL, "user"), headers, null, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		RestModel<AuthUser> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<AuthUser>>() {
			private static final long serialVersionUID = -4730088002721684575L;
		}.getType());
		return model;
	}

	/**
	 * 更新当前用户
	 * @return
	 */
	public RestModel<String> updateCurrentUser(String token, AuthUser user) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		RespEntity respEntity = HttpUtil.request("PUT", String.format("%s%s", AUTH_SERVICE_URL, "user"), headers, null, gson.toJson(user));
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		RestModel<String> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<String>>() {
			private static final long serialVersionUID = -2333446726270999600L;
		}.getType());
		return model;
	}

	/**
	 * 修改当前用户手机号
	 * @return
	 */
	public RestModel<String> updateUserMobile(String token, String projectName, String newMobile, String validCode) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		Map<String, Object> params = new HashMap<>();
		params.put("newMobile", newMobile);
		params.put("validCode", validCode);
		params.put("projectName", projectName);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		RespEntity respEntity = HttpUtil.request("PUT", String.format("%s%s", AUTH_SERVICE_URL, "user/update/mobile"), headers, params, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		RestModel<String> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<String>>() {
			private static final long serialVersionUID = -2333446726270999600L;
		}.getType());
		return model;
	}
	
	/**
	 * 用户注册
	 * @param userName
	 * @param password
	 * @return
	 */
	public RestModel<Integer> register(String userName, String password) {
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		params.put("password", password);
		RespEntity respEntity = HttpUtil.request("POST", String.format("%s%s", AUTH_SERVICE_URL, "user"), null, params, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new Gson();
		RestModel<Integer> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<Integer>>() {
			private static final long serialVersionUID = -4720993135558299973L;
		}.getType());
		return model;
	}

	/**
	 * 管理员获取用户信息
	 * @param token
	 * @return
	 */
	public RestModel<AuthUser> getUserInfoByAdmin(String token, Integer userId) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		RespEntity respEntity = HttpUtil.request("GET", String.format("%s%s%s", AUTH_SERVICE_URL, "admin/", userId), headers, null, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		RestModel<AuthUser> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<AuthUser>>() {
			private static final long serialVersionUID = -8596419997122191936L;
		}.getType());
		return model;
	}

	/**
	 * 管理员创建用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public RestModel<Integer> createUserByAdmin(String token, AuthUser user) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		RespEntity respEntity = HttpUtil.request("POST", String.format("%s%s", AUTH_SERVICE_URL, "admin"), headers, null, gson.toJson(user));
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		RestModel<Integer> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<Integer>>() {
			private static final long serialVersionUID = -4720993135558299973L;
		}.getType());
		return model;
	}

	/**
	 * 管理员更新用户
	 * @param userName
	 * @param password
	 * @return
	 */
	public RestModel<Integer> updateUserByAdmin(String token, Integer userId, AuthUser user) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		RespEntity respEntity = HttpUtil.request("PUT", String.format("%s%s%s", AUTH_SERVICE_URL, "admin/", userId), headers, null,
				gson.toJson(user));
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		RestModel<Integer> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<Integer>>() {
			private static final long serialVersionUID = -4720993135558299973L;
		}.getType());
		return model;
	}

	/**
	 * 验证验证码
	 * @param token
	 * @param validCode
	 * @return
	 */
	public RestModel<Boolean> verifyValidCode(String mobile, String validCode) {
		Map<String, Object> params = new HashMap<>();
		params.put("mobile", mobile);
		params.put("validCode", validCode);
		RespEntity respEntity = HttpUtil.request("POST", String.format("%s%s", AUTH_SERVICE_URL, "message/valid"), null, params, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new Gson();
		RestModel<Boolean> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<Boolean>>() {
			private static final long serialVersionUID = -8664238833089948786L;
		}.getType());
		return model;
	}

	/**
	 * 删除token
	 * @param token
	 * @param validCode 验证码
	 * @return
	 */
	public RestModel<Boolean> deleteToken(String token) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		RespEntity respEntity = HttpUtil.request("DELETE", String.format("%s%s", AUTH_SERVICE_URL, "token"), headers, null, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new Gson();
		RestModel<Boolean> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<Boolean>>() {
			private static final long serialVersionUID = -5647708456701593090L;
		}.getType());
		return model;
	}

	/**
	 * GET /admin 根据用户名获取用户信息
	 * @param token
	 * @param userName
	 * @return
	 */
	public RestModel<AuthUser> getUserInfoByUserName(String token, String userName) {
		Map<String, Object> headers = new HashMap<>();
		headers.put("Authorization", token);
		Map<String, Object> params = new HashMap<>();
		params.put("userName", userName);
		RespEntity respEntity = HttpUtil.request("GET", String.format("%s%s", AUTH_SERVICE_URL, "admin"), headers, params, null);
		if (!respEntity.isSuccess()) {
			return new RestModel<>(ErrorStatus.SYSTEM_EXCEPTION);
		}
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd HH:mm:ss").create();
		RestModel<AuthUser> model = gson.fromJson(respEntity.getResult(), new TypeToken<RestModel<AuthUser>>() {
			private static final long serialVersionUID = -8596419997122191936L;
		}.getType());
		return model;

	}
}
