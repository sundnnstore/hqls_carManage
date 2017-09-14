package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsDict;

@Mapper
public interface DictMapper {

	@Select("select description from hqls_dict where dict_key = #{dictKey} and dict_value = #{dictValue}")
	public String getDescByKeyAndValue(@Param("dictKey") String dictKey, @Param("dictValue")  String dictValue);
	
	@Select("select description from hqls_dict where dict_key = 'order_status' and dict_value = #{1}")
	public String getOrderStatusDesc(Integer orderStatus);
	
	@Select("select * from hqls_dict where dict_key = #{1}")
	public List<HqlsDict> findDictByKey(String dictKey);
	
	/**
	 * 获取物流费用
	 * @param dictKey = "other_fee"
	 * @return
	 */
	@Select("select dict_value from hqls_dict where dict_key = #{1}")
	public String getOtherFee(String dictKey);
}