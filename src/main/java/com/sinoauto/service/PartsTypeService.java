package com.sinoauto.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dao.mapper.PartsTypeMapper;
import com.sinoauto.dto.PartsTypeDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;


@Service
public class PartsTypeService {
	@Autowired
	private PartsTypeMapper partsTypeMapper; 
	/**
	 * 	配件类型
	 * 	@User liud
	 * 	@Date 2017年8月23日下午12:13:21
	 * 	@return
	 */
	public List<PartsTypeDto> partsTypes(){
		List<PartsTypeDto> partsType=partsTypeMapper.partsType();
		if(partsType==null){partsType=new ArrayList<>();};
		return partsType;
	}
	
	/**
	 *  新增节点树
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:03:34
	 * 	@return
	 */
	@Transactional
	public ResponseEntity<RestModel<Integer>> insert(HqlsPartsType hqlsPartsType){
		try {
			HqlsPartsType add=new HqlsPartsType();
			add.setTypeName(hqlsPartsType.getTypeName());
			add.setPid(hqlsPartsType.getPartsTypeId());
			add.setPartsType(1); //通用配件
			//创建一个配件类型
			partsTypeMapper.insert(add);
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"新增节点树失败");
		}
		return RestModel.success();
	}
}
	