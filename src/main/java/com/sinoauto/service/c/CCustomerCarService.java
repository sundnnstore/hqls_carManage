package com.sinoauto.service.c;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.bean.c.CCustomerCar;
import com.sinoauto.dao.mapper.CCustomerCarMapper;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class CCustomerCarService {


    @Autowired
    private CCustomerCarMapper customerMapper;

    /**
     * 我的爱车：查询所有车辆
     * @param customerId
     * @author Wuxiao
     */
    public ResponseEntity<RestModel<List<CCustomerCar>>> findAllCar(Integer customerId) {
        try {
            return RestModel.success(customerMapper.findAllCars(customerId));
        } catch (Exception e) {
            e.printStackTrace();
            return RestModel.error(HttpStatus.OK, ErrorStatus.SYSTEM_EXCEPTION, "查询我的爱车异常！");
        }
    }

}