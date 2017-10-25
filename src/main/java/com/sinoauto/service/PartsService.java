package com.sinoauto.service;

import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.beans.factory.annotation.Autowired;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.sinoauto.dao.mapper.PartsMapper;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.StringUtils;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPartsAttrExtr;
import com.sinoauto.dao.bean.HqlsPartsModel;
import com.sinoauto.dao.bean.HqlsPartsPic;
import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dao.mapper.CarBrandMapper;
import com.sinoauto.dao.mapper.PartsAttrExtrMapper;
import com.sinoauto.dao.mapper.PartsPicMapper;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsLevelDto;
import com.sinoauto.dto.PartsListDto;
import com.sinoauto.dto.PartsModelDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.dto.PartsQueryDto;
import com.sinoauto.dto.PartsTreeDto;
import com.sinoauto.dto.PartsTreeRecursionDto;
import com.sinoauto.entity.ErrorStatus;
import com.sinoauto.entity.RestModel;

@Service
public class PartsService {

	@Autowired
	private PartsMapper partsMapper;

	@Autowired
	private PartsPicMapper partsPicMapper;

	@Autowired
	private PartsAttrExtrMapper partsAttrExtrMapper;
	
	@Autowired
	private CarBrandMapper carBrandMapper;
	
	
	/**
	 * 储存每次循环的节点的id,name
	 */
	private List<Map<Integer, String>> mapNode =new  ArrayList<>();
	
	/**
	 * 组装等级的集合 
	 * 		格式 ： 一级  二级  三级
	 */
	private List<List<PartsLevelDto>> partsLevelDtos =new ArrayList<List<PartsLevelDto>>();
	
	/**
	 * 操作标记
	 */
//	private Integer flag=0;
	

	public ResponseEntity<RestModel<List<CommonDto>>> findListByType(Integer partsType, Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		Page<CommonDto> page = partsMapper.findPartsTypeListByType(partsType);
		return RestModel.success(page, (int) page.getTotal());
	}

	public ResponseEntity<RestModel<Map<String, Object>>> findAllParts(Integer pageIndex, Integer pageSize) {
		// 查询所有易损件
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<CommonDto> page1 = partsMapper.findPartsTypeListByType(1);
		// 查询所有通用件
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		Page<CommonDto> page2 = partsMapper.findPartsTypeListByType(2);
		Map<String, Object> map = new HashMap<>();
		map.put("obj1", page1);
		map.put("count1", page1.getTotal());
		map.put("obj2", page2);
		map.put("count2", page2.getTotal());
		return RestModel.success(map);
	}

	public ResponseEntity<RestModel<Boolean>> hasChildType(Integer partsTypeId) {
		// 查询此类别下的子类数量
		int count = partsMapper.getPartsCountByPid(partsTypeId);
		if (count > 0) {
			return RestModel.success(true);
		}
		return RestModel.success(false);
	}

	public ResponseEntity<RestModel<Object>> findListByPid(Integer partsTypeId, Integer pageIndex, Integer pageSize) {
		// 查询此类别下的子类数量
		int count = partsMapper.getPartsCountByPid(partsTypeId);
		// 返回类型
		Object objList;
		// 总记录数
		int totalCount;
		if (pageIndex != null && pageSize != null) {
			PageHelper.startPage(pageIndex, pageSize);
		}
		// 此类别下还有子类
		if (count > 0) {
			Page<CommonDto> page = partsMapper.findPartsTypeListByPid(partsTypeId);
			objList = page;
			totalCount = (int) page.getTotal();
		}
		// 此类别下没有子类，展示商品列表
		else {
			Page<PartsDesListDto> page = partsMapper.findPartsListByTypeId(partsTypeId, null);
			objList = page;
			totalCount = (int) page.getTotal();
		}
		return RestModel.success(objList, totalCount);
	}

	public ResponseEntity<RestModel<List<PartsDesListDto>>> findPartsByConditon(Integer partsTypeId, String condition) {
		if (condition != null) {
			condition = condition.trim();
		}
		return RestModel.success(partsMapper.findPartsListByTypeId(partsTypeId, condition));
	}
	
