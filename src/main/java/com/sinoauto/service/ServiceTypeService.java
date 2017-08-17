package com.sinoauto.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsServiceType;
import com.sinoauto.dao.mapper.ServiceTypeMapper;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class ServiceTypeService {

	@Autowired
	private ServiceTypeMapper serviceTypeMapper;

	public ResponseEntity<RestModel<List<HqlsServiceType>>> findServiceTypesByTypeName(String typeName, Integer pageIndex, Integer pageSize) {
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<HqlsServiceType> rs = serviceTypeMapper.findServiceTypesByTypeName(typeName);
		return RestModel.success(rs, (int) rs.getTotal());
	}

	public ResponseEntity<RestModel<String>> addServiceType(HqlsServiceType serviceType) {
		try {
			serviceTypeMapper.insert(serviceType);
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);
		}
		return RestModel.success("添加服务项目成功！");
	}

	public ResponseEntity<RestModel<String>> updateServiceType(HqlsServiceType serviceType) {
		try {
			int result = serviceTypeMapper.update(serviceType);
			if (result == 0) {
				return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "编辑服务项目失败");
			}
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION);
		}
		return RestModel.success("编辑服务项目成功！");
	}

	public ResponseEntity<RestModel<String>> updateServiceTypeStatus(Integer serviceTypeId) {
		try {
			int result = serviceTypeMapper.updateServiceTypeStatus(serviceTypeId);
			if (result == 1) {
				return RestModel.success("修改成功");
			}
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "修改失败");
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.INVALID_DATA.getErrcode(), "修改失败");
		}
	}

}