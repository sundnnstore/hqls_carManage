package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsPurchaseOrder;
import com.sinoauto.dto.PurchaseOrderParamDto;
import com.sinoauto.dto.PurchaseOrderQueryDto;
import com.sinoauto.dto.ShopCartInfoDto;
import com.sinoauto.dto.PurchaseOrderDto;

@Mapper
public interface PurchaseOrderMapper {

	public int insert(HqlsPurchaseOrder order);
	
	/**
	 * 根据Id查询订单
	 * @param orderId
	 * @return
	 * @author wuxiao
	 */
	@Select("select * from hqls_purchase_order where purchase_order_id = #{1}")
	public HqlsPurchaseOrder getOrderById(Integer orderId);
	
	/**
	 * 根据门店Id和订单状态查询订单列表
	 * @param storeId
	 * @param orderStatus
	 * @return
	 */
	public List<PurchaseOrderParamDto> findOrder(@Param("storeId") Integer storeId, @Param("orderStatus") Integer orderStatus);
	
	/**
	 * 查询订单进入待支付页面
	 * @param orderId
	 * @return
	 * @author wuxiao
	 */
	public ShopCartInfoDto getOrderByOrderId(@Param("orderId") Integer orderId);
	
	/**
	 * 支付操作
	 * @param order
	 * @return
	 * @author wuxiao
	 */
	public int payOperation(HqlsPurchaseOrder order);
	/**
	 * 	查询订单列表
	 * 	@User liud
	 * 	@Date 2017年8月17日下午6:06:04
	 * 	@param purchaseOrderDto
	 * 	@return
	 */
	public List<PurchaseOrderDto> findPurchaseOrderByContidion(PurchaseOrderQueryDto purchaseOrderDto);

	/**
	 * 	确认发货
	 * 	@User liud
	 * 	@Date 2017年8月17日下午7:43:59
	 * 	@param order
	 */
	@Update("update hqls_purchase_order poo set poo.logistics_id=#{logisticsId},logistics_no=#{logisticsNo},remark=#{remark} where poo.purchase_order_id=#{purchaseOrderId}")
	public void confirmShipment(HqlsPurchaseOrder order);
	
	/**
	 * 确认收货
	 * @param order
	 * @author wuxiao
	 */
	@Update("update hqls_purchase_order poo set poo.order_status = #{orderStatus} where poo.purchase_order_id=#{purchaseOrderId}")
	public void confirmReceipt(HqlsPurchaseOrder order);
	
	@Update("update hqls_purchase_order set order_status = #{orderStatus}, dml_time = now() where purchase_order_id = #{purchaseOrderId}")
	public int updateOrderStatus(HqlsPurchaseOrder order);
	
	public int update(HqlsPurchaseOrder order);
	
	public Page<PurchaseOrderDto> findOrderListByContidion(@Param("orderStatus") Integer orderStatus, @Param("storeId") Integer storeId, 
			@Param("userName") String userName, @Param("mobile") String mobile);
	
	@Update("update hqls_purchase_order set order_status = 2 where order_no = #{1} and order_status <> 2")
	public int updateOrderStatusForPay(String orderNo);
}