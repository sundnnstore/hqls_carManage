package com.sinoauto.dto;

import java.util.Date;
import java.util.List;
import com.sinoauto.dao.bean.HqlsParts;
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
	List<HqlsParts> parts;
	
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
//	@ApiModelProperty("配件名称")
//	private String partsName;
//	@ApiModelProperty("型号")
//	private String partsModel;
//	@ApiModelProperty("规格")
//	private String partsSpec;
//	@ApiModelProperty("购买金额")
//	private Double buyPrice;
	@ApiModelProperty("金额总计")
	private Double totalFee;
	@ApiModelProperty("购买数量")
	private Integer buyCount;
	/**
	 * 共有属性
	 */
	@ApiModelProperty("联系人姓名")
	private String userName;
	@ApiModelProperty("联系人电话")
	private String mobile;
	public Integer getOrderStatus() {
		return orderStatus;
	}
	public void setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
	}
	public Integer getStoreId() {
		return storeId;
	}
	public void setStoreId(Integer storeId) {
		this.storeId = storeId;
	}
	public String getOrderNo() {
		return orderNo;
	}
	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}
	public String getOrderStatusName() {
		return orderStatusName;
	}
	public void setOrderStatusName(String orderStatusName) {
		this.orderStatusName = orderStatusName;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public Integer getBuyCount() {
		return buyCount;
	}
	public void setBuyCount(Integer buyCount) {
		this.buyCount = buyCount;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public List<HqlsParts> getParts() {
		return parts;
	}
	public void setParts(List<HqlsParts> parts) {
		this.parts = parts;
	}
	public Double getTotalFee() {
		return totalFee;
	}
	public void setTotalFee(Double totalFee) {
		this.totalFee = totalFee;
	}
}
