package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 活动表活动表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CActivity implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("活动ID")
	private Integer activityId;
	@ApiModelProperty("活动名称")
	private String activityName;
	@ApiModelProperty("活动的logo图片")
	private String logoUrl;
	@ApiModelProperty("活动开始时间")
	private Date beginTime;
	@ApiModelProperty("活动结束时间")
	private Date endTime;
	@ApiModelProperty("活动地点")
	private String activityLocation;
	@ApiModelProperty("活动描述")
	private String activityDesc;
	@ApiModelProperty("是否删除")
	private Boolean isDelete;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置活动ID
	 * @param activityId 活动ID
	 */
	public void setActivityId(Integer activityId) {
		this.activityId = activityId;
	}
	/**
	 * 获取活动ID
	 * @return 活动ID
	 */
	public Integer getActivityId() {
		return this.activityId;
	}
	/**
	 * 设置活动名称
	 * @param activityName 活动名称
	 */
	public void setActivityName(String activityName) {
		this.activityName = activityName;
	}
	/**
	 * 获取活动名称
	 * @return 活动名称
	 */
	public String getActivityName() {
		return this.activityName;
	}
	/**
	 * 设置活动的logo图片
	 * @param logoUrl 活动的logo图片
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取活动的logo图片
	 * @return 活动的logo图片
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}
	/**
	 * 设置活动开始时间
	 * @param beginTime 活动开始时间
	 */
	public void setBeginTime(Date beginTime) {
		this.beginTime = beginTime;
	}
	/**
	 * 获取活动开始时间
	 * @return 活动开始时间
	 */
	public Date getBeginTime() {
		return this.beginTime;
	}
	/**
	 * 设置活动结束时间
	 * @param endTime 活动结束时间
	 */
	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}
	/**
	 * 获取活动结束时间
	 * @return 活动结束时间
	 */
	public Date getEndTime() {
		return this.endTime;
	}
	/**
	 * 设置活动地点
	 * @param activityLocation 活动地点
	 */
	public void setActivityLocation(String activityLocation) {
		this.activityLocation = activityLocation;
	}
	/**
	 * 获取活动地点
	 * @return 活动地点
	 */
	public String getActivityLocation() {
		return this.activityLocation;
	}
	/**
	 * 设置活动描述
	 * @param activityDesc 活动描述
	 */
	public void setActivityDesc(String activityDesc) {
		this.activityDesc = activityDesc;
	}
	/**
	 * 获取活动描述
	 * @return 活动描述
	 */
	public String getActivityDesc() {
		return this.activityDesc;
	}
	/**
	 * 设置是否删除
	 * @param isDelete 是否删除
	 */
	public void setIsDelete(Boolean isDelete) {
		this.isDelete = isDelete;
	}
	/**
	 * 获取是否删除
	 * @return 是否删除
	 */
	public Boolean getIsDelete() {
		return this.isDelete;
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

