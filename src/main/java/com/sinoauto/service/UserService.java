package com.sinoauto.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsAuthority;
import com.sinoauto.dao.bean.HqlsRole;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.bean.HqlsUserRole;
import com.sinoauto.dao.mapper.AuthorityMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dao.mapper.UserRoleMapper;
import com.sinoauto.dto.UserDto;
import com.sinoauto.dto.UserLoginDto;
import com.sinoauto.entity.AuthUser;
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
			Map<HqlsAuthority, Set<HqlsAuthority>> menuMap = new HashMap<>();
			Map<String, Set<HqlsAuthority>> returnMap = new HashMap<>();
			for (HqlsAuthority menu : firstAuthority) {
				if (!menuMap.containsKey(menu)) {
					Set<HqlsAuthority> menuSet = new HashSet<>();
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
				RestModel<AuthUser> uInfo = authService.getUserInfoByUserName(token, userDto.getMobile());
				if (uInfo.getErrcode() == 0) {
					globalUserId = uInfo.getResult().getUserId();
					user.setGlobalUserId(globalUserId);
					if (flag) {// 本系统中用户不存在
						userMapper.insert(user);
					} else {// 修改本系统中用户的全局用户ID
						userMapper.updateGlobalUserId(globalUserId, user.getUserId());
					}
				} else {
					return RestModel.error(HttpStatus.BAD_REQUEST, uInfo.getErrcode(), uInfo.getErrmsg());
				}
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

}