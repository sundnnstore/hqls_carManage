package com.sinoauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.bean.HqlsGeography;
import com.sinoauto.dao.mapper.GeographyMapper;
import com.sinoauto.entity.RestModel;

@Service
public class GeographyService {
	
	@Autowired
	private GeographyMapper geographyMapper;
	
	public ResponseEntity<RestModel<List<HqlsGeography>>> findCustomers(String lat,String lng) {
		return RestModel.success(geographyMapper.findCustomersByLatAndLng(Double.parseDouble(lat), Double.parseDouble(lng)));
	} 

}
