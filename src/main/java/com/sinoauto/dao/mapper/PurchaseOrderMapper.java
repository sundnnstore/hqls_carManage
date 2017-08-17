package com.sinoauto.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Update;
import com.sinoauto.dao.bean.HqlsPurchaseOrder;
import com.sinoauto.dto.PurchaseOrderDto;

@Mapper
public interface PurchaseOrderMapper {

	public int insert(HqlsPurchaseOrder order);
	
	/**
	 * 	查询配件列表
	 * 	@User liud
	 * 	@Date 2017年8月17日下午6:06:04
	 * 	@param purchaseOrderDto
	 * 	@return
	 */
	public List<PurchaseOrderDto> findPurchaseOrderByContidion(PurchaseOrderDto purchaseOrderDto);

	/**
	 * 	确认发货
	 * 	@User liud
	 * 	@Date 2017年8月17日下午7:43:59
	 * 	@param order
	 */
	@Update("update hqls_purchase_order poo set poo.logistics_id=#{logisticsId},logistics_no=#{logisticsNo} where poo.purchase_order_id=#{purchaseOrderId}")
	public void confirmShipment(HqlsPurchaseOrder order);
	
	
}