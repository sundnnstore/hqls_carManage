package com.sinoauto.service;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.bean.HqlsPartsBrand;
import com.sinoauto.dao.mapper.PartsBrandMapper;
import com.sinoauto.entity.RestModel;


@Service
public class PartsBrandService {

	@Autowired
	private PartsBrandMapper partsBrandMapper;
	/**
	 * 	查询所有品牌ID
	 * 	@User liud
	 * 	@Date 2017年8月21日下午1:08:40
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<HqlsPartsBrand>>> findPartsBrands(){
		return RestModel.success(partsBrandMapper.findPartsBrands());
	}
}