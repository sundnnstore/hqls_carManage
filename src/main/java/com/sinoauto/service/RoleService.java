package com.sinoauto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsRole;
import com.sinoauto.dao.mapper.AuthorityMapper;
import com.sinoauto.dao.mapper.RoleAuthorityMapper;
import com.sinoauto.dao.mapper.RoleMapper;
import com.sinoauto.dto.AuthorityDto;
import com.sinoauto.dto.RoleDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class RoleService {

	@Autowired
	private RoleMapper roleMapper;

	@Autowired
	private AuthorityMapper authorityMapper;

	@Autowired
	private RoleAuthorityMapper roleAuthorityMapper;

	public ResponseEntity<RestModel<List<HqlsRole>>> findRoles(Integer pageIndex, Integer pageSize) {
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<HqlsRole> roles = roleMapper.findRoles();
		return RestModel.success(roles, (int) roles.getTotal());
	}

	public ResponseEntity<RestModel<Integer>> checkRole(Integer roleId) {
		return RestModel.success(roleMapper.checkRoles(roleId));
	}

	public ResponseEntity<RestModel<List<AuthorityDto>>> findAllAuthoritys() {
		return RestModel.success(authorityMapper.findAllAuthorities());
	}

	@Transactional
	public ResponseEntity<RestModel<String>> addAuthority(RoleDto roleDto) {
		HqlsRole role = roleMapper.checkRoleIsExit(roleDto.getRoleName());
		if (role != null) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "角色已存在！");
		}
		roleMapper.insertRole(roleDto);// 插入角色信息
		List<Integer> authoritys = roleDto.getAuthorityIds();
		for (Integer authorityId : authoritys) {
			roleMapper.insertRoleAuthority(roleDto.getRoleId(), authorityId);
		}
		return RestModel.success("success");

	}

	@Transactional
	public ResponseEntity<RestModel<String>> editAuthority(RoleDto roleDto) {
		HqlsRole role = roleMapper.checkRoleIsExit(roleDto.getRoleName());
		if (role != null && (role.getRoleId() != roleDto.getRoleId())) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "角色已存在！");
		}
		roleMapper.updateRoleName(roleDto);
		// 删除之前对应的权限
		roleAuthorityMapper.deleteByRoleId(roleDto.getRoleId());
		List<Integer> authoritys = roleDto.getAuthorityIds();
		for (Integer authorityId : authoritys) {
			roleMapper.insertRoleAuthority(roleDto.getRoleId(), authorityId);
		}
		return RestModel.success("success");

	}
	
	@Transactional
	public ResponseEntity<RestModel<String>> delRole(Integer roleId){
		roleAuthorityMapper.deleteByRoleId(roleId);
		roleMapper.delRoleByRoleId(roleId);
		return RestModel.success();
	}

	public ResponseEntity<RestModel<List<AuthorityDto>>> findCheckedAuthoritys(Integer roleId) {
		// 所有菜单
		List<AuthorityDto> allAuthoritys = authorityMapper.findAllAuthorities();
		List<AuthorityDto> checkedAuthoritys = authorityMapper.findCheckedAuthoritiesByRoleId(roleId);
		List<AuthorityDto> res = new ArrayList<>();
		for (AuthorityDto au : allAuthoritys) {
			if (checkedAuthoritys.contains(au)) {
				au.setChecked(true);
			}
			res.add(au);
		}
		return RestModel.success(res);
	}

}