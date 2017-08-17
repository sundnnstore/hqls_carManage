package com.sinoauto.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.entity.RestModel;


@Service
public class PartsService {

	@Autowired
	private PartsMapper partsMapper;
	
	public ResponseEntity<RestModel<List<CommonDto>>> findListByType(Integer partsType) {
		return RestModel.success(partsMapper.findPartsTypeListByType(partsType));
	}
	
	public ResponseEntity<RestModel<Object>> findListByPid(Integer partsTypeId) {
		//查询此类别下的子类数量
		int count = partsMapper.getPartsCountByPid(partsTypeId);
		Object objList;
		//此类别下还有子类
		if (count > 0) {
			objList = partsMapper.findPartsTypeListByType(partsTypeId);
		}
		//此类别下没有子类，展示商品列表
		else {
			objList = partsMapper.findPartsListByTypeId(partsTypeId);
		}
		
		return RestModel.success(objList);
	}
	
	public ResponseEntity<RestModel<PartsDetailDto>> getPartsDetail(Integer partsId) {
		
		return RestModel.success(partsMapper.getPartsDetail(partsId));
	}
	
}