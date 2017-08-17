package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 字典表字典表<br>
 * @author tangwt
 * @version 1.0, 2017-08-16
 */

public class HqlsDict implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("字典ID")
	private Integer dictId;
	@ApiModelProperty("基础数据key")
	private String dictKey;
	@ApiModelProperty("基础数据value")
	private String dictValue;
	@ApiModelProperty("描述")
	private String description;
	@ApiModelProperty("创建时间")
	private Date createTime;
	@ApiModelProperty("操作时间")
	private Date dmlTime;
	
	/**
	 * 设置字典ID
	 * @param dictId 字典ID
	 */
	public void setDictId(Integer dictId) {
		this.dictId = dictId;
	}
	/**
	 * 获取字典ID
	 * @return 字典ID
	 */
	public Integer getDictId() {
		return this.dictId;
	}
	/**
	 * 设置基础数据key
	 * @param dictKey 基础数据key
	 */
	public void setDictKey(String dictKey) {
		this.dictKey = dictKey;
	}
	/**
	 * 获取基础数据key
	 * @return 基础数据key
	 */
	public String getDictKey() {
		return this.dictKey;
	}
	/**
	 * 设置基础数据value
	 * @param dictValue 基础数据value
	 */
	public void setDictValue(String dictValue) {
		this.dictValue = dictValue;
	}
	/**
	 * 获取基础数据value
	 * @return 基础数据value
	 */
	public String getDictValue() {
		return this.dictValue;
	}
	/**
	 * 设置描述
	 * @param description 描述
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * 获取描述
	 * @return 描述
	 */
	public String getDescription() {
		return this.description;
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

}

