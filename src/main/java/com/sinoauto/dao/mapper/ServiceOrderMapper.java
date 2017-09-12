package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsServiceOrder;
import com.sinoauto.dto.CustomerInfoDto;
import com.sinoauto.dto.ServiceOrderDto;

@Mapper
public interface ServiceOrderMapper {

	public Page<ServiceOrderDto> findServiceOrdersByOrderStatus(@Param("orderStatus") Integer orderStatus, @Param("storeId") Integer storeId);

	@Select("select * from hqls_service_order where service_order_id = #{orderId}")
	public HqlsServiceOrder getServiceOrderByOrderId(@Param("orderId") Integer orderId);

	@Update("update hqls_service_order set order_status = 2 ,finish_time = now() where service_order_id = #{orderId}")
	public int updateOrderStauts(@Param("orderId") Integer orderId);

	public int insert(ServiceOrderDto order);

	@Select("select service_order_id from hqls_service_order where code = #{code} and order_status = 1 and order_type = 1")
	public Integer getOrderIdByCode(@Param("code") String code);

	@Update("update hqls_service_order set total_amount = total_amount+#{orderAmount} where service_order_id = #{serviceOrderId}")
	public int updateTotalAmount(@Param("serviceOrderId") Integer serviceOrderId, @Param("orderAmount") Double orderAmount);

	@Select("SELECT hc.avatar_url,hc.customer_name,hso.car_model,hso.car_no,hso.customer_id,hso.store_id,"
			+ "(SELECT count(1) FROM hqls_service_order  ho WHERE ho.customer_id = hso.customer_id AND ho.store_id = hso.store_id) as totalServiceCount"
			+ " FROM hqls_service_order hso" + " LEFT JOIN hqls_customer hc" + " ON hso.customer_id = hc.customer_id"
			+ " WHERE service_order_id = #{serviceOrderId}")
	@ResultType(value = CustomerInfoDto.class)
	public CustomerInfoDto getCustomerInfoByServiceOrderId(@Param("serviceOrderId") Integer serviceOrderId);
	
	@Select("SELECT hst.service_type_name FROM hqls_service_type hst LEFT JOIN hqls_service_order hs ON hst.service_type_id = hs.service_type_id WHERE hs.customer_id = #{customerId} and hs.store_id = #{storeId} ORDER BY hs.create_time DESC LIMIT 3")
	public List<String> getServiceTypesByCustomerIdAndStoreId(@Param("storeId")Integer storeId,@Param("customerId")Integer customerId);

}