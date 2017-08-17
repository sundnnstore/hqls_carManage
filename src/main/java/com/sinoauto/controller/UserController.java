package com.sinoauto.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.entity.RestModel;
import com.sinoauto.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户操作")
@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@ApiOperation(value = "门店登录获取token", notes = "tangwt")
	@PostMapping("store/login")
	public ResponseEntity<RestModel<String>> storeLogin(@RequestParam(value="userName",required=true)String userName,@RequestParam(value="passWord",required=true)String passWord){
		return userService.storeLogin(userName, passWord);
	}

}
