package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.sinoauto.dao.bean.HqlsPurchaseOrder;

@Mapper
public interface PurchaseOrderMapper {

	public int insert(HqlsPurchaseOrder order);
	
}