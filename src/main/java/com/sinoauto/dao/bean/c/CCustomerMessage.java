package com.sinoauto.dao.bean.c;

import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModelProperty;



/**
 * 客户消息表客户消息表<br>
 * @author tangwt
 * @version 1.0, 2017-11-09
 */

public class CCustomerMessage implements Serializable {
	
    private static final long serialVersionUID = 1L;

	@ApiModelProperty("消息ID")
	private Integer customerMessageId;
	@ApiModelProperty("客户ID")
	private Integer customerId;
	@ApiModelProperty("消息分类（1、系统消息2、订单3、充值4、其他）")
	private Integer messageType;
	@ApiModelProperty("消息内容")
	private String messageContent;
	@ApiModelProperty("是否删除")
	private Boolean isDelete;
	@ApiModelProperty("是否已读")
	private Boolean isRead;
	@ApiModelProperty("推送时间")
	private Date pushTime;
	@ApiModelProperty("创建时间")
	private Date createTime;
	
	/**
	 * 设置消息ID
	 * @param customerMessageId 消息ID
	 */
	public void setCustomerMessageId(Integer customerMessageId) {
		this.customerMessageId = customerMessageId;
	}
	/**
	 * 获取消息ID
	 * @return 消息ID
	 */
	public Integer getCustomerMessageId() {
		return this.customerMessageId;
	}
	/**
	 * 设置客户ID
	 * @param customerId 客户ID
	 */
	public void setCustomerId(Integer customerId) {
		this.customerId = customerId;
	}
	/**
	 * 获取客户ID
	 * @return 客户ID
	 */
	public Integer getCustomerId() {
		return this.customerId;
	}
	/**
	 * 设置消息分类（1、系统消息2、订单3、充值4、其他）
	 * @param messageType 消息分类（1、系统消息2、订单3、充值4、其他）
	 */
	public void setMessageType(Integer messageType) {
		this.messageType = messageType;
	}
	/**
	 * 获取消息分类（1、系统消息2、订单3、充值4、其他）
	 * @return 消息分类（1、系统消息2、订单3、充值4、其他）
	 */
	public Integer getMessageType() {
		return this.messageType;
	}
	/**
	 * 设置消息内容
	 * @param messageContent 消息内容
	 */
	public void setMessageContent(String messageContent) {
		this.messageContent = messageContent;
	}
	/**
	 * 获取消息内容
	 * @return 消息内容
	 */
	public String getMessageContent() {
		return this.messageContent;
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
	 * 设置是否已读
	 * @param isRead 是否已读
	 */
	public void setIsRead(Boolean isRead) {
		this.isRead = isRead;
	}
	/**
	 * 获取是否已读
	 * @return 是否已读
	 */
	public Boolean getIsRead() {
		return this.isRead;
	}
	/**
	 * 设置推送时间
	 * @param pushTime 推送时间
	 */
	public void setPushTime(Date pushTime) {
		this.pushTime = pushTime;
	}
	/**
	 * 获取推送时间
	 * @return 推送时间
	 */
	public Date getPushTime() {
		return this.pushTime;
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

