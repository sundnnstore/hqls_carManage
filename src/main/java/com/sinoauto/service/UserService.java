package com.sinoauto.service;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsAuthority;
import com.sinoauto.dao.bean.HqlsRole;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.bean.HqlsUserRole;
import com.sinoauto.dao.mapper.AuthorityMapper;
import com.sinoauto.dao.mapper.ClientInfoMapper;
import com.sinoauto.dao.mapper.RoleMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dao.mapper.UserRoleMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.UserDto;
import com.sinoauto.dto.UserLoginDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;
import com.sinoauto.util.Constant;

@Service
public class UserService {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AuthorityMapper authorityMapper;
	@Autowired
	private UserRoleMapper userRoleMapper;
	@Autowired
	private RoleMapper roleMapper;
	@Autowired
	private ClientInfoMapper clientInfoMapper;

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseEntity<RestModel<UserLoginDto>> login(String userName, String passWord) {
		RestModel<TokenModel> rest = authService.getToken(userName, passWord, "ls", "web", "1.0", Constant.UUID_LOGIN);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		HqlsUser user = userMapper.getUserByGloabUserId(userId);
		if (user == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST);
		}
		UserLoginDto ul = new UserLoginDto();
		ul.setMobile(user.getMobile());
		ul.setUserName(user.getUserName());
		ul.setToken(rest.getResult().getToken());
		return RestModel.success(ul);
	}

	/**
	 * 门店端登录
	 * @param username
	 * @param password
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<UserLoginDto>> storeLogin(String userName, String passWord,String clientId) {
		RestModel<TokenModel> rest = authService.getToken(userName, passWord, "ls", "web", "1.0", Constant.UUID_LOGIN);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		Integer storeId = userMapper.checkUser(userId);
		if (storeId == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST);
		}
		UserLoginDto ul = new UserLoginDto();
		ul.setMobile("");
		ul.setUserName("");
		ul.setToken(rest.getResult().getToken());
		ul.setStoreId(storeId);
		if(!StringUtils.isEmpty(clientId)){
			HqlsUser user = userMapper.getUserByGloabUserId(userId);
			clientInfoMapper.deleteClientInfoByCId(clientId);
			clientInfoMapper.insertClientInfo(clientId, user.getUserId());
		}
		return RestModel.success(ul);
	}

	public ResponseEntity<RestModel<Map<String, Set<HqlsAuthority>>>> findUserAuthority(String token, Integer isBack) {
		// 获取当前用户
		RestModel<TokenModel> rest = authService.validToken(token);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_TOKEN);
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		HqlsUser user = userMapper.getUserByGloabUserId(userId);
		if (user != null) {
			List<HqlsAuthority> firstAuthority = authorityMapper.findFirstAuthorities(user.getUserId(), isBack);
			List<HqlsAuthority> secondAuthority = authorityMapper.findSecondAuthorities(user.getUserId(), isBack);
			// 组合map
			Map<HqlsAuthority, Set<HqlsAuthority>> menuMap = new TreeMap<>();
			Map<String, Set<HqlsAuthority>> returnMap = new LinkedHashMap<>();
			for (HqlsAuthority menu : firstAuthority) {
				if (!menuMap.containsKey(menu)) {
					Set<HqlsAuthority> menuSet = new TreeSet<>();
					for (HqlsAuthority menu2 : secondAuthority) {
						if (menu2.getPid() == menu.getAuthorityId()) {
							menuSet.add(menu2);
						}
					}
					menuMap.put(menu, menuSet);
					returnMap.put(menu.getAuthorityName(), menuSet);
				}
			}
			return RestModel.success(returnMap);
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST.getErrcode(), "TOKEN错误");
	}

	/**
	 * 按条件查询用户信息
	 * @param roleId
	 * @param userName
	 * @param mobile
	 * @param pageIndex
	 * @param pageSize
	 * @return
	 */
	public ResponseEntity<RestModel<List<UserDto>>> findUsersByConditions(Integer roleId, String userName, String mobile, Integer pageIndex,
			Integer pageSize) {
		if (pageSize != null && pageIndex != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<UserDto> users = userMapper.findUsersByConditions(roleId, userName, mobile);
		return RestModel.success(users, (int) users.getTotal());
	}

	/**
	 * 新增用户
	 * @param userDto
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> addUser(UserDto userDto, String token) {
		try {
			RestModel<Integer> registerInfo = authService.register(userDto.getMobile(), "123456");
			HqlsUser user = userMapper.getUserInfoByMobile(userDto.getMobile());
			Boolean flag = false;
			if (user == null) {
				user = new HqlsUser();
				user.setIsUseable(true);
				user.setMobile(userDto.getMobile());
				user.setPassword("123456");
				user.setUserName(userDto.getUserName());
				flag = true;// 用户信息不存在
			}
			Integer globalUserId = null;
			if (registerInfo.getErrcode() == 0) {// 注册成功
				globalUserId = registerInfo.getResult(); // 用户中心的编号
				user.setGlobalUserId(globalUserId);
				if (flag) {// 本系统中用户不存在
					userMapper.insert(user);
				} else {// 修改本系统中用户的全局用户ID
					userMapper.updateGlobalUserId(globalUserId, user.getUserId());
				}
			} else if (registerInfo.getErrcode() == 4006 || registerInfo.getErrmsg().contains("该用户已注册")) { // 用户已注册，则同步用户信息,
				/*
				 * RestModel<AuthUser> uInfo = authService.getUserInfoByUserName(token, userDto.getMobile());
				 * if (uInfo.getErrcode() == 0) {
				 * globalUserId = uInfo.getResult().getUserId();
				 * user.setGlobalUserId(globalUserId);
				 * if (flag) {// 本系统中用户不存在
				 * userMapper.insert(user);
				 * } else {// 修改本系统中用户的全局用户ID
				 * userMapper.updateGlobalUserId(globalUserId, user.getUserId());
				 * }
				 * } else {
				 * return RestModel.error(HttpStatus.BAD_REQUEST, uInfo.getErrcode(), uInfo.getErrmsg());
				 * }
				 */
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA, "用户已注册！");
			} else {
				return RestModel.error(HttpStatus.BAD_REQUEST, registerInfo.getErrcode(), registerInfo.getErrmsg());

			}
			// 插入用户角色信息
			List<HqlsRole> roles = userDto.getRoles();
			if (roles != null && roles.size() > 0) {
				userRoleMapper.deleteUserRolesByUserId(user.getUserId());
				for (HqlsRole role : roles) {
					HqlsUserRole userRole = new HqlsUserRole();
					userRole.setRoleId(role.getRoleId());
					userRole.setUserId(user.getUserId());
					userRoleMapper.insert(userRole);
				}
			}
			return RestModel.success("添加成功");
		} catch (Exception e) {
			e.printStackTrace();
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "添加失败");
	}

	public ResponseEntity<RestModel<String>> updateUserStatus(Integer userId) {
		int result = userMapper.updateUserStatus(userId);
		if (result == 1) {
			return RestModel.success("编辑成功");
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "编辑失败");
	}

	public ResponseEntity<RestModel<List<CommonDto>>> findAllRole() {

		return RestModel.success(roleMapper.findAll());
	}

	/**
	 * 修改用户账号信息
	 * 
	 * @return
	 */
	public ResponseEntity<RestModel<String>> updateUserAccount(String token, String newMobile) {
		// 获取当前用户
		RestModel<TokenModel> rest = authService.validToken(token);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_TOKEN);
		}
		Integer globalId = rest.getResult().getUserId();
		HqlsUser user = userMapper.getUserByGloabUserId(globalId);
		// 获取当前登录人userId
		Integer userId = user.getUserId();
		// 判断新账号在数据库是否存在
		HqlsUser hqlsUser = userMapper.getUserInfoByMobile(newMobile);
		if (hqlsUser != null) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "账号在数据中已存在！");
		} else {
			// 根据userId更新用户表的信息
			userMapper.updateUserByUserId(newMobile, userId);
		}
		return RestModel.success();
	}

	/**
	 * 修改用户信息（用户名，用户角色）
	 * @param userDto
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> updateUser(UserDto userDto) {
		HqlsUser user = userMapper.getUserById(userDto.getUserId());
		if (user == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST, "用户不存在！");
		}
		try {
			// 修改用户名
			user.setUserName(userDto.getUserName());
			userMapper.updateUser(user);
			// 删除该用户的所有角色
			userRoleMapper.deleteUserRolesByUserId(user.getUserId());
			// 插入用户角色信息
			List<HqlsRole> roles = userDto.getRoles();
			if (roles != null && roles.size() > 0) {
				for (HqlsRole role : roles) {
					HqlsUserRole userRole = new HqlsUserRole();
					userRole.setRoleId(role.getRoleId());
					userRole.setUserId(user.getUserId());
					userRoleMapper.insert(userRole);
				}
			}
			return RestModel.success("修改成功");
		} catch (Exception e) {
			TransactionAspectSupport.currentTransactionStatus().isRollbackOnly();
			e.printStackTrace();
		}
		return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION, "修改用户异常");
	}

	/**
	 * 根据用户Id查询用户信息
	 * @param userId
	 * @return
	 */
	public ResponseEntity<RestModel<UserDto>> getUser(Integer userId) {
		return RestModel.success(userMapper.getUserDtoByUserId(userId));
	}

	public ResponseEntity<RestModel<Boolean>> checkAuthorization(String token) {
		// 获取当前用户
		RestModel<TokenModel> rest = authService.validToken(token);
		if (rest.getErrcode() != 0) {// 解析token失败
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_TOKEN);
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		Integer count = userMapper.checkUser(userId);
		if (count == null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA);
		}
		return RestModel.success(true);
	}
	
	public ResponseEntity<RestModel<Boolean>> checkMobile(String mobile) {
		// 获取当前用户
		HqlsUser user = userMapper.getUserByMobile(mobile);
		if(user == null){
			return RestModel.success(false);
		}
		Integer count = userMapper.checkUser(user.getGlobalUserId());
		if (count == null) {
			return RestModel.success(false);
		}
		return RestModel.success(true);
	}

}