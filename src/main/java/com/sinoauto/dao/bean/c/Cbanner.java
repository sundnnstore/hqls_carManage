package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 滚动栏表滚动栏表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class Cbanner implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("滚动栏ID")
	private Integer bannerId;
	@ApiModelProperty("滚动栏名称")
	private String bannerName;
	@ApiModelProperty("描述")
	private String bannerDesc;
	@ApiModelProperty("显示的logoURL")
	private String logoUrl;
	@ApiModelProperty("跳转的URL")
	private String jumpUrl;
	@ApiModelProperty("banner排序")
	private Integer bannerSort;
	@ApiModelProperty("是否删除")
	private Boolean isDelete;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置滚动栏ID
	 * @param bannerId 滚动栏ID
	 */
	public void setBannerId(Integer bannerId) {
		this.bannerId = bannerId;
	}
	/**
	 * 获取滚动栏ID
	 * @return 滚动栏ID
	 */
	public Integer getBannerId() {
		return this.bannerId;
	}
	/**
	 * 设置滚动栏名称
	 * @param bannerName 滚动栏名称
	 */
	public void setBannerName(String bannerName) {
		this.bannerName = bannerName;
	}
	/**
	 * 获取滚动栏名称
	 * @return 滚动栏名称
	 */
	public String getBannerName() {
		return this.bannerName;
	}
	/**
	 * 设置描述
	 * @param bannerDesc 描述
	 */
	public void setBannerDesc(String bannerDesc) {
		this.bannerDesc = bannerDesc;
	}
	/**
	 * 获取描述
	 * @return 描述
	 */
	public String getBannerDesc() {
		return this.bannerDesc;
	}
	/**
	 * 设置显示的logoURL
	 * @param logoUrl 显示的logoURL
	 */
	public void setLogoUrl(String logoUrl) {
		this.logoUrl = logoUrl;
	}
	/**
	 * 获取显示的logoURL
	 * @return 显示的logoURL
	 */
	public String getLogoUrl() {
		return this.logoUrl;
	}
	/**
	 * 设置跳转的URL
	 * @param jumpUrl 跳转的URL
	 */
	public void setJumpUrl(String jumpUrl) {
		this.jumpUrl = jumpUrl;
	}
	/**
	 * 获取跳转的URL
	 * @return 跳转的URL
	 */
	public String getJumpUrl() {
		return this.jumpUrl;
	}
	/**
	 * 设置banner排序
	 * @param bannerSort banner排序
	 */
	public void setBannerSort(Integer bannerSort) {
		this.bannerSort = bannerSort;
	}
	/**
	 * 获取banner排序
	 * @return banner排序
	 */
	public Integer getBannerSort() {
		return this.bannerSort;
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

