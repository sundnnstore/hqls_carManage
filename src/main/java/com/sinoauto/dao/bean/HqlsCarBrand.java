package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 品牌基础表品牌基础表<br>
 * @author tangwt
 * @version 1.0, 2017-10-16
 */

public class HqlsCarBrand implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("品牌ID")
	private Integer brandId;
	@ApiModelProperty("品牌编码")
	private String brandCode;
	@ApiModelProperty("品牌名称")
	private String brandName;
	@ApiModelProperty("品牌英文名称")
	private String brandEname;
	@ApiModelProperty("品牌名称首字母")
	private String firstChar;
	@ApiModelProperty("品牌LogoUrl")
	private String logoUrl;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("记录创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	@ApiModelProperty("操作标识（新增-1，修改-2，删除-3）")
	private Boolean dmlFlag;
	
	/**
	 * 设置品牌ID
	 * @param brandId 品牌ID
	 */
	public void setBrandId(Integer brandId) {
		this.brandId = brandId;
	}
	/**
	 * 获取品牌ID
	 * @return 品牌ID
	 */
	public Integer getBrandId() {
		return this.brandId;
	}
	/**
	 * 设置品牌编码
	 * @param brandCode 品牌编码
	 */
	public void setBrandCode(String brandCode) {
		this.brandCode = brandCode;
	}
	/**
	 * 获取品牌编码
	 * @return 品牌编码
	 */
	public String getBrandCode() {
		return this.brandCode;
	}
	/**
	 * 设置品牌名称
	 * @param brandName 品牌名称
	 */
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	/**
	 * 获取品牌名称
	 * @return 品牌名称
	 */
	public String getBrandName() {
		return this.brandName;
	}
	/**
	 * 设置品牌英文名称
	 * @param brandEname 品牌英文名称
	 */
	public void setBrandEname(String brandEname) {
		this.brandEname = brandEname;
	}
	/**
	 * 获取品牌英文名称
	 * @return 品牌英文名称
	 */
	public String getBrandEname() {
		return this.brandEname;
	}
	/**
	 * 设置品牌名称首字母
	 * @param firstChar 品牌名称首字母
	 */
	public void setFirstChar(String firstChar) {
		this.firstChar = firstChar;
	}
	/**
	 * 获取品牌名称首字母
	 * @return 品牌名称首字母
	 */
	public String getFirstChar() {
		return this.firstChar;
	}
	/**
	 * 设置品牌LogoUrl
	 * @param logoUrl 品牌LogoUrl
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取品牌LogoUrl
	 * @return 品牌LogoUrl
	 */
	public String getLogoUrl() {
		return this.logoUrl;
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

