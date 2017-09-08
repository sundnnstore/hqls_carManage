package com.sinoauto.dto;

public class CountyDto {

	private String lat;
	private String lng;
	private String countInfo;

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		if (lat != null) {
			this.lat = lat;
		}
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		if (lng != null) {
			this.lng = lng;
		}
	}

	public String getCountInfo() {
		return countInfo;
	}

	public void setCountInfo(String countInfo) {
		if (countInfo != null) {
			this.countInfo = countInfo;
		}
	}

}
