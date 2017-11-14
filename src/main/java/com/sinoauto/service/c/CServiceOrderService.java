package com.sinoauto.service.c;

import com.sinoauto.dto.c.ServiceOrderListDto;
import com.sinoauto.entity.RestModel;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class CServiceOrderService {



    /**
     * 按订单状态查询订单（0，全部；1，待支付；2，待服务；9，待完成（增项待支付））
     * @param status
     * @param pageSize
     * @param pageNum
     * @author Wuxiao
     */
    public ResponseEntity<RestModel<List<ServiceOrderListDto>>> findOrderByStatus(Integer status, Integer pageSize, Integer pageNum) {
        

        return null;
    }

}