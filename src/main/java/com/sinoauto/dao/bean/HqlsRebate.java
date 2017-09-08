package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 返利表
 * @author wuxiao
 * @version 1.0
 * @date 2017-09-06 16:12:25
 */
public class HqlsRebate implements Serializable {

	private static final long serialVersionUID = 1L;

	@ApiModelProperty("返利Id")
	private Integer rebateId;
	@ApiModelProperty("门店Id")
	private Integer storeId;
	@ApiModelProperty("返利金额")
	private Double rebateMoney;
	@ApiModelProperty("当前返利期数")
	private Integer rebatePeriod;
	@ApiModelProperty("是否已返利（1-是；0-否）")
	private Boolean isRebate;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	@ApiModelProperty("返利时间")
	private Date rebateTime;

	public Integer getRebateId() {
		return rebateId;
	}

	public void setRebateId(Integer rebateId) {
		if (rebateId != null) {
			this.rebateId = rebateId;
		}
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		if (storeId != null) {
			this.storeId = storeId;
		}
	}

	public Double getRebateMoney() {
		return rebateMoney;
	}

	public void setRebateMoney(Double rebateMoney) {
		if (rebateMoney != null) {
			this.rebateMoney = rebateMoney;
		}
	}

	public Integer getRebatePeriod() {
		return rebatePeriod;
	}

	public void setRebatePeriod(Integer rebatePeriod) {
		if (rebatePeriod != null) {
			this.rebatePeriod = rebatePeriod;
		}
	}

	public Boolean getIsRebate() {
		return isRebate;
	}

	public void setIsRebate(Boolean isRebate) {
		if (isRebate != null) {
			this.isRebate = isRebate;
		}
	}

	public Date getDmlTime() {
		return dmlTime;
	}

	public void setDmlTime(Date dmlTime) {
		if (dmlTime != null) {
			this.dmlTime = dmlTime;
		}
	}

	public Date getRebateTime() {
		return rebateTime;
	}

	public void setRebateTime(Date rebateTime) {
		if (rebateTime != null) {
			this.rebateTime = rebateTime;
		}
	}

}
