package com.sinoauto.controller.c;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.c.ProjectDto;
import com.sinoauto.dto.c.ProjectOrderDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.c.ProjectService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "C端服务项目")
public class ProjectController {

	@Autowired
	private ProjectService projectService;

	@ApiOperation(value = "获取首页banner和服务一级项目数据", notes = "tangwt")
	@GetMapping("gethomepagedata")
	public ResponseEntity<RestModel<Map<String, Object>>> getHomePageData() {
		return projectService.getHomePageData();
	}

	@ApiOperation(value = "通过第一级项目ID获取第二级项目名称及第三级项目集合", notes = "tangwt")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "projectId", value = "第一级项目ID", required = true, dataType = "int") })
	@GetMapping("findprojectsbyfirstprojectid")
	public ResponseEntity<RestModel<List<ProjectDto>>> findProjectsByFirstProjectId(Integer projectId) {
		return projectService.findProjectsByFirstProjectId(projectId);
	}
	
	
	@ApiOperation(value = "单击小项目进入项目购买流程", notes = "tangwt")
	@ApiImplicitParams({ @ApiImplicitParam(paramType = "query", name = "projectId", value = "项目ID", required = true, dataType = "int"),
		@ApiImplicitParam(paramType = "query", name = "lat", value = "用户当前位置经度", required = false, dataType = "double"),
		@ApiImplicitParam(paramType = "query", name = "lng", value = "用户当前位置纬度", required = false, dataType = "double")})
	@GetMapping("getprojectorderinfo")
	public ResponseEntity<RestModel<ProjectOrderDto>> getProjectOrderInfo(@RequestHeader String Authorization, Integer projectId,Double lat,Double lng) {
		return projectService.getProjectOrderInfo(Authorization, projectId, lat, lng);
	}

}
