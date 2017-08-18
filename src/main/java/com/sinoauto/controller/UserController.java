package com.sinoauto.controller;

import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.HqlsAuthority;
import com.sinoauto.dto.UserDto;
import com.sinoauto.dto.UserLoginDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.UserService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags = "用户操作")
@RestController
public class UserController {

	@Autowired
	private UserService userService;

	@ApiOperation(value = "登录获取token", notes = "tangwt")
	@PostMapping("login")
	public ResponseEntity<RestModel<UserLoginDto>> login(@RequestParam(value = "userName", required = true) String userName,
			@RequestParam(value = "passWord", required = true) String passWord) {
		return userService.login(userName, passWord);
	}

	@ApiOperation(value = "获取权限集合", notes = "tangwt")
	@PostMapping("authorities")
	public ResponseEntity<RestModel<Map<String, Set<HqlsAuthority>>>> findUserAuthority(@RequestHeader String Authorization,
			@RequestParam(value = "isBack", required = true) Integer isBack) {
		return userService.findUserAuthority(Authorization, isBack);
	}

	@ApiOperation(value = "按条件查找人员信息集合", notes = "tangwt")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "roleId", value = "角色ID", required = false, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "userName", value = "姓名", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "mobile", value = "手机号", required = false, dataType = "String"),
			@ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", required = true, dataType = "int") })
	@GetMapping(value = "findusers")
	public ResponseEntity<RestModel<List<UserDto>>> findUsersByConditions(Integer roleId, String userName, String mobile, Integer pageIndex,
			Integer pageSize) {
		return userService.findUsersByConditions(roleId, userName, mobile, pageIndex, pageSize);
	}

	@ApiOperation(value = "新增人员信息", notes = "tangwt")
	@PostMapping("adduser")
	public ResponseEntity<RestModel<String>> addUser(@RequestHeader String Authorization, @RequestBody UserDto userDto) {
		if (StringUtils.isEmpty(userDto.getMobile())) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "手机号为空！");
		}
		return userService.addUser(userDto, Authorization);
	}

	@ApiOperation(value = "禁用/启用人员状态", notes = "tangwt")
	@PostMapping("updateuserstatus")
	public ResponseEntity<RestModel<String>> updateUserStatus(@RequestParam("userId") Integer userId) {
		if (userId == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "用户ID为空！");
		}
		return userService.updateUserStatus(userId);
	}

}
