package com.sinoauto.dao.bean;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;

/**
 * 用户加盟表
 * @author yq
 *
 */
public class HqlsUserJoin implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@ApiModelProperty(value="用户加盟id")
	private Integer userJoinId;
	@ApiModelProperty(value="门店名称")
	private String storeName;
	@ApiModelProperty(value="门店地址")
	private String address;
	@ApiModelProperty(value="联系人名称")
	private String contactName;
	@ApiModelProperty(value="联系人电话")
	private String contactMobile;
	@ApiModelProperty(value="加盟原因")
	private String remark;
	@ApiModelProperty(value="创建时间")
	private Date createTime;
	@ApiModelProperty(value="操作时间")
	private Date dmlTime;
	public Integer getUserJoinId() {
		return userJoinId;
	}
	public void setUserJoinId(Integer userJoinId) {
		this.userJoinId = userJoinId;
	}
	public String getStoreName() {
		return storeName;
	}
	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	public String getContactMobile() {
		return contactMobile;
	}
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Date getCreateTime() {
		return createTime;
	}
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
	public Date getDmlTime() {
		return dmlTime;
	}
	public void setDmlTime(Date dmlTime) {
		this.dmlTime = dmlTime;
	}
	
	

}
