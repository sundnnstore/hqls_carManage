package com.sinoauto.controller.c;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sinoauto.dao.bean.c.CCustomerCar;
import com.sinoauto.entity.RestModel;
import com.sinoauto.service.c.CCustomerCarService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(tags = "用户车辆操作")
public class CustomerCarController {

    @Autowired
    private CCustomerCarService customerCarService;

    @ApiOperation(value="我的爱车信息", notes="wuxiao")
    @GetMapping("customerCars")
    public ResponseEntity<RestModel<List<CCustomerCar>>> customerCars(@RequestParam(value="customerId") Integer customerId) {
        return customerCarService.findAllCar(customerId);
    }

    
}