package com.sinoauto.dto;

import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 采购订单dto
 *@author liud
 *@Date2017年8月17日下午5:13:08
 *
 */
public class PurchaseOrderDto {
	/**
	 * 查询条件
	 */
	@ApiModelProperty("订单状态")
	private Integer orderStatus;
	@ApiModelProperty("门店ID")
	private Integer storeId;
	
	/**
	 * 返回集合属性
	 */
	@ApiModelProperty("订单编号")
	private String orderNo;
	@ApiModelProperty("订单状态名称")
	private String orderStatusName;
	@ApiModelProperty("订单创建时间")
	private Date createTime;
	@ApiModelProperty("收货地址")
	private String address;
	@ApiModelProperty("门店名称")
	private String storeName;
	@ApiModelProperty("配件名称")
	private String partsName;
	@ApiModelProperty("型号")
	private String partsModel;
	@ApiModelProperty("规格")
	private String partsSpec;
	@ApiModelProperty("现价")
	private Double curPrice;
	/**
	 * 共有属性
	 */
	@ApiModelProperty("联系人姓名")
	private String name;
	@ApiModelProperty("联系人电话")
	private String mobile;
}
