package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 配件图片表配件图片表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsPartsPic implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("配件图片ID")
	private Integer partsPicId;
	@ApiModelProperty("配件ID")
	private Integer partsId;
	@ApiModelProperty("图片url")
	private String url;
	@ApiModelProperty("排序")
	private Integer sorting;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置配件图片ID
	 * @param partsPicId 配件图片ID
	 */
	public void setPartsPicId(Integer partsPicId) {
		this.partsPicId = partsPicId;
	}
	/**
	 * 获取配件图片ID
	 * @return 配件图片ID
	 */
	public Integer getPartsPicId() {
		return this.partsPicId;
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
	 * 设置图片url
	 * @param url 图片url
	 */
	public void setUrl(String url) {
		this.url = url;
	}
	/**
	 * 获取图片url
	 * @return 图片url
	 */
	public String getUrl() {
		return this.url;
	}
	/**
	 * 设置排序
	 * @param sorting 排序
	 */
	public void setSorting(Integer sorting) {
		this.sorting = sorting;
	}
	/**
	 * 获取排序
	 * @return 排序
	 */
	public Integer getSorting() {
		return this.sorting;
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

