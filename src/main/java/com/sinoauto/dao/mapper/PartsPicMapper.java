package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Param;

import com.sinoauto.dao.bean.HqlsPartsPic;

@Mapper
public interface PartsPicMapper {

	@Select("select url from hqls_parts_pic where parts_id = #{1} order by sorting limit 0,1")
	public String getFirstUrlByPartsId(Integer partsId);
	
	/**
	 *  新增商品图片
	 * 	@User liud
	 * 	@Date 2017年8月17日下午4:08:24
	 * 	@param hqlsPartsPic
	 */
	public void insert(HqlsPartsPic hqlsPartsPic);
	
	/**
	 *  删除配件id对应的所有图片
	 * 	@User liud
	 * 	@Date 2017年8月17日下午4:40:57
	 * 	@param partsId
	 */
	public void delete(@Param("partsId")Integer partsId);
}