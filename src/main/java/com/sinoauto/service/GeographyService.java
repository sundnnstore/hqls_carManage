package com.sinoauto.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.bean.HqlsGeography;
import com.sinoauto.dao.mapper.DistrictMapper;
import com.sinoauto.dao.mapper.GeographyMapper;
import com.sinoauto.dto.CountyDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;
import com.sinoauto.util.OperDataUtil;

@Service
public class GeographyService {

	@Autowired
	private GeographyMapper geographyMapper;

	@Autowired
	private DistrictMapper districtMapper;

	public ResponseEntity<RestModel<List<HqlsGeography>>> findCustomers(String lat, String lng,Integer distance) {
		return RestModel.success(geographyMapper.findCustomersByLatAndLng(Double.parseDouble(lat), Double.parseDouble(lng),distance));
	}

	public ResponseEntity<RestModel<List<CountyDto>>> findCountys(String cityName, Integer isCounty) {
		if (isCounty == 1) {
			List<String> countys = districtMapper.findAllCountyByCityName(cityName);
			if (countys != null && countys.size() > 0) {
				List<CountyDto> countyDtos = new ArrayList<>();
				for (String county : countys) {
					CountyDto cd = new CountyDto();
					String lat = county.split(",")[1];
					String lng = county.split(",")[2];
					String countyCode = county.split(",")[3];
					Integer count = geographyMapper.getCustomersCountByCountyCode(countyCode);
					cd.setCountInfo(county.split(",")[0].split(cityName)[1] + ":" + count + " 人");
					cd.setLat(lat);
					cd.setLng(lng);
					countyDtos.add(cd);
				}
				return RestModel.success(countyDtos);
			} else {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.DATA_NOT_EXIST);
			}
		} else {
			List<CountyDto> countyDtos = new ArrayList<>();
			CountyDto cd = new CountyDto();
			String cityInfo = geographyMapper.getCityInfoByCityName(cityName);
			String cityCode = cityInfo.split(",")[0];
			String address = cityInfo.split(",")[1].split(cityName)[0] + cityName;
			String[] location = OperDataUtil.getPosition(address);
			String lat = location[0];
			String lng = location[1];
			Integer count = geographyMapper.getCustomersCountByCityCode(cityCode);
			cd.setCountInfo(cityName + ":" + count + " 人");
			cd.setLat(lat);
			cd.setLng(lng);
			countyDtos.add(cd);
			return RestModel.success(countyDtos);
		}

	}

}
