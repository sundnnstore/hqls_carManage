package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.c.CServiceProject;


@Mapper
public interface CServiceProjectMapper {
	
	@Select("select * from c_service_project where pid = 0 and store_id = 0 order by project_sort")
	public List<CServiceProject> findFirstProjects();
	
	@Select("select * from c_service_project where service_project_id =#{projectId}")
	public CServiceProject getProjectByProjectId(@Param("projectId")Integer projectId);
	
	@Select("select * from c_service_project where pid = #{pid}")
	public List<CServiceProject> findProjectsByPid(@Param("pid")Integer pid);

}