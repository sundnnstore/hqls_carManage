package com.sinoauto.controller.c;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.c.AddOrUpdateCarInfoDto;
import com.sinoauto.dto.c.CustomerLoginDto;
import com.sinoauto.dto.c.CustomerUpdateInfoDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.c.CustomerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "顾客操作")
public class CustomerController {
	
	@Autowired
	private CustomerService customerService;

	@ApiOperation(value = "顾客登录获取个人信息和token", notes = "wuxiao")
	@GetMapping("c/login")
	public ResponseEntity<RestModel<CustomerLoginDto>> login(
			@RequestParam(value="mobile", required=true) String mobile,
			@RequestParam(value="password", required=true) String password) {
		
		return customerService.login(mobile, password);
	}
	
	@ApiOperation(value = "快捷登录获取个人信息和token", notes = "wuxiao")
	@GetMapping("c/fastlogin")
	public ResponseEntity<RestModel<CustomerLoginDto>> fastLogin(
			@RequestParam(value="mobile", required=true) String mobile,
			@RequestParam(value="code", required=true) String code) {
		
		return customerService.fastLogin(mobile, code);
	}
	
	@ApiOperation(value = "注册", notes = "wuxiao")
	@GetMapping("c/register")
	public ResponseEntity<RestModel<CustomerLoginDto>> register(
			@RequestParam(value="mobile", required=true) String mobile,
			@RequestParam(value="password", required=true) String password,
			@RequestParam(value="code", required=true) String code,
			@RequestHeader(value="Authorization", required=false) String Authorization) {
		
		return customerService.register(mobile, password, code, Authorization);
	}
	
	@ApiOperation(value = "更新顾客信息", notes = "wuxiao")
	@PostMapping("c/updatecustomerinfo")
	public ResponseEntity<RestModel<String>> updateCustomerInfo(
			@RequestBody CustomerUpdateInfoDto updateInfo,
			@RequestHeader(value="Authorization") String Authorization) {
		
		return customerService.updateCustomerInfo(updateInfo, Authorization);
	}
	
	@ApiOperation(value = "选车型/添加车辆信息", notes = "wuxiao")
	@PostMapping("c/addcarinfo")
	public ResponseEntity<RestModel<String>> addCarInfo(@RequestBody AddOrUpdateCarInfoDto carInfo,
			@RequestHeader String Authorization) {
		
		return null;
	}
	
}
