package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 车系基础表车系基础表<br>
 * @author tangwt
 * @version 1.0, 2017-10-16
 */

public class HqlsCarSeries implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("车系ID")
	private Integer seriesId;
	@ApiModelProperty("品牌Id")
	private Integer brandId;
	@ApiModelProperty("车系名称")
	private String seriesName;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("记录创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	@ApiModelProperty("操作标识（新增-1，修改-2，删除-3）")
	private Boolean dmlFlag;
	
	/**
	 * 设置车系ID
	 * @param seriesId 车系ID
	 */
	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}
	/**
	 * 获取车系ID
	 * @return 车系ID
	 */
	public Integer getSeriesId() {
		return this.seriesId;
	}
	/**
	 * 设置品牌Id
	 * @param brandId 品牌Id
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取品牌Id
	 * @return 品牌Id
	 */
	public Integer getBrandId() {
		return this.brandId;
	}
	/**
	 * 设置车系名称
	 * @param seriesName 车系名称
	 */
	public void setSeriesName(String seriesName) {
		this.seriesName = seriesName;
	}
	/**
	 * 获取车系名称
	 * @return 车系名称
	 */
	public String getSeriesName() {
		return this.seriesName;
	}
	/**
	 * 设置备注
	 * @param remark 备注
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}
	/**
	 * 获取备注
	 * @return 备注
	 */
	public String getRemark() {
		return this.remark;
	}
	/**
	 * 设置记录创建时间
	 * @param createTime 记录创建时间
	 */
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	/**
	 * 获取记录创建时间
	 * @return 记录创建时间
	 */
	public Date getCreateTime() {
		return this.createTime;
	}
	/**
	 * 设置操作时间
	 * @param dmlTime 操作时间
	 */
	public void setDmlTime(Date dmlTime) {
		this.dmlTime = dmlTime;
	}
	/**
	 * 获取操作时间
	 * @return 操作时间
	 */
	public Date getDmlTime() {
		return this.dmlTime;
	}
	/**
	 * 设置操作标识（新增-1，修改-2，删除-3）
	 * @param dmlFlag 操作标识（新增-1，修改-2，删除-3）
	 */
	public void setDmlFlag(Boolean dmlFlag) {
		this.dmlFlag = dmlFlag;
	}
	/**
	 * 获取操作标识（新增-1，修改-2，删除-3）
	 * @return 操作标识（新增-1，修改-2，删除-3）
	 */
	public Boolean getDmlFlag() {
		return this.dmlFlag;
	}

}

