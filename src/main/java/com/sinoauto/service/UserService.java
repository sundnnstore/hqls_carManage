package com.sinoauto.service;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;

@Service
public class UserService {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserMapper userMapper;

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseEntity<RestModel<String>> storeLogin(String userName, String passWord) {
		RestModel<TokenModel> rest = authService.getToken(userName, passWord, "st", "web", "1.0", UUID.randomUUID().toString());
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		HqlsUser user = userMapper.getUserByGloabUserId(userId);
		if (user == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST);
		}
		return RestModel.success(rest.getResult().getToken());
	}

}