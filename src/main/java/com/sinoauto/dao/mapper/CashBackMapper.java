package com.sinoauto.dao.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsCashBack;
import com.sinoauto.dto.CashBackDto;

@Mapper
public interface CashBackMapper {

	public int insert(HqlsCashBack cashBack);

	public Page<CashBackDto> findCashBackList(@Param("returnType") Integer returnType, @Param("operateUserName") String operateUserName,
			@Param("createTime") String createTime);

}