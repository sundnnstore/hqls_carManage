package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * <br>
 * @author tangwt
 * @version 1.0, 2017-10-16
 */

public class HqlsPartsModel implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("车辆配件ID")
	private Integer id;
	@ApiModelProperty("配件ID")
	private Integer partsId;
	@ApiModelProperty("车型ID")
	private Integer modelId;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置车辆配件ID
	 * @param id 车辆配件ID
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取车辆配件ID
	 * @return 车辆配件ID
	 */
	public Integer getId() {
		return this.id;
	}
	/**
	 * 设置配件ID
	 * @param partsId 配件ID
	 */
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	/**
	 * 获取配件ID
	 * @return 配件ID
	 */
	public Integer getPartsId() {
		return this.partsId;
	}
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

