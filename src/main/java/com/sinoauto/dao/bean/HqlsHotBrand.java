package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * <br>
 * @author tangwt
 * @version 1.0, 2017-11-02
 */

public class HqlsHotBrand implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("热门品牌id")
	private Integer id;
	@ApiModelProperty("品牌ID")
	private Integer brandId;
	@ApiModelProperty("排序")
	private Integer brandSort;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置热门品牌id
	 * @param id 热门品牌id
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取热门品牌id
	 * @return 热门品牌id
	 */
	public Integer getId() {
		return this.id;
	}
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
	 * 设置排序
	 * @param brandSort 排序
	 */
	public void setBrandSort(Integer brandSort) {
		this.brandSort = brandSort;
	}
	/**
	 * 获取排序
	 * @return 排序
	 */
	public Integer getBrandSort() {
		return this.brandSort;
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

