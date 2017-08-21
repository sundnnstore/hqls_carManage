package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.HqlsRole;
import com.sinoauto.dto.AuthorityDto;
import com.sinoauto.dto.RoleDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.RoleService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@Api(tags="角色")
@RestController
public class RoleController {
	
	@Autowired
	private RoleService roleService;
	
	
	@ApiOperation(value = "查找角色集合", notes = "tangwt")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "pageIndex", value = "页码", required = true, dataType = "int"),
			@ApiImplicitParam(paramType = "query", name = "pageSize", value = "每页条数", required = true, dataType = "int") })
	@GetMapping(value = "findroles")
	public ResponseEntity<RestModel<List<HqlsRole>>> findRoles(Integer pageIndex,Integer pageSize) {
		return roleService.findRoles(pageIndex, pageSize);
	}
	
	@ApiOperation(value = "判断角色下是否有用户存在", notes = "tangwt")
	@PostMapping(value = "checkrole")
	public ResponseEntity<RestModel<Integer>> checkRole(@RequestParam("roleId")Integer roleId) {
		return roleService.checkRole(roleId);
	}
	
	@ApiOperation(value = "查找所有菜单", notes = "tangwt")
	@GetMapping(value = "findallauthoritys")
	public ResponseEntity<RestModel<List<AuthorityDto>>> findAllAuthoritys(){
		return roleService.findAllAuthoritys();
	}
	
	@ApiOperation(value = "添加角色", notes = "tangwt")
	@PostMapping(value = "addrole")
	public ResponseEntity<RestModel<String>> addAuthority(@RequestBody RoleDto roleDto){
		return roleService.addAuthority(roleDto);
	}
	
	@ApiOperation(value = "角色编辑角色", notes = "tangwt")
	@PostMapping(value = "editrole")
	public ResponseEntity<RestModel<String>> editAuthority(@RequestBody RoleDto roleDto){
		return roleService.editAuthority(roleDto);
	}
	
	@ApiOperation(value = "删除角色", notes = "tangwt")
	@PostMapping(value = "delrole")
	public ResponseEntity<RestModel<String>> delRole(@RequestParam("roleId")Integer roleId){
		return roleService.delRole(roleId);
	}
	
	@ApiOperation(value = "查找已选菜单", notes = "tangwt")
	@PostMapping(value = "findcheckedauthoritys")
	public ResponseEntity<RestModel<List<AuthorityDto>>> findCheckedAuthoritys(@RequestParam("roleId") Integer roleId){
		return roleService.findCheckedAuthoritys(roleId);
	}
	

}
