package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 配件品牌表配件品牌表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsPartsBrand implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("配件品牌ID")
	private Integer partsBrandId;
	@ApiModelProperty("配件品牌名称")
	private String partsBrandName;
	@ApiModelProperty("备注")
	private String remark;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置配件品牌ID
	 * @param partsBrandId 配件品牌ID
	 */
	public void setPartsBrandId(Integer partsBrandId) {
		this.partsBrandId = partsBrandId;
	}
	/**
	 * 获取配件品牌ID
	 * @return 配件品牌ID
	 */
	public Integer getPartsBrandId() {
		return this.partsBrandId;
	}
	/**
	 * 设置配件品牌名称
	 * @param partsBrandName 配件品牌名称
	 */
	public void setPartsBrandName(String partsBrandName) {
		this.partsBrandName = partsBrandName;
	}
	/**
	 * 获取配件品牌名称
	 * @return 配件品牌名称
	 */
	public String getPartsBrandName() {
		return this.partsBrandName;
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

