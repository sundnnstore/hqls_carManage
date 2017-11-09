package com.sinoauto.service.c;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.bean.c.CBanner;
import com.sinoauto.dao.bean.c.CServiceProject;
import com.sinoauto.dao.mapper.BannerMapper;
import com.sinoauto.dao.mapper.CServiceProjectMapper;
import com.sinoauto.dto.c.ProjectDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class ProjectService {

	@Autowired
	private CServiceProjectMapper projectMapper;
	@Autowired
	private BannerMapper bannerMapper;

	/**
	 * 获取首页数据
	 * @return
	 */
	public ResponseEntity<RestModel<Map<String, Object>>> getHomePageData() {
		Map<String, Object> map = new HashMap<>();
		// 查询首页banner图的数据
		List<CBanner> banners = bannerMapper.findAllBanners();
		map.put("banners", banners);
		// 查询第一级的服务项目列表
		List<CServiceProject> projects = projectMapper.findFirstProjects();
		map.put("projects", projects);
		return RestModel.success(map);
	}

	/**
	 * 通过第一级项目ID获取第二级项目名称及第三级项目集合
	 * @param projectId
	 * @return
	 */
	public ResponseEntity<RestModel<List<ProjectDto>>> findProjectsByFirstProjectId(Integer projectId) {
		CServiceProject project = projectMapper.getProjectByProjectId(projectId);
		if (project == null || project.getPid() != 0 || project.getStoreId() != 0) {
			return RestModel.error(HttpStatus.OK, ErrorStatus.INVALID_DATA.getErrcode(), "服务项目ID有误");
		}
		List<CServiceProject> secondProjects = projectMapper.findProjectsByPid(project.getServiceProjectId());
		if (secondProjects == null || secondProjects.size() == 0) {
			return RestModel.error(HttpStatus.OK, ErrorStatus.INVALID_DATA.getErrcode(), "服务项目无下属项目");
		}
		List<ProjectDto> projects = new ArrayList<>();
		for (CServiceProject p : secondProjects) {
			ProjectDto projectDto = new ProjectDto();
			projectDto.setProjectName(p.getServiceProjectName());
			List<CServiceProject> serviceProjects = projectMapper.findProjectsByPid(p.getServiceProjectId());
			projectDto.setProjects(serviceProjects);
			projects.add(projectDto);
		}
		return RestModel.success(projects);
	}

}