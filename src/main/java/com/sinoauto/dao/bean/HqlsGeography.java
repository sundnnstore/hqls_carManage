package com.sinoauto.dao.bean;

import java.io.Serializable;

public class HqlsGeography implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String customerName;
	private String mobile;
	private String carNo;
	private String lngG;
	private String latG;
	private String lngB;
	private String latB;
	private String address;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		if (id != null) {
			this.id = id;
		}
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		if (customerName != null) {
			this.customerName = customerName;
		}
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		if (mobile != null) {
			this.mobile = mobile;
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

	public String getLngG() {
		return lngG;
	}

	public void setLngG(String lngG) {
		if (lngG != null) {
			this.lngG = lngG;
		}
	}

	public String getLatG() {
		return latG;
	}

	public void setLatG(String latG) {
		if (latG != null) {
			this.latG = latG;
		}
	}

	public String getLngB() {
		return lngB;
	}

	public void setLngB(String lngB) {
		if (lngB != null) {
			this.lngB = lngB;
		}
	}

	public String getLatB() {
		return latB;
	}

	public void setLatB(String latB) {
		if (latB != null) {
			this.latB = latB;
		}
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		if (address != null) {
			this.address = address;
		}
	}

}
