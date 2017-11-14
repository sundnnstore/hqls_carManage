package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.sinoauto.dto.c.ServiceOrderListDto;

@Mapper
public interface CServiceOrderMapper {

    /**
     * 根据订单状态查询订单
     * @param status
     * @author Wuxiao
     */
    public List<ServiceOrderListDto> findOrderBystatus(@Param("status") Integer status);

}