	public ResponseEntity<RestModel<PartsDetailDto>> getPartsDetail(Integer partsId) {

		return RestModel.success(partsMapper.getPartsDetail(partsId));
	}

	/**
	 * 
	 * 按条件查询配件分类
	 * @User liud
	 * @Date 2017年8月17日上午11:28:59
	 * @param partsDto
	 * @return
	 */
	public ResponseEntity<RestModel<Page<PartsDto>>> findPartsByCondition(PartsQueryDto partsDto, Integer pageIndex, Integer pageSize) {
		PageHelper.startPage(pageIndex, pageSize);
		List<PartsDto> partsDtos = null;// 返回集合
		Page<PartsDto> partsDtoPage = null;// 返回页面
		try {
			partsDtos = partsMapper.findPartsByCondition(partsDto);
			if (partsDtos == null) {
				partsDtos = new ArrayList<>();

			}
			partsDtoPage = (Page<PartsDto>) partsDtos;
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "查询信息异常");
		}
		return RestModel.success(partsDtoPage, (int) partsDtoPage.getTotal());
	}

	/**
	 * 新增
	 * @User liud
	 * @Date 2017年8月17日下午12:44:33
	 * @return
	 */
	@Transactional
	public ResponseEntity<RestModel<Integer>> addParts(PartsOperDto partsOperDto) {
		try {
			if (partsOperDto != null) {
				// 插入配件属性基础表
				HqlsParts hqlsParts = new HqlsParts();
				hqlsParts.setCreateTime(new Date());
				hqlsParts.setCurPrice(partsOperDto.getCurPrice());
				hqlsParts.setDiscount(partsOperDto.getDiscount());
				hqlsParts.setDmlTime(new Date());
				hqlsParts.setIsUsable(partsOperDto.getIsUsable());
				hqlsParts.setOrigin(partsOperDto.getOrigin());
				hqlsParts.setPartsBrandId(partsOperDto.getPartsBrandId());
				hqlsParts.setPartsCode("HQ" + System.currentTimeMillis());
				hqlsParts.setPartsFactory(partsOperDto.getPartsFactory());
				hqlsParts.setPartsModel(partsOperDto.getPartsModel());
				hqlsParts.setPartsName(partsOperDto.getPartsName());
				hqlsParts.setPartsSpec(partsOperDto.getPartsSpec());
				hqlsParts.setPartsTypeId(partsOperDto.getPartsTypeId());// 配件类型id
				hqlsParts.setPartsUnit(partsOperDto.getPartsUnit());
				hqlsParts.setPrice(partsOperDto.getPrice());
				hqlsParts.setRemark(partsOperDto.getRemark());
				hqlsParts.setShelfLife(partsOperDto.getShelfLife());
				partsMapper.insert(hqlsParts);
				// 插入配件图片,和配件动态属性
				insertPartsPicAndAttrExr(partsOperDto, hqlsParts.getPartsId());
			}
			return RestModel.success();
		} catch (Exception e) {
			System.out.println(e);
			// 事物处理
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION,"新增数据异常");
	}

	/**
	 * 修改
	 * @User liud
	 * @Date 2017年8月17日下午12:44:44
	 * @return
	 * 共用接口
	 *		1. 编辑配件信息
	 *		2. 上下架
	 */
	@Transactional
	public ResponseEntity<RestModel<Integer>> updateParts(PartsOperDto partsOperDto) {
		try {
			if (partsOperDto != null) {
				Integer partsId = partsOperDto.getPartsId();
				if (partsId != null) {
					// 修改配件的基本属性
					partsMapper.update(partsOperDto);
					List<HqlsPartsPic> partsPics = partsOperDto.getPartsPics();
					List<HqlsPartsAttrExtr> partsAttrExtrs = partsOperDto.getPartsAttrExtrs();
					List<CommonDto> partsModels = partsOperDto.getCarModels(); //配件车型
					if (partsPics != null && !partsPics.isEmpty()) {
						// 删除配件id对应的所有图片
						partsPicMapper.delete(partsId);
					}
					if (partsAttrExtrs != null && !partsAttrExtrs.isEmpty()) {
						// 删除配件的动态属性
						partsAttrExtrMapper.delete(partsId);
					}
					
					// 插入配件图片,和配件动态属性,配件对应车型
					if (partsAttrExtrs != null || partsPics != null || partsModels!=null ) {
						insertPartsPicAndAttrExr(partsOperDto, partsId);
					}
					
						
				}
			}
			return RestModel.success();
		} catch (Exception e) {
			System.out.println(e);
			// 事物处理
			TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
		}
		return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "修改商品信息异常");
	}

	/**
	 * 	插入配件图片和配件参数配置
	 * 	@User liud
	 * 	@Date 2017年8月17日下午4:32:28
	 * 	@param partsOperDto
	 */
	public void insertPartsPicAndAttrExr(PartsOperDto partsOperDto, Integer partsId) {
		/**
		 * 配件插入表
		 */
		HqlsPartsAttrExtr hqlsPartsAttrExtr = null;// 插入配件动态属性表
		HqlsPartsPic partsPic = null;// 插入配件图片
		HqlsPartsModel partsModel = null;//配件对应车型

		// 配件的配置属性
		if (partsOperDto.getPartsAttrExtrs()!=null&&partsOperDto.getPartsAttrExtrs().size() > 0) {
			for (HqlsPartsAttrExtr partsAttrExtrs : partsOperDto.getPartsAttrExtrs()) {
				hqlsPartsAttrExtr = new HqlsPartsAttrExtr();
				hqlsPartsAttrExtr.setAttrKey(partsAttrExtrs.getAttrKey());
				hqlsPartsAttrExtr.setAttrValue(partsAttrExtrs.getAttrValue());
				hqlsPartsAttrExtr.setPartsId(partsId);
				partsAttrExtrMapper.insert(hqlsPartsAttrExtr);
			}
		}
		// 配件图片
		List<HqlsPartsPic> hqlsPartsPic = partsOperDto.getPartsPics();
		if (hqlsPartsPic!=null && hqlsPartsPic.size() > 0) {
			for (int i = 0; i < hqlsPartsPic.size(); i++) {
				partsPic = new HqlsPartsPic();
				partsPic.setPartsId(partsId);
				partsPic.setCreateTime(new Date());
				partsPic.setSorting(i + 1);
				partsPic.setUrl(hqlsPartsPic.get(i).getUrl());
				partsPicMapper.insert(partsPic);
			}
		}
		
		// 配件对应车型
		List<CommonDto> hqlsPartsCarModel = partsOperDto.getCarModels();
		if(hqlsPartsCarModel!=null&&hqlsPartsCarModel.size()>0){
			for (int i = 0; i < hqlsPartsCarModel.size(); i++) {
				partsModel = new HqlsPartsModel();
				partsModel.setModelId(hqlsPartsCarModel.get(i).getId());
				partsModel.setPartsId(partsId);
				partsMapper.insertPartsModel(partsModel);
			}
		}
	}

	/**
	 * 	查询采购订单对应的配件ID
	 * 	@User liud
	 * 	@Date 2017年8月18日下午4:18:39
	 * 	@param purchOrderId
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<HqlsParts>>> findPartsByPurchOrderId(Integer purchOrderId) {
		List<HqlsParts> parts = null;
		try {
			parts = partsMapper.findPartsByPurchOrderId(purchOrderId);
			if (parts == null) {
				parts = new ArrayList<>();
			}
		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "查询订单对应的配件信息异常");
		}
		return RestModel.success(parts);
	}

	/**
	 *  配件树形菜单,点击查询下一级菜单
	 * 		1.根据一个pid找到下面的所有子类的id
	 * 		2.再拿子类的id去pid中找看是否存在子id
	 * 		3.如果存在子id则再去找
	 * 	@User liud
	 * 	@Date 2017年8月19日上午11:06:56
	 * 	@param pId
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<PartsTreeDto>>> partsTree(Integer pid) {
		List<PartsTreeDto> childrenParts = null;
		try {
			pid = pid == null ? 1 : pid;
			childrenParts = partsMapper.partsTree(pid);
			if (childrenParts == null) {
				childrenParts = new ArrayList<>();
			}

		} catch (Exception e) {
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(), "配件树形菜单查询异常");
		}
		return RestModel.success(childrenParts);
	}

	/**
	 * 递归查询
	 * @param pid 父id
	 * @param operflag 操作标识 --> 1:新增  2.编辑 3.查看 
	 * @return
	 */
	public PartsTreeRecursionDto partsTreeRecursion(Integer pid, Integer operflag) {
		/**
		 * 配件子集
		 */
		List<PartsTreeRecursionDto> childTree = null;
		
		/**
		 * 当前配件类型信息
		 */
		PartsTreeRecursionDto partsParent = partsMapper.partsParent(pid);
		if (partsParent == null) {partsParent = new PartsTreeRecursionDto();};
		if (operflag == null) {operflag =100;}
		switch (operflag) {
		case 1:
			partsParent.setSpread(false); // 收缩
			break;
		case 2:
			partsParent.setSpread(true); // 展开
			break;
		case 3:
			partsParent.setSpread(true); // 展开
			break;
		default:
			partsParent.setSpread(false); // 展开
			break;
		}

		childTree = partsMapper.partsChildTreeByPid(pid);
		// 判断是否存在子菜单
		if (childTree != null) {
			// 创建存储子菜单集合
			partsParent.setChildren(new ArrayList<>());
			// 存储子节点树；
			for (PartsTreeRecursionDto child : childTree) {
				// 查询子菜单下是否存在子菜单
				PartsTreeRecursionDto part = partsTreeRecursion(child.getId(), operflag);
				partsParent.getChildren().add(part);
			}
		} else {
			childTree = new ArrayList<>();
		}
		return partsParent;
	}

	/**
	 * 返回格式为
	 * 		一级  二级  三级  
	 * 	@User liud
	 * 	@Date 2017年8月25日下午4:38:43
	 * 	@param partsTypeId
	 * 	@param depth
	 * 	@param pageIndex
	 * 	@param pageSize
	 * 	@return
	 */
	public ResponseEntity<RestModel<List<List<PartsLevelDto>>>> findPartsByLevel(Integer partsTypeId,Integer depth,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		List<List<PartsLevelDto>> pagePartsLevel=null; //页面数据
		//List<PartsLevelDto> partsLevelsInfo =null;//最后返回数据
		PartsTreeRecursionDto data =null ;
		try {
			if(partsTypeId!=null){ //根据当前配件类型查询
				/**
				 * 反查第一级
				 */
				Integer pid = findTopId(partsTypeId);
				if(pid==null) pid=0;
				
				/**
				 * 拿到菜单数据源
				 */
				data = this.findPartsTreeByDepth(pid,depth);
				if(data==null){
					data =new PartsTreeRecursionDto();
				}else{
					/**
					 * 按照 一级  二级  三级  组装树形菜单
					 */
					pagePartsLevel = parseTreeRecurData(data,depth);
				}
			}else{//查询全部
				data = this.findPartsTreeByDepth(0,depth);
				if(data==null){
					data =new PartsTreeRecursionDto();
				}else{
					pagePartsLevel = parseTreeRecurData(data,depth);
				}
			}
			//初始化全局变量
			partsLevelDtos = new ArrayList<>();
//			flag=0;
			mapNode =new ArrayList<>();
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.INTERNAL_SERVER_ERROR, ErrorStatus.SYSTEM_EXCEPTION.getErrcode(),"查询树形等级信息失败");
		}
		int total= 0;
		if(pagePartsLevel==null){
			total =0;
		}else{
			total = (int)pagePartsLevel.size();
		}
		return RestModel.success(pagePartsLevel,total);
		
	}

	/**
	 * 根据当前id反一级菜单ID
	 * @param partsTypeId
	 * @return
	 */
	public Integer findTopId(Integer partsTypeId) {
		// 查询出当前partstypeid的父级别id
		Integer retId=null;
		HqlsPartsType hqlsPartsType = partsMapper.findPidByPtId(partsTypeId);
		if(hqlsPartsType!=null){
			if(hqlsPartsType.getPid()!=null&&hqlsPartsType.getPid()==0){
				return partsTypeId;
			}else{
				System.out.println("pid----->"+hqlsPartsType.getPid());
				retId = findTopId(hqlsPartsType.getPid());
			}
		}else{
			retId =partsTypeId;
		}
		return retId;
	}
	
	/**
	 * 	组装数据，返回格式为
	 * 		一级  二级  三级 ,如果不存在 则给个默认值 暂无
	 * 	@User liud
	 * 	@Date 2017年8月25日下午4:30:34
	 * 	@param data
	 * 	@param depth
	 * 	@return  
	 * 
	 *  备注临时存储等级的对象最好 将hashmap --> 换成对象
	 */
	public List<List<PartsLevelDto>> parseTreeRecurData(PartsTreeRecursionDto data,Integer depth){
		List<PartsTreeRecursionDto> partChildren =null; //组装数据
		/**
		 * 存储循环过得配件类型的id 和 name
		 */
		ConcurrentHashMap<Integer, String> nodeTemp =new ConcurrentHashMap<Integer, String>();
		if(data!=null){
			partChildren = data.getChildren();
			for (PartsTreeRecursionDto nextNode : partChildren) {
					System.out.println("nextNodeId"+nextNode.getId()+"\tnextNodeName"+nextNode.getName());
					//存储等级临时数据	
					if(nextNode.getId()>0&&nextNode.getChildren().size()>0){
						/**
						 * 可以用对象代替，这样可以省一点事,减少维护
						 */
						nodeTemp.put(nextNode.getId(), nextNode.getName());
						mapNode.add(nodeTemp);			
					}
					
					/**
					 * 递归查询
					 */
					parseTreeRecurData(nextNode,depth);
					//System.out.println("当他自己下面没有集合的时候跳出的节点:"+nextNode.getChildren().size()+"\nNodeName:"+nextNode.getName());
					//如果我能确定跳出来的是最后一级则,不删除追加
					Integer le = nextNode.getChildren().size(); //子节点数量
					if(le<=0){
						nodeTemp.clear();
						nodeTemp.put(nextNode.getId(), nextNode.getName());
						mapNode.add(nodeTemp);
						//新增
						List<PartsLevelDto> orginzeOneLevelInfo =new ArrayList<PartsLevelDto>();
						for (int i = 0; i < depth; i++) {  //自定义的查询树深度
							if(mapNode.size()>i){  //级别 
								Map<Integer, String> node = mapNode.get(i);
								if(node!=null&&!node.isEmpty()){
									Iterator<Entry<Integer, String>> iter = node.entrySet().iterator();
									while (iter.hasNext()) {
										Map.Entry<Integer, String> entry = iter.next();
										Integer id =entry.getKey(); //id
										String name =entry.getValue(); //name
										PartsLevelDto levelInfo =new PartsLevelDto();
										//将这个值拼装到 List<PartsLevlDto中去>
										id=id==null?0:id;
										name=name==null?"暂无数据":name;
										levelInfo.setId(id);
										levelInfo.setName(name);
										orginzeOneLevelInfo.add(levelInfo);  //orginzeOneLevelInfo
									}
									
								}
								
							}
						}
						if(mapNode.size()<depth){
							Integer len = mapNode.size();
							for (int j = 0; j <(depth-len);j++) {
								PartsLevelDto levelInfo =new PartsLevelDto();
								levelInfo.setId(-1);
								levelInfo.setName("暂无数据");
								orginzeOneLevelInfo.add(levelInfo);
								
							}
						}
						
						mapNode.remove(mapNode.size()-1);
						nodeTemp.clear();
						/**
						 * 添加到返回对象中去
						 */
						partsLevelDtos.add(orginzeOneLevelInfo);
						
					}else{//如果不是最后一级,则删除他下面的子集和他自己
						if(mapNode.size()>0){
							for (int i = 0; i < le; i++) {
								Integer chilId = nextNode.getChildren().get(i).getId();//他自己下面的子集对象
								Integer delEleId =null;
								for (int j = 0; j < mapNode.size(); j++) {
									//存储的临时子集下面的对象
									Map<Integer, String> delEle = mapNode.get(j);
									Iterator<Entry<Integer, String>> iter = delEle.entrySet().iterator();
									while (iter.hasNext()) {
										Map.Entry<Integer, String> entry = iter.next();
										delEleId =entry.getKey();
										if(chilId==delEleId){//临时变量中是否存在当前id 和他子集id 
											mapNode.remove(j);
											j=0;
										}
									}
									if(nextNode.getId()==delEleId){ //删除他自己
										mapNode.remove(j);
										j=0;
									}
									if(mapNode.size()==0){mapNode.clear();nodeTemp.clear();}
								}
								
							}
						}
					}
					
				}
			}			
		return partsLevelDtos;
	}
	
	/**
	 * 	按深度返回树形菜单集合
	 * 	@User liud
	 * 	@Date 2017年8月25日下午3:42:11
	 * 	@param pid 
	 * 	@param depth 查询深度
	 * 	@return
	 */
	public PartsTreeRecursionDto findPartsTreeByDepth(Integer pid,Integer depth){
			/**
			 * 配件子集
			 */
			List<PartsTreeRecursionDto> childTree = null;
			
			/**
			 * 当前配件类型信息
			 */
			PartsTreeRecursionDto partsParent = partsMapper.partsParent(pid);
			if (partsParent == null) {partsParent = new PartsTreeRecursionDto();};

			childTree = partsMapper.partsChildTreeByPid(pid);
			// 判断是否存在子菜单
			if (childTree != null) {
				// 创建存储子菜单集合
				partsParent.setChildren(new ArrayList<>());
				// 存储子节点树；
				for (PartsTreeRecursionDto child : childTree) {
					// 查询子菜单下是否存在子菜单
					PartsTreeRecursionDto part = findPartsTreeByDepth(child.getId(),depth);
					partsParent.getChildren().add(part);
				}
			} else {
				childTree = new ArrayList<>();
			}
			return partsParent;
	}
	
	/**
	 * 	商品分类查看和编辑显示  ,从最后一个级别开始查,知道找到最上层一个,跳出循环
	 * 	@User liud
	 * 	@Date 2017年9月1日上午11:04:49
	 * 	@param pid
	 * 	@param operflag
	 * 	@return
	 */
	public PartsTreeRecursionDto partsTreeForEditAndView(Integer lastChildId, Integer operflag) {
		HqlsPartsType hqlsPartsType = partsMapper.findPidByPtId(lastChildId);
		if(hqlsPartsType!=null){
			if(hqlsPartsType.getPid()!=null&&hqlsPartsType.getPid()==0){ //如果到最顶层,返回对象,先倒过来查
			}else{
				System.out.println("pid----->"+hqlsPartsType.getPid());
			}
		}else{//对象不存在
			//retId =partsTypeId;
		}
		return null;
	}
	
	/**
	 * 查询所有车辆品牌
	 * @return
	 */
	public ResponseEntity<RestModel<List<CommonDto>>> findAllBrands() {
		try {
			return RestModel.success(carBrandMapper.findAllBrands());
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	
	/**
	 * 根据品牌id查询车系
	 * 
	 * @param brandId
	 * @return
	 */
	public ResponseEntity<RestModel<List<CommonDto>>> findSeriesByBrandId(Integer brandId) {
		try {
			return RestModel.success(carBrandMapper.findSeriesByBrandId(brandId));
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 * 根据品牌id查询车系
	 * 
	 * @param brandId
	 * @return
	 */
	public ResponseEntity<RestModel<List<CommonDto>>> findModelsBySeriesId(Integer seriesId) {
		try {
			return RestModel.success(carBrandMapper.findModelsBySeriesId(seriesId));
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 * 根据车型Id查询配件
	 * @param modelId
	 * @return
	 */
	public ResponseEntity<RestModel<Map<String, Object>>> findPartsByModelId(Integer modelId) {
		try {
			List<PartsListDto> partsList = partsMapper.findPartsByModelId(modelId);
			List<CommonDto> typeList = partsMapper.findPartsTypeByModelId(modelId);
			Map<String, Object> result = new HashMap<String, Object>();
			result.put("partsList", partsList);
			result.put("typeList", typeList);
			return RestModel.success(result);
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 * 根据配件名称或车型条件查询配件
	 * @param condition
	 * @return
	 */
	public ResponseEntity<RestModel<List<PartsListDto>>> findPartsByCondition(String condition) {
		try {
			List<PartsListDto> partsList;
			if (StringUtils.isEmpty(condition)) {
				partsList  = partsMapper.findPartsByModelId(null);
			} else {
				String[] conditions = condition.split("\\ ");
				partsList = partsMapper.findPartsListByCondition(conditions);
			}
			return RestModel.success(partsList);
		} catch (Exception e) {
			e.printStackTrace();
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION);
		}
	}
	
	/**
	 *  车辆品牌查询
	 * 	@User liud
	 * 	@Date 2017年10月19日下午5:23:50
	 * 	@param brandName
	 * 	@param pageIndex
	 * 	@param pageSize
	 * 	@return
	 */
	public ResponseEntity<RestModel<Page<CommonDto>>> carBrandCombobox(String brandName,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		try {
			Page<CommonDto> page = partsMapper.carBrandCombobox(brandName);
			return RestModel.success(page, (int) page.getTotal());
		} catch (Exception e) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION,"车辆品牌查询异常");
		}
	} 
	
	
	/**
	 *  车型下拉数据
	 * 	@User liud
	 * 	@Date 2017年10月18日下午5:34:52
	 * 	@param seriesId
	 * 	@param modelName
	 * 	@param pageIndex
	 * 	@param pageSize
	 * 	@return
	 */
	public ResponseEntity<RestModel<Page<CommonDto>>> carSeriesCombobox(Integer brandId,String seriesName,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		try {
			Page<CommonDto> page = partsMapper.carSeriesCombobox(brandId, seriesName);
			return RestModel.success(page, (int) page.getTotal());
		} catch (Exception e) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION,"车系查询异常");
		}
	} 
	
	/**
	 *  车型下拉数据
	 * 	@User liud
	 * 	@Date 2017年10月18日下午5:34:52
	 * 	@param seriesId
	 * 	@param modelName
	 * 	@param pageIndex
	 * 	@param pageSize
	 * 	@return
	 */
	public ResponseEntity<RestModel<Page<CommonDto>>> carModelCombobox(Integer seriesId,String modelName,Integer pageIndex,Integer pageSize){
		PageHelper.startPage(pageIndex, pageSize);
		try {
			Page<CommonDto> page = partsMapper.carModelCombobox(seriesId, modelName);
			return RestModel.success(page, (int) page.getTotal());
		} catch (Exception e) {
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION,"车型查询异常");
		}
	}
	
	/**
	 *  查询配件车型明细
	 * @param partsId
	 * @return
	 */
	public ResponseEntity<RestModel<Page<PartsModelDto>>> viewPartsModels(Integer partsId,Integer pageIndex,Integer pageSize){
		try {
			PageHelper.startPage(pageIndex, pageSize);
			Page<PartsModelDto> pmd = partsMapper.viewPartsModel(partsId);
			if(pmd==null) pmd = new Page<>();
			return RestModel.success(pmd,(int)pmd.getTotal());
		} catch (Exception e) {
			System.out.println(e);
			return RestModel.error(HttpStatus.BAD_REQUEST, ErrorStatus.SYSTEM_EXCEPTION,"查询配件车型明细异常");
		}
	}
	
	/**
	 * 删除配件车型关联
	 * 	@User liud
	 * 	@Date 2017年10月25日下午2:14:48
	 * 	@param carModelId
	 */
	public boolean deletePartsCarModelByModelId(Integer carModelId){
		try{
			partsMapper.deletePartsCarModelByModelId(carModelId);
		}catch (Exception e) {
			return false;
		}
		return true;
		
	}
	
}