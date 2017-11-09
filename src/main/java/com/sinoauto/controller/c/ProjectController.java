package com.sinoauto.controller.c;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.entity.RestModel;
import com.sinoauto.service.c.ProjectService;

import io.swagger.annotations.Api;
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

}
