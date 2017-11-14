package com.sinoauto.controller.c;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dto.c.ServiceOrderListDto;
import com.sinoauto.entity.RestModel;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags="服务订单操作")
public class CServiceOrderController {

    @ApiOperation(value="按订单状态查询（0，全部；1，待支付；2，待服务；9，待完成（增项待支付））")
    @GetMapping("findorderbystatus")
    public ResponseEntity<RestModel<List<ServiceOrderListDto>>> findOrderByStatus(
                                @RequestParam(value="status", required=true) Integer status,
                                @RequestParam(value="pageNum", required=false) Integer pageNum,
                                @RequestParam(value="pageSize", required=true) Integer pageSize) {
        
        return null;
    }

}