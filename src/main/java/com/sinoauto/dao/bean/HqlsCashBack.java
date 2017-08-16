package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 返利表返利表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsCashBack implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("返利ID")
	private Integer cashBackId;
	@ApiModelProperty("充值金额")
	private Double money;
	@ApiModelProperty("返利率")
	private Double discount;
	@ApiModelProperty("返还金额")
	private Double returnMoney;
	@ApiModelProperty("返还类型（1返利率 2返还金额）")
	private Integer returnType;
	@ApiModelProperty("是否启用")
	private Integer isUsable;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置返利ID
	 * @param cashBackId 返利ID
	 */
	public void setCashBackId(Integer cashBackId) {
		this.cashBackId = cashBackId;
	}
	/**
	 * 获取返利ID
	 * @return 返利ID
	 */
	public Integer getCashBackId() {
		return this.cashBackId;
	}
	/**
	 * 设置充值金额
	 * @param money 充值金额
	 */
	public void setMoney(Double money) {
		this.money = money;
	}
	/**
	 * 获取充值金额
	 * @return 充值金额
	 */
	public Double getMoney() {
		return this.money;
	}
	/**
	 * 设置返利率
	 * @param discount 返利率
	 */
	public void setDiscount(Double discount) {
		this.discount = discount;
	}
	/**
	 * 获取返利率
	 * @return 返利率
	 */
	public Double getDiscount() {
		return this.discount;
	}
	/**
	 * 设置返还金额
	 * @param returnMoney 返还金额
	 */
	public void setReturnMoney(Double returnMoney) {
		this.returnMoney = returnMoney;
	}
	/**
	 * 获取返还金额
	 * @return 返还金额
	 */
	public Double getReturnMoney() {
		return this.returnMoney;
	}
	/**
	 * 设置返还类型（1返利率 2返还金额）
	 * @param returnType 返还类型（1返利率 2返还金额）
	 */
	public void setReturnType(Integer returnType) {
		this.returnType = returnType;
	}
	/**
	 * 获取返还类型（1返利率 2返还金额）
	 * @return 返还类型（1返利率 2返还金额）
	 */
	public Integer getReturnType() {
		return this.returnType;
	}
	/**
	 * 设置是否启用
	 * @param isUsable 是否启用
	 */
	public void setIsUsable(Integer isUsable) {
		this.isUsable = isUsable;
	}
	/**
	 * 获取是否启用
	 * @return 是否启用
	 */
	public Integer getIsUsable() {
		return this.isUsable;
	}
	/**
	 * 设置创建时间
	 * @param createTime 创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取创建时间
	 * @return 创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}

}

