package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.c.CBanner;

@Mapper
public interface BannerMapper {
	
	@Select("select * from c_banner where is_delete = 0 order by banner_sort")
	public List<CBanner> findAllBanners();

}