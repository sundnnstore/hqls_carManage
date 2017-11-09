package com.sinoauto.dto.c;

import java.util.List;

import com.sinoauto.dao.bean.c.CServiceProject;

import io.swagger.annotations.ApiModelProperty;

public class ProjectDto {

	@ApiModelProperty("第二级项目名称")
	private String projectName;
	@ApiModelProperty("第三级服务项目集合")
	private List<CServiceProject> projects;

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		if (projectName != null) {
			this.projectName = projectName;
		}
	}

	public List<CServiceProject> getProjects() {
		return projects;
	}

	public void setProjects(List<CServiceProject> projects) {
		if (projects != null) {
			this.projects = projects;
		}
	}

}
