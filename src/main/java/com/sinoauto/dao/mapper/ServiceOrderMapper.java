package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsServiceOrder;
import com.sinoauto.dto.ServiceOrderDto;

@Mapper
public interface ServiceOrderMapper {
	
	public Page<ServiceOrderDto> findServiceOrdersByOrderStatus(@Param("orderStatus")Integer orderStatus,@Param("storeId")Integer storeId);
	
	@Select("select * from hqls_service_order where service_order_id = #{orderId}")
	public HqlsServiceOrder getServiceOrderByOrderId(@Param("orderId")Integer orderId);
	
	@Update("update hqls_service_order set order_status = 2 where service_order_id = #{orderId}")
	public int updateOrderStauts(@Param("orderId")Integer orderId);

}