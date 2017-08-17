package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.sinoauto.dao.bean.HqlsPurchaseOrder;
import com.sinoauto.dto.PurchaseOrderParamDto;

@Mapper
public interface PurchaseOrderMapper {

	public int insert(HqlsPurchaseOrder order);
	
	@Select("select * from hqls_purchase_order where order_id = #{1}")
	public HqlsPurchaseOrder getOrderById(Integer orderId);
	
	public List<PurchaseOrderParamDto> findOrder(@Param("storeId") Integer storeId, @Param("orderStatus") Integer orderStatus);
	
	public PurchaseOrderParamDto getOrderByOrderId(@Param("orderId") Integer orderId);
	
	public int payOperation(HqlsPurchaseOrder order);
	
}