package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 车型表车型表<br>
 * @author tangwt
 * @version 1.0, 2017-10-16
 */

public class HqlsCarModel implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("车型ID")
	private Integer modelId;
	@ApiModelProperty("车系Id")
	private Integer seriesId;
	@ApiModelProperty("厂商Id")
	private Integer factoryId;
	@ApiModelProperty("车型名称")
	private String modelName;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("记录创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	@ApiModelProperty("操作标识（新增-1，修改-2，删除-3）")
	private Boolean dmlFlag;
	
	/**
	 * 设置车型ID
	 * @param modelId 车型ID
	 */
	public void setModelId(Integer modelId) {
		this.modelId = modelId;
	}
	/**
	 * 获取车型ID
	 * @return 车型ID
	 */
	public Integer getModelId() {
		return this.modelId;
	}
	/**
	 * 设置车系Id
	 * @param seriesId 车系Id
	 */
	public void setSeriesId(Integer seriesId) {
		this.seriesId = seriesId;
	}
	/**
	 * 获取车系Id
	 * @return 车系Id
	 */
	public Integer getSeriesId() {
		return this.seriesId;
	}
	/**
	 * 设置厂商Id
	 * @param factoryId 厂商Id
	 */
	public void setFactoryId(Integer factoryId) {
		this.factoryId = factoryId;
	}
	/**
	 * 获取厂商Id
	 * @return 厂商Id
	 */
	public Integer getFactoryId() {
		return this.factoryId;
	}
	/**
	 * 设置车型名称
	 * @param modelName 车型名称
	 */
	public void setModelName(String modelName) {
		this.modelName = modelName;
	}
	/**
	 * 获取车型名称
	 * @return 车型名称
	 */
	public String getModelName() {
		return this.modelName;
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

