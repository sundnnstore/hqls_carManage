package com.sinoauto.service;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.mapper.PartsTypeMapper;
import com.sinoauto.dto.PartsTypeDto;


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
}
	