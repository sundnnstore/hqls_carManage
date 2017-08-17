package com.sinoauto.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.entity.RestModel;


@Service
public class PartsService {
	
	@Autowired
	private PartsMapper partsMapper;
	
	/**
	 * 
	 * 按条件查询配件分类
	 * @User liud
	 * @Date 2017年8月17日上午11:28:59
	 * @param partsDto
	 * @return
	 */
	public ResponseEntity<RestModel<List<PartsDto>>> findPartsByCondition(PartsDto partsDto){
		List<PartsDto> partsDtos = null;//返回对象
		
		return RestModel.success(partsDtos);
	}
	
	/**
	 * 新增
	 * @User liud
	 * @Date 2017年8月17日下午12:44:33
	 * @return
	 */
	public Integer addParts(PartsOperDto partsOperDto){
		
		return 1;
	}
	
	/**
	 * 修改
	 * @User liud
	 * @Date 2017年8月17日下午12:44:44
	 * @return
	 * 1. 根据 通用件 易损件 的ID查询 他们所属的子集
	 * 2. 这些子集就是配件的父级
	 * 3. 包含上下架的功能
	 * 4. 可以修改 配件的参数信息
	 *
	 */
	public Integer updateParts(PartsOperDto partsOperDto){
		return 1;
	}
	
}