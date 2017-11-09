package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.c.CServiceProject;


@Mapper
public interface CServiceProjectMapper {
	
	@Select("select * from c_service_project where pid = 0 and store_id = 0")
	public List<CServiceProject> findFirstProjects();

}