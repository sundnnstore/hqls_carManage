package com.sinoauto.service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.sinoauto.dao.mapper.PurchaseOrderMapper;
import com.sinoauto.dto.PurchaseOrderDto;
import com.sinoauto.entity.RestModel;


@Service
public class PurchaseOrderService {

	@Autowired
	PurchaseOrderMapper purchaseOrderMapper; 
	
	public ResponseEntity<RestModel<Page<PurchaseOrderDto>>> findPurchaseOrderByContion(){
		return null;
	}
}