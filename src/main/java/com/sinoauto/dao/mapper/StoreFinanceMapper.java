package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsStoreFinance;
import com.sinoauto.dto.StoreFinanceDto;

@Mapper
public interface StoreFinanceMapper {

	@Update("update hqls_store_finance set balance=balance+#{money},cash_able=cash_able+#{money} where store_id = #{storeId} ")
	public int addCash(@Param("storeId") Integer storeId, @Param("money") Double money);

	@Select("select count(*) from hqls_store_finance where store_id = #{storeId}")
	public int getStoreFinanceByStoreId(@Param("storeId") Integer storeId);

	public int insert(HqlsStoreFinance finance);

	@Select("select * from hqls_store_finance where store_id = #{storeId}")
	public HqlsStoreFinance getStoreFinance(Integer storeId);

	public int updateBalance(HqlsStoreFinance finance);

	public Page<StoreFinanceDto> findStoreFinanceistByContidion(@Param("storeId") Integer storeId, @Param("customerName") String customerName,
			@Param("mobile") String mobile);

	public HqlsStoreFinance findStoreFinance(@Param("storeId") Integer storeId);

	@Update("UPDATE hqls_store_finance SET balance = #{balance}+balance, cash_able = #{cashAble}+cash_able,cash_disable = #{cashDisable}+cash_disable, dml_time = now() WHERE store_id = #{storeId}")
	public int updateMoney(@Param("balance") Double balance, @Param("cashAble") Double cashAble, @Param("cashDisable") Double cash_disable,
			@Param("storeId") Integer storeId);
	
	@Update("update hqls_store_finance set balance=balance+#{money}, dml_time = now() where store_id = #{storeId}")
	public int updateStoreBalance(@Param("storeId") Integer storeId, @Param("money") Double money);

}