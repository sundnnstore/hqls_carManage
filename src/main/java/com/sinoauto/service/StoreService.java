package com.sinoauto.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.sinoauto.dao.mapper.StoreMapper;
import com.sinoauto.dto.StoreDto;
import com.sinoauto.entity.RestModel;


@Service
public class StoreService {
	
	@Autowired
	private StoreMapper storeMapper;
	
	
	/**
	 * 根据当前登陆人查询门店信息
	 * @param mobile
	 * @param password
	 * @return
	 */
	public ResponseEntity<RestModel<List<StoreDto>>> getStoreInfo(String token) {
		// 获取当前用户
       /* RestModel<TokenModel> rest = authService.validToken(token);
        if (rest.getErrcode() != 0) {// 解析token失败
            return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
        }
        List<StoreDto> stUsers = new ArrayList<>();
        Integer userId = rest.getResult().getUserId();// 当前登录人的userid
*/		
        Integer userId =null;
		List<StoreDto> storeList = storeMapper.findStoreInfo(userId);
		return  RestModel.success(storeList);
		
	}
	
	

}