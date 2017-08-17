package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsShipAddress;

@Mapper
public interface ShipAddressMapper {

	public int insert(HqlsShipAddress address);
	
	public int update(HqlsShipAddress address);
	
	public int delete(@Param("addressId") Integer addressId);
	
	public int batchDelete(@Param("addressIds") Integer[] addressIds);
	
	@Select("select * from hqls_ship_address")
	public Page<HqlsShipAddress> findAll();
	
	@Select("select * from hqls_ship_address where ship_address_id = #{1}")
	public HqlsShipAddress getShipAddressById(Integer shipAddressId);
	
}