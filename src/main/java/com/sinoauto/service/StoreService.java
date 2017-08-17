package com.sinoauto.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.mapper.StoreMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dto.StoreDto;
import com.sinoauto.dto.StoreInfoDto;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;


@Service
public class StoreService {
	
	@Autowired
	private StoreMapper storeMapper;
	
	@Autowired
	private AuthService authService;
	
	@Autowired
	private UserMapper userMapper;
	
	
	/**
	 * 根据当前登陆人查询门店信息
	 * @param mobile
	 * @param password
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<List<StoreDto>>> getStoreInfo(String token) {
		// 获取当前用户
        RestModel<TokenModel> rest = authService.validToken(token);
        if (rest.getErrcode() != 0) {// 解析token失败
            return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
        }
        Integer userId = rest.getResult().getUserId();// 当前登录人的userid
        //获取userId
        HqlsUser user = userMapper.getUserByGloabUserId(userId);
		List<StoreDto> storeList = storeMapper.findStoreInfo(user.getUserId());
		return  RestModel.success(storeList);
		
	}
	
	/**
	 * 修改门店名称
	 * @param storeName
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> changeStoreName(String storeName,Integer storeId){
		
        //修改当前门店名称
        storeMapper.changeStoreName(storeName,storeId);
        
        return RestModel.success();
		
	}
	
	
	/**
	 * 修改门店联系方式
	 * @param storeMobile
	 * @param storeId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> changeStoreMobile(String storeMobile,Integer storeId){
		
        //修改门店联系方式
        storeMapper.changeStoreMobile(storeMobile,storeId);
        
        return RestModel.success();
		
	}
	
	
	/**
	 * 修改门店背景图片
	 * @param storeMobile
	 * @param storeId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> changeStoreUrl(String backUrl,Integer storeId){
		
        //修改门店背景图片
        storeMapper.changeStoreUrl(backUrl,storeId);
        
        return RestModel.success();
		
	}
	
	/**
	 * 根据门店名称等查询门店信息
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<List<StoreInfoDto>>> findStore(String storeName,String userName,String mobile,String address,Integer pageIndex,Integer pageSize){
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);// 分页使用
		}
		Page<StoreInfoDto> storeList = storeMapper.getStore( storeName, userName, mobile, address);
		return RestModel.success(storeList,(int) storeList.getTotal());
		
	}
	
	/**
	 * 门店禁用与启用
	 * @param isUseable
	 * @param storeId
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> changeIsUseable(Integer storeId){
		
        storeMapper.changeIsUseable(storeId);
        
        return RestModel.success();
		
	}
	
	
	/**
	 * 修改门店地址
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<String>> changeStoreAddress(Integer provinceId,Integer cityId,Integer countyId,String address,Integer storeId){
		
		storeMapper.changeStoreAddress(address,storeId);
		return RestModel.success();
	}
	
	/**
	 * 新增门店信息
	 * @return
	 */
	/*public ResponseEntity<RestModel<HqlsStore>> insertStore(){
		//查询所有启用的门店信息
		List<HqlsStore> store = storeMapper.findAllStore();
		
		
		//新增门店信息
		return 
	}*/
	
	

}