package com.sinoauto.service;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsStore;
import com.sinoauto.dao.bean.HqlsUser;
import com.sinoauto.dao.bean.HqlsUserStore;
import com.sinoauto.dao.mapper.StoreMapper;
import com.sinoauto.dao.mapper.UserMapper;
import com.sinoauto.dao.mapper.UserStoreMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.StoreDto;
import com.sinoauto.dto.StoreInfoDto;
import com.sinoauto.dto.StoreTreeDto;
import com.sinoauto.entity.AuthUser;
import com.sinoauto.entity.RestModel;
import com.sinoauto.entity.TokenModel;


@Service
public class StoreService {
	private Logger logger = LoggerFactory.getLogger(UserService.class);
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private AuthService authService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private UserStoreMapper userStoreMapper;
	
	
	/**
	 * 根据当前登陆人查询门店信息
	 * @param mobile
	 * @param password
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<List<StoreDto>>> findStoreInfo(String token) {
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
	public ResponseEntity<RestModel<List<StoreInfoDto>>> findStore(String storeName,String userName,String mobile,
																	String address,Integer provinceId,Integer cityId,Integer countyId,Integer pageIndex,Integer pageSize){
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);// 分页使用
		}
		Page<StoreInfoDto> storeList = storeMapper.findStore( storeName, userName, mobile, address,provinceId,cityId,countyId);
		return RestModel.success(storeList,(int) storeList.getTotal());
		
	}
	
	/**
	 * 门店禁用与启用
	 * @param isUseable
	 * @param storeId
	 * @return
	 */
	public ResponseEntity<RestModel<String>> changeIsUseable(Integer storeId){
        storeMapper.changeIsUseable(storeId);
        return RestModel.success();
		
	}
	
	
	/**
	 * 修改门店地址
	 * @return
	 */
	public ResponseEntity<RestModel<String>> changeStoreAddress(Integer provinceId,Integer cityId,Integer countyId,String address,Integer storeId){
		storeMapper.changeStoreAddress( provinceId,cityId,countyId,address,storeId);
		return RestModel.success();
	}
	
