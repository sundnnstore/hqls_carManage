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
	
	@Update("update hqls_service_order set order_status = 2 ,finish_time = now() where service_order_id = #{orderId}")
	public int updateOrderStauts(@Param("orderId")Integer orderId);
	
	public int insert(ServiceOrderDto order);
	
	@Select("select service_order_id from hqls_service_order where code = #{code} and order_status = 1 and order_type = 1")
	public Integer getOrderIdByCode(@Param("code")String code);

}