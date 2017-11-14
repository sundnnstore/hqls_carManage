package com.sinoauto.dto.c;

import io.swagger.annotations.ApiModelProperty;

public class ServiceOrderListDto {

    @ApiModelProperty("订单ID")
    private Integer orderId;
    @ApiModelProperty("订单号")
    private String orderNo;
    @ApiModelProperty("门店缩略图")
    private String storePicUrl;
    @ApiModelProperty("门店名称")
    private String storeName;
    @ApiModelProperty("服务项目名称")
    private String projectName;
    @ApiModelProperty("订单金额")
    private Double orderAmount;
    @ApiModelProperty("订单状态")
    private String orderStatusDesc;

    /**
     * @return the orderAmount
     */
    public Double getOrderAmount() {
        return orderAmount;
    }

    /**
     * @param orderAmount the orderAmount to set
     */
    public void setOrderAmount(Double orderAmount) {
        this.orderAmount = orderAmount;
    }

    /**
     * @return the orderId
     */
    public Integer getOrderId() {
        return orderId;
    }

    /**
     * @param orderId the orderId to set
     */
    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    /**
     * @return the orderNo
     */
    public String getOrderNo() {
        return orderNo;
    }

    /**
     * @param orderNo the orderNo to set
     */
    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    /**
     * @return the orderStatusDesc
     */
    public String getOrderStatusDesc() {
        return orderStatusDesc;
    }

    /**
     * @param orderStatusDesc the orderStatusDesc to set
     */
    public void setOrderStatusDesc(String orderStatusDesc) {
        this.orderStatusDesc = orderStatusDesc;
    }

    /**
     * @return the projectName
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * @param projectName the projectName to set
     */
    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    /**
     * @return the storeName
     */
    public String getStoreName() {
        return storeName;
    }

    /**
     * @param storeName the storeName to set
     */
    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    /**
     * @return the storePicUrl
     */
    public String getStorePicUrl() {
        return storePicUrl;
    }

    /**
     * @param storePicUrl the storePicUrl to set
     */
    public void setStorePicUrl(String storePicUrl) {
        this.storePicUrl = storePicUrl;
    }
}