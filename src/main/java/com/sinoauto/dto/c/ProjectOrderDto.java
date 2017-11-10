package com.sinoauto.dto.c;

import java.util.List;

import io.swagger.annotations.ApiModelProperty;

/**
 * 服务项目下单展示dto
 * @author tangwt
 * @version 1.0
 * @date 2017-11-10 10:03:22
 */
public class ProjectOrderDto {

	@ApiModelProperty("服务项目ID")
	private Integer serviceProjectId;
	@ApiModelProperty("服务项目名称")
	private String serviceProjectName;
	@ApiModelProperty("服务项目LOGO")
	private String projectLogoUrl;
	@ApiModelProperty("客户汽车ID")
	private String customerCarId;
	@ApiModelProperty("车辆信息")
	private String carNo;
	@ApiModelProperty("推荐门店ID")
	private Integer storeId;
	@ApiModelProperty("推荐门店信息")
	private String storeName;
	@ApiModelProperty("服务项目金额")
	private Double amount;
	@ApiModelProperty("子服务项目集合")
	private List<LastProjectDto> sonProjects;

	public Integer getServiceProjectId() {
		return serviceProjectId;
	}

	public void setServiceProjectId(Integer serviceProjectId) {
		if (serviceProjectId != null) {
			this.serviceProjectId = serviceProjectId;
		}
	}

	public String getServiceProjectName() {
		return serviceProjectName;
	}

	public void setServiceProjectName(String serviceProjectName) {
		if (serviceProjectName != null) {
			this.serviceProjectName = serviceProjectName;
		}
	}

	public String getProjectLogoUrl() {
		return projectLogoUrl;
	}

	public void setProjectLogoUrl(String projectLogoUrl) {
		if (projectLogoUrl != null) {
			this.projectLogoUrl = projectLogoUrl;
		}
	}

	public String getCustomerCarId() {
		return customerCarId;
	}

	public void setCustomerCarId(String customerCarId) {
		if (customerCarId != null) {
			this.customerCarId = customerCarId;
		}
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		if (carNo != null) {
			this.carNo = carNo;
		}
	}

	public Integer getStoreId() {
		return storeId;
	}

	public void setStoreId(Integer storeId) {
		if (storeId != null) {
			this.storeId = storeId;
		}
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		if (storeName != null) {
			this.storeName = storeName;
		}
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		if (amount != null) {
			this.amount = amount;
		}
	}

	public List<LastProjectDto> getSonProjects() {
		return sonProjects;
	}

	public void setSonProjects(List<LastProjectDto> sonProjects) {
		if (sonProjects != null) {
			this.sonProjects = sonProjects;
		}
	}

}
