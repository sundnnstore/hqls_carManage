package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsStore;
import com.sinoauto.dto.StoreDto;
import com.sinoauto.dto.StoreInfoDto;
import com.sinoauto.dto.StoreTreeDto;

@Mapper
public interface StoreMapper {
	
	
	public List<StoreDto> findStoreInfo(@Param("userId") Integer userId);
	
	
	@Update("UPDATE hqls_store SET store_name=#{storeName} where store_id=#{storeId} ")
	public int changeStoreName(@Param("storeName") String storeName,@Param("storeId") Integer storeId);
	
	
	
	@Update("UPDATE hqls_store SET mobile=#{storeMobile} where store_id=#{storeId} ")
	public int changeStoreMobile(@Param("storeMobile") String storeMobile,@Param("storeId") Integer storeId);
	
	
	
	@Update("UPDATE hqls_store SET back_url=#{backUrl} where store_id=#{storeId} ")
	public int changeStoreUrl(@Param("backUrl") String backUrl,@Param("storeId") Integer storeId);
	
	
	public Page<StoreInfoDto> getStore(@Param("storeName") String storeName,@Param("userName") String userName,@Param("mobile") String mobile, @Param("address") String address);
	
	
	
	@Update("UPDATE hqls_store SET is_useable="+
							" CASE is_useable "+ 
							  " WHEN 0 THEN 1 "+
							  " WHEN 1 THEN 0 "+
							  "END "+
				" where store_id=#{storeId} ")
	public int changeIsUseable(@Param("storeId") Integer storeId);
	
	
	public int changeStoreAddress(@Param("address") String address,@Param("storeId") Integer storeId);
	
	
	@Select("select  store_id as storeId,store_name as storeName,pid from hqls_store where is_useable=1 and store_id=#{storeId}")
	public StoreTreeDto findStoreByStoreId(@Param("storeId") Integer storeId);
	
	@Select("select  store_id as storeId,store_name as storeName,pid from hqls_store where is_useable=1 and pid=#{storeId}")
	public List<StoreTreeDto> findSecondStore(@Param("storeId") Integer storeId);
	
	
	public int insert(HqlsStore store);
	
	

}