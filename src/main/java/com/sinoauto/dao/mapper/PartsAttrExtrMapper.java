package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import com.sinoauto.dao.bean.HqlsPartsAttrExtr;

@Mapper
public interface PartsAttrExtrMapper {

	/**
	 * 	新增配件的扩展属性
	 * 	@User liud
	 * 	@Date 2017年8月17日下午4:10:14
	 * 	@param hqlsPartsAttrExtr
	 */
	public void insert(HqlsPartsAttrExtr hqlsPartsAttrExtr);
	
	/**
	 * 	删除配件对应的动态参数配置
	 * 	@User liud
	 * 	@Date 2017年8月17日下午4:43:31
	 * 	@param partsId
	 */
	public void delete(Integer partsId);
}