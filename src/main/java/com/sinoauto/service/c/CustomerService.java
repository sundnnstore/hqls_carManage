package com.sinoauto.service.c;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.sinoauto.dao.bean.c.CCustomer;
import com.sinoauto.dao.bean.c.CCustomerCar;
import com.sinoauto.dao.mapper.CCustomerCarMapper;
import com.sinoauto.dao.mapper.CCustomerMapper;
import com.sinoauto.dto.c.AddOrUpdateCarInfoDto;
import com.sinoauto.dto.c.CustomerLoginDto;
import com.sinoauto.dto.c.CustomerUpdateInfoDto;
import com.sinoauto.entity.AuthUser;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;
import com.sinoauto.service.AuthService;
import com.sinoauto.util.Constant;

@Service
public class CustomerService {

	@Autowired
	private AuthService authService;
	@Autowired
	private CCustomerMapper customerMapper;
	@Autowired
	private CCustomerCarMapper customerCarMapper;
	
	//用户名，密码   用于快捷登录接口获取token
	private final static String USERNAME = "cadmin";
	private final static String PASSWORD = "";
	
	/**
	 * C端:顾客登录
	 * @param mobile
	 * @param password
	 * @return
	 * @author Wuxiao
	 */
	public ResponseEntity<RestModel<CustomerLoginDto>> login(String mobile, String password) {
		try {
			RestModel<TokenModel> rest = authService.getToken(mobile, password, "ls", "web", "1.0", Constant.UUID_LOGIN); 
			//token 解析失败
			if (rest.getErrcode() != 0) {
				return RestModel.error(HttpStatus.OK, rest.getErrcode(), "用户名或密码不正确");
			}
			//根据全局用户 ID 获取当前客户
			CCustomer customer = customerMapper.getCustomerByUserId(rest.getResult().getUserId()); 
			//获取客户的默认车辆牌号
			CCustomerCar car = customerCarMapper.getDefaultCarByCustomerId(customer.getCustomerId());
			String carNo = car == null? "": car.getCarNo();
			CustomerLoginDto login = new CustomerLoginDto();
			login.setCarNo(carNo);
			login.setCustomerId(customer.getCustomerId());
			login.setCustomerName(customer.getCustomerName());
			login.setHeadUrl(customer.getHeadUrl());
			login.setToken(rest.getResult().getToken());
			
			return RestModel.success(login);
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 * 生成指定长度随机字母、数字组合的密码
	 * @param length
	 * @return
	 * @author Wuxiao
	 */
	static String generatorSixPass(int length) {
		StringBuffer password = new StringBuffer("");
		Random ran = new Random();
		for (int i = 0; i < length; i++) {
			String charOrNum = (ran.nextInt(2) % 2 == 0) ? "char" : "num";
			if ("char".equalsIgnoreCase(charOrNum)) {
				//随机生成一个小写字母
				password.append((char) (ran.nextInt(26) + 97));
			}
			else if ("num".equalsIgnoreCase(charOrNum)) {
				//随机生成一个数字
				password.append(ran.nextInt(10));
			}
		}
		return password.toString();
	}
	
	/**
	 * C端： 快捷登录
	 * @param mobile
	 * @param code 验证码
	 * @return
	 * @author Wuxiao
	 */
	@Transactional
	public ResponseEntity<RestModel<CustomerLoginDto>> fastLogin(String mobile, String code) {
		try {
			//验证 验证码是否正确
			RestModel<Boolean> valid = authService.verifyValidCode(mobile, code);
			if (!valid.getResult()) {
				return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION, "验证码不正确！");
			}
			//判断用户是否已注册
			CCustomer customer = customerMapper.getCustomerByMobile(mobile);
			//用户不存在， 新增用户
			if (customer == null) {
				String password = generatorSixPass(6);
				//组装顾客信息
				customer = new CCustomer();
				customer.setBalance(0.00);
				customer.setCustomerName(mobile);
				customer.setGender(3);
				customer.setIsActive(false);
				customer.setMobile(mobile);
				RestModel<Integer> rest = authService.register(mobile, password);
				//全局用户注册成功
				if (rest.getErrcode() == 0) { 
					//在顾客表中添加用户
					customer.setGlobalUserId(rest.getResult());
					customerMapper.insert(customer);
					//推送生成的默认密码给用户
					
					
					return login(mobile, password);
				}
				//顾客在全局表已存在，将顾客信息存储在顾客表
				else if (rest.getErrcode() == 4006 || rest.getErrmsg().contains("该用户已注册")) {
					//获取管理员 token
					RestModel<TokenModel> tokenModel = authService.getToken(USERNAME, PASSWORD, "ls", "web", "1.0", Constant.UUID_LOGIN);
					String token = tokenModel.getResult().getToken();
					//获取顾客信息
					RestModel<AuthUser> user = authService.getUserInfoByUserName(token, mobile);
					customer.setGlobalUserId(user.getResult().getUserId());
					customerMapper.insert(customer);
					
					return login(mobile, user.getResult().getPassword());
				}
				else {
					customer = null;
					return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION, "全局用户表插入数据异常");
				}
			}
			//顾客已存在
			else {
				//获取管理员 token
				RestModel<TokenModel> tokenModel = authService.getToken(USERNAME, PASSWORD, "ls", "web", "1.0", Constant.UUID_LOGIN);
				String token = tokenModel.getResult().getToken();
				//获取顾客信息
				RestModel<AuthUser> user = authService.getUserInfoByUserName(token, mobile);
				return login(mobile, user.getResult().getPassword());
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 * C端:顾客注册
	 * @param mobile 手机号
	 * @param password 密码
	 * @param code 验证码
	 * @param token 邀请人的信息
	 * @return
	 * @author Wuxiao
	 */
	@Transactional
	public ResponseEntity<RestModel<CustomerLoginDto>> register(String mobile, String password, String code, String token) {
		try {
			//判断是否有 token （是否是通过邀请注册）
			Integer inviteUserId = null;
			if (!StringUtils.isEmpty(token)) {
				RestModel<TokenModel> model = authService.validToken(token);
				if (model.getErrcode() != 0) { //解析 token 失败
					return RestModel.error(HttpStatus.OK, ErrorStatus.INVALID_TOKEN);
				}
				CCustomer inviteU = customerMapper.getCustomerByUserId(model.getResult().getUserId());
				inviteUserId = inviteU.getCustomerId();
			}
			//验证 验证码是否正确
			RestModel<Boolean> valid = authService.verifyValidCode(mobile, code);
			if (!valid.getResult()) {
				return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION, "验证码不正确！");
			}
			//在全局用户表中添加用户
			RestModel<Integer> rest = authService.register(mobile, password);
			if (rest.getErrcode() == 0) {
				Integer globalUserId = rest.getResult(); //全局用户ID
				//在顾客表中添加用户
				CCustomer customer = new CCustomer();
				customer.setBalance(0.00);
				customer.setCustomerName(mobile);
				customer.setGender(3);
				customer.setGlobalUserId(globalUserId);
				customer.setInviterUserId(inviteUserId);
				customer.setIsActive(false);
				customer.setMobile(mobile);
				customerMapper.insert(customer);
				return login(mobile, password);
			}
			else if (rest.getErrcode() == 4006 || rest.getErrmsg().contains("该用户已注册")) {
				return RestModel.error(HttpStatus.OK, ErrorStatus.INVALID_DATA, "用户已注册！");
			}
			else {
				return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION, rest.getErrmsg());
			}
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 * C端： 更新用户信息
	 * @param updateInfo 要修改的信息
	 * @return
	 * @author Wuxiao
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> updateCustomerInfo(CustomerUpdateInfoDto updateInfo, String token) {
		try {
			RestModel<TokenModel> rest = authService.validToken(token);
			//解析 token 失败
			if (rest.getErrcode() != 0) {
				return RestModel.error(HttpStatus.OK, ErrorStatus.INVALID_TOKEN, rest.getErrmsg());
			}
			Integer globalUserId = rest.getResult().getUserId();
			CCustomer customer = customerMapper.getCustomerByUserId(globalUserId);
			customer.setBirthday(updateInfo.getBirthday());
			customer.setCustomerName(updateInfo.getCustomerName());
			customer.setGender(updateInfo.getGender());
			customer.setHeadUrl(updateInfo.getHeadUrl());
			customerMapper.update(customer);
			
			return RestModel.success();
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION, "更新顾客信息异常！");
		}
	}
	
	/**
	 * C端： 添加车辆信息
	 * @param carInfo 车辆的信息
	 * @param token 
	 * @return
	 * @author Wuxiao
	 */
	public ResponseEntity<RestModel<String>> addCarInfo(AddOrUpdateCarInfoDto carInfo, String token) {
		RestModel<TokenModel> rest = authService.validToken(token);
		//解析 token 失败
		if (rest.getErrcode() != 0) {
			return RestModel.error(HttpStatus.OK, ErrorStatus.INVALID_TOKEN, rest.getErrmsg());
		}
		Integer globalUserId = rest.getResult().getUserId();
		CCustomer customer = customerMapper.getCustomerByUserId(globalUserId);
		//判断 customerCarId 是否存在，若存在，则为修改车辆信息，先删除已存在的车辆，再增加
		if (carInfo.getCustomerCarId() != null) {
			customerCarMapper.deleteCarByPrimaryKey(carInfo.getCustomerCarId());
		}
		//添加客户车辆信息
		CCustomerCar car = new CCustomerCar();
		car.setCarModelId(carInfo.getModelId());
		car.setCarNo(carInfo.getCarNo());
		car.setCustomerId(customer.getCustomerId());
		car.setInsuranceCompanyId(carInfo.getInsuranceCompanyId());
		car.setInsuranceExpirationTime(carInfo.getInsuranceExpirationTime());
		car.setMileage(carInfo.getMileage());
		car.setVinNo(carInfo.getVinNo());
		Boolean isDefault = carInfo.getIsDefault();
		//根据客户ID查询客户车辆
		if (customerCarMapper.getCarCountByCustomerId(customer.getCustomerId()) == 0) {
			//将第一辆车设为默认车辆
			car.setIsDefault(true);
		} else {
			if (isDefault) { //如果客户已有车辆，且将当前辆车设为默认车辆，则更改其他车辆为非默认
				customerCarMapper.updateCarIsDefault(false, customer.getCustomerId());
			}
			car.setIsDefault(isDefault);
		}
		customerCarMapper.insert(car);
		
		return RestModel.success();
	}
	
}