	/**
	 * 查询门店信息
	 * @return
	 */
	public StoreTreeDto findStoreIsUseable(Integer storeId) {
		// 查询一级门店
		StoreTreeDto store = storeMapper.getStoreByStoreId(storeId);
		// 判断一级门店是否存在
		store = store == null ? new StoreTreeDto() : store;
		// 查询二级门店
		List<StoreTreeDto> storeList = storeMapper.findSecondStore(store.getStoreId());
		if (storeList != null && !storeList.isEmpty()) {
			store.setChildren(new ArrayList<>());
			for (StoreTreeDto st : storeList) {
				StoreTreeDto t = findStoreIsUseable(st.getStoreId());// 递归查询
				store.getChildren().add(t);
			}
		}
		return store;
	}
	
	
	/**
	 * 新增门店信息
	 * @param storeInfoDto
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<Integer>> insertStore(String token,StoreInfoDto storeInfoDto){
		
		HqlsUser user = new HqlsUser();
		String mobile = storeInfoDto.getMobile();
		String userName = storeInfoDto.getUserName();
		String password ="123456";
		user.setMobile(mobile);
		user.setUserName(userName);
		user.setPassword(password);
		user.setIsUseable(true);
		user.setCreateTime(new Date());
		user.setDmlTime(new Date());
		HqlsStore store = new HqlsStore();
		store.setAddress(storeInfoDto.getAddress());
		store.setBackUrl(storeInfoDto.getBackUrl());
		store.setCityId(storeInfoDto.getCityId());
		store.setCityName(storeInfoDto.getCityName());
		store.setCountyId(storeInfoDto.getCountyId());
		store.setCountyName(storeInfoDto.getCountyName());
		store.setIsUseable(storeInfoDto.getIsUseable());
		store.setLatitude(storeInfoDto.getLatitude());
		store.setLongitude(storeInfoDto.getLongitude());
		store.setMobile(storeInfoDto.getMobile());
		store.setPid(storeInfoDto.getPid());
		store.setProvinceId(storeInfoDto.getProvinceId());
		store.setProvinceName(storeInfoDto.getProvinceName());
		
		store.setStoreCode("store---");
		store.setStoreName(storeInfoDto.getStoreName());
		
		//新增门店信息
		 storeMapper.insert(store);
		 int stoId = store.getStoreId();
		//注册用户信息
		RestModel<Integer> registerInfo = authService.register(mobile,password);
		//查询当前用户在系统中是否存在
		HqlsUser hquser =	userMapper.getUserInfoByMobile(mobile);
		Integer globalUserId = null;
		Integer userId = null;
		if (registerInfo.getErrcode() == 0) {//注册成功
			logger.info("注册，新增用户。");
			globalUserId = registerInfo.getResult(); //用户中心的编号
			user.setGlobalUserId(globalUserId);
			if(hquser != null){
				userId = hquser.getUserId();
			}else{
				userMapper.insert(user);
				userId = user.getUserId();
			}
			//新增用户门店信息表
			HqlsUserStore userStore = new HqlsUserStore();
			userStore.setStoreId(stoId);
			userStore.setUserId(userId);
			userStoreMapper.insert(userStore);
			
		} else if(registerInfo.getErrcode() == 4006 || registerInfo.getErrmsg().contains("该用户已注册")){ //用户已注册，则同步用户信息, 但是密码可能与用户输入的不一致！！！，需要优化！！！！
			logger.info("用户已注册，则同步用户信息");
			RestModel<AuthUser> uInfo = authService.getUserInfoByUserName(token, mobile);
			if(uInfo.getErrcode() == 0){
				globalUserId = uInfo.getResult().getUserId();
				user.setGlobalUserId(globalUserId);
				if(hquser != null){
					userId = hquser.getUserId();
				}else{
					userMapper.insert(user);
					userId = user.getUserId();
				}
				//新增用户门店信息表
				HqlsUserStore userStore = new HqlsUserStore();
				userStore.setStoreId(stoId);
				userStore.setUserId(userId);
				userStoreMapper.insert(userStore);
			} else {
				logger.info("注册失败.");
				return RestModel.error(HttpStatus.BAD_REQUEST, uInfo.getErrcode(), uInfo.getErrmsg());
			}
		} else {
			logger.info("注册失败....");
			return RestModel.error(HttpStatus.BAD_REQUEST, registerInfo.getErrcode(), registerInfo.getErrmsg());
			
		}
		return RestModel.success();
	}
	
	
	/**]
	 * 修改门店账号
	 * @param account
	 * @return
	 */
	public ResponseEntity<RestModel<String>> changeAccount(String token,String account){
		// 获取当前用户
        RestModel<TokenModel> rest = authService.validToken(token);
        if (rest.getErrcode() != 0) {// 解析token失败
            return RestModel.error(HttpStatus.BAD_REQUEST, rest.getErrcode(), rest.getErrmsg());
        }
        Integer userId = rest.getResult().getUserId();// 当前登录人的userid
        //获取userId
        HqlsUser user = userMapper.getUserByGloabUserId(userId);
        //判断修改后账号在系统中是否存在
        HqlsUser hquser =	userMapper.getUserInfoByMobile(account);
        if(hquser == null){
        //根据userid 更改当前登陆人账号
        	userMapper.changeAccount(account,user.getUserId());
        }
		return RestModel.success();
		
	}
	
	
	/**
	 * 查询所有门店
	 * @return
	 */
	public ResponseEntity<RestModel<List<CommonDto>>> findAllStore(){
		
		List<CommonDto> store = storeMapper.findAllStore();
		return RestModel.success(store);
	}
	
	
	/**根据storeid查询当前门店信息
	 * @param storeId
	 * @return
	 */
	public ResponseEntity<RestModel<StoreInfoDto>> getStoreByStoreId(Integer storeId){
		StoreInfoDto store = storeMapper.getStoreInfoByStoreId(storeId);
		return RestModel.success(store);
		
	}
	
	
	/**
	 * 根据storeid修改当条门店信息
	 * @param storeId
	 * @param storeName
	 * @param backUrl
	 * @param mobile
	 * @param address
	 * @return
	 */
	public ResponseEntity<RestModel<Integer>> changeStoreByStoreId(Integer storeId,String storeName,String backUrl,String mobile,String address){
		storeMapper.updateStoreByStoreId(storeId,storeName,backUrl,mobile,address);
		return RestModel.success();
		
	}
}