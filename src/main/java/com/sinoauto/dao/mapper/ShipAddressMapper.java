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
	
	@Select("select * from hqls_ship_address where store_id = #{1} and is_default = 1")
	public HqlsShipAddress getDefaultAddressByStoreId(Integer storeId);
	
	@Select("select * from hqls_ship_address where store_id = #{1} order by create_time desc limit 0,1")
	public HqlsShipAddress getAddressByStoreId(Integer storeId);
	
}