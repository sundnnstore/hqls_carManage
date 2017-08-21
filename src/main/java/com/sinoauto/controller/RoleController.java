package com.sinoauto.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.HqlsRole;
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
	@GetMapping(value = "findusers")
	public ResponseEntity<RestModel<List<HqlsRole>>> findRoles(Integer pageIndex,Integer pageSize) {
		return null;
	}

}
