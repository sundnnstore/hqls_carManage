package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsStore;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.StoreDto;
import com.sinoauto.dto.StoreInfoDto;
import com.sinoauto.dto.StoreTreeDto;

@Mapper
public interface StoreMapper {

	public List<StoreDto> findStoreInfo(@Param("userId") Integer userId);

	@Update("UPDATE hqls_store SET store_name=#{storeName} where store_id=#{storeId} ")
	public int changeStoreName(@Param("storeName") String storeName, @Param("storeId") Integer storeId);

	@Update("UPDATE hqls_store SET mobile=#{storeMobile} where store_id=#{storeId} ")
	public int changeStoreMobile(@Param("storeMobile") String storeMobile, @Param("storeId") Integer storeId);

	@Update("UPDATE hqls_store SET back_url=#{backUrl} where store_id=#{storeId} ")
	public int changeStoreUrl(@Param("backUrl") String backUrl, @Param("storeId") Integer storeId);

	public Page<StoreInfoDto> findStore(@Param("storeName") String storeName, @Param("userName") String userName,
			@Param("mobile") String mobile,
			@Param("storeLevel") int storeLevel, @Param("storeClass") int storeClass,
			@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId,
			@Param("countyId") Integer countyId);

	@Update("UPDATE hqls_store SET is_useable=" + " CASE is_useable " + " WHEN 0 THEN 1 " + " WHEN 1 THEN 0 " + "END "
			+ " where store_id=#{storeId} ")
	public int changeIsUseable(@Param("storeId") Integer storeId);

	@Update("UPDATE hqls_store SET province_id=#{provinceId},city_id=#{cityId},county_id=#{countyId},address=#{address} where store_id=#{storeId}")
	public int changeStoreAddress(@Param("provinceId") Integer provinceId, @Param("cityId") Integer cityId,
			@Param("countyId") Integer countyId, @Param("address") String address, @Param("storeId") Integer storeId);

	@Select("select  store_id as storeId,store_name as name,pid from hqls_store where   store_id=#{storeId}")
	public StoreTreeDto getStoreByStoreId(@Param("storeId") Integer storeId);

	@Select("select  store_id as storeId,store_name as name,pid from hqls_store where   pid=#{storeId}")
	public List<StoreTreeDto> findSecondStore(@Param("storeId") Integer storeId);

	public int insert(HqlsStore store);

	@Select("select store_id as `key`,store_name as `value` from hqls_store")
	public List<CommonDto> findAllStore();

	@Select("select us.user_name as userName,sto.store_name as storeName,sto.address,sto.mobile as stoMobile,us.mobile as userMobile,sto.back_url as backUrl,sto.store_id as storeId,"
			+ " sto.is_useable as isUseable,us.user_id as userId,hsf.balance,"
			+ " sto.longitude,sto.latitude,sto.province_id as provinceId,sto.province_name as provinceName,sto.city_id as cityId,sto.city_name as cityName,"
			+ " sto.county_id as countyId,sto.county_name as countyName , CASE sto.store_level when 1 then 'A' when 2 then 'B' when 3 then 'C' when 4 then 'D' ELSE '' END as storeLevel,CASE sto.store_class when 1 then '加盟' when 2 then '直营' when 3 then '控股' when 4 then '合作'  ELSE '' END as storeClass "
			+ " from hqls_store sto left join hqls_user_store hqus on sto.store_id = hqus.store_id left join hqls_user us on hqus.user_id=us.user_id"
			+ " left join hqls_store_finance hsf on sto.store_id = hsf.store_id "
			+ " where sto.store_id = #{storeId} and hqus.is_contact=1")
	public StoreDto getStoreInfoByStoreId(@Param("storeId") Integer storeId);

	public int updateStoreByStoreId(@Param("storeId") Integer storeId, @Param("storeName") String storeName,
			@Param("backUrl") String backUrl, @Param("address") String address, @Param("provinceId") Integer provinceId,
			@Param("cityId") Integer cityId, @Param("countyId") Integer countyId);

	public int updateStoreByStoreId(@Param("storeId") Integer storeId, @Param("storeName") String storeName,
			@Param("backUrl") String backUrl, @Param("mobile") String mobile, @Param("address") String address);

	@Select("select * from hqls_store where store_code=#{1}")
	public HqlsStore getStoreByStoreCode(String storeCode);

	public int updateStoreInfo(StoreInfoDto storeInfoDto);
	
	@Select("select * from hqls_store")
	public List<HqlsStore> findAllStoreList();
	
	public Page<StoreInfoDto> findStoreByReviewStatus();


	@Select("select store_id as `key`, store_name as `value` from hqls_store where pid = #{storeId}")
	public List<CommonDto> findChildStore(@Param("storeId") Integer storeId);

	@Select("select us.user_name as userName,sto.store_name as storeName,sto.address,sto.mobile as stoMobile,us.mobile as userMobile,sto.back_url as backUrl,sto.store_id as storeId,sto.is_useable as isUseable,us.user_id as userId,"
			+ " sto.longitude,sto.latitude,sto.province_id as provinceId,sto.province_name as provinceName,sto.city_id as cityId,sto.city_name as cityName,sto.county_id as countyId,sto.county_name as countyName  from hqls_store sto left join hqls_user_store hqus on sto.store_id = hqus.store_id left join hqls_user us on hqus.user_id=us.user_id"
			+ " where sto.store_id = #{storeId}")
	public List<StoreInfoDto> findStoreById(@Param("storeId") Integer storeId);
	
	@Select("select pid from hqls_store where countyId = #{countyId}")
	public List<Integer> getPidByCountyId(@Param("countyId") Integer countyId);
}