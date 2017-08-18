package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsOrderDetail;

@Mapper
public interface OrderDetailMapper {

	public int insert(HqlsOrderDetail detail);
	
	@Select("select * from hqls_order_detail where order_id = #{1}")
	public List<HqlsOrderDetail> getDetailByOrderId(Integer orderId);
	
}