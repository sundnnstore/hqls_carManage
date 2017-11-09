package com.sinoauto.service.c;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.bean.c.CBanner;
import com.sinoauto.dao.bean.c.CServiceProject;
import com.sinoauto.dao.mapper.BannerMapper;
import com.sinoauto.dao.mapper.CServiceProjectMapper;
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

}