package com.sinoauto.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsAuthority;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.AuthorityMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.UserDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;

@Service
public class UserService {

	@Autowired
	private AuthService authService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private AuthorityMapper authorityMapper;

	/**
	 * 登录
	 * @param username
	 * @param password
	 * @return
	 */
	public ResponseEntity<RestModel<String>> login(String userName, String passWord) {
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

	public ResponseEntity<RestModel<Map<String, Set<HqlsAuthority>>>> findUserAuthority(String token, Integer isBack) {
		// 获取当前用户
		RestModel<TokenModel> rest = authService.validToken(token);
		if (rest.getErrcode() != 0) {// 解析token失败
			return null;
		}
		Integer userId = rest.getResult().getUserId();// 当前登录人的userid
		HqlsUser user = userMapper.getUserByGloabUserId(userId);
		if (user != null) {
			List<HqlsAuthority> firstAuthority = authorityMapper.findFirstAuthorities(userId, isBack);
			List<HqlsAuthority> secondAuthority = authorityMapper.findSecondAuthorities(userId, isBack);
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

}