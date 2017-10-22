package com.sinoauto.dto;

import java.util.List;

/**
 * 配件车型
 * @author 刘东
 *
 */
public class PartsModelDto {
	
	private Integer partsId;
	private Integer carBrandId;
	private String carBrandName;
	private Integer carSeriesId;
	private String carSeriesName;
	/**
	 * 配件车型
	 */
	private List<CommonDto> carModels;
	public Integer getCarBrandId() {
		return carBrandId;
	}
	public void setCarBrandId(Integer carBrandId) {
		this.carBrandId = carBrandId;
	}
	public String getCarBrandName() {
		return carBrandName;
	}
	public void setCarBrandName(String carBrandName) {
		this.carBrandName = carBrandName;
	}
	public Integer getCarSeriesId() {
		return carSeriesId;
	}
	public void setCarSeriesId(Integer carSeriesId) {
		this.carSeriesId = carSeriesId;
	}
	
	public List<CommonDto> getCarModels() {
		return carModels;
	}
	public void setCarModels(List<CommonDto> carModels) {
		this.carModels = carModels;
	}
	public Integer getPartsId() {
		return partsId;
	}
	public void setPartsId(Integer partsId) {
		this.partsId = partsId;
	}
	public String getCarSeriesName() {
		return carSeriesName;
	}
	public void setCarSeriesName(String carSeriesName) {
		this.carSeriesName = carSeriesName;
	}
	
}
