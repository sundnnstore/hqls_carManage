package com.sinoauto.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dao.bean.HqlsPartsAttrExtr;
import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsLevelDto;
import com.sinoauto.dto.PartsOperDto;
import com.sinoauto.dto.PartsQueryDto;
import com.sinoauto.dto.PartsTreeDto;
import com.sinoauto.dto.PartsTreeRecursionDto;

@Mapper
public interface PartsMapper {
	
	/**
	 * 按主键查询配件
	 * @param partsId
	 * @return
	 */
	@Select("select * from hqls_parts where parts_id = #{1} and is_usable = 1")
	public HqlsParts getPartsById(Integer partsId);
	
	@Select("select * from hqls_parts where parts_id = #{1}")
	public HqlsParts getPartsByPartsId(Integer partsId);

	/**
	 * 按配件类型查询第一级分类，pid = 0
	 * @param partsType（1，易损件；2通用件）
	 * @return
	 */
	@Select("select parts_type_id as `key`, type_name as `value`, parts_type as `id`, icon as `name` from hqls_parts_type where parts_type = #{1} and pid = 0")
	public Page<CommonDto> findPartsTypeListByType(Integer partsType);
	
	/**
	 * 
	 * @return
	 */
	@Select("select parts_type_id as `key`, type_name as `value`, parts_type as `id` from hqls_parts_type where pid = 0")
	public Page<CommonDto> findAllParts();
	
	/**
	 * 按父级Id查询配件类型集合
	 * @param pid
	 * @return
	 */
	@Select("select parts_type_id as `key`, type_name as `value`, icon as `name` from hqls_parts_type where pid = #{1}")
	public Page<CommonDto> findPartsTypeListByPid(Integer pid);
	
	@Select("select count(*) from hqls_parts_type where type_name = #{1}")
	public Integer findPartsTypeListBytypeName(String typeName);
	/**
	 * 根据配件类型查询所有配件信息
	 * @param partTypeId
	 * @return
	 */
	public Page<PartsDesListDto> findPartsListByTypeId(@Param("partTypeId") Integer partTypeId, @Param("condition") String condition);
	
	@Select("select count(1) from hqls_parts_type as type where type.pid = #{1}")
	public int getPartsCountByPid(Integer pid);
	
	/**
	 * 查询配件详情
	 * @param partsId
	 * @return
	 */
	public PartsDetailDto getPartsDetail(@Param("partsId") Integer partsId);
	
	/**
	 * 根据订单Id查询所有商品
	 * @param orderId
	 * @return
	 */
	public List<PartsDesListDto> findPartsListByOrderId(@Param("orderId") Integer orderId);
	
	/**
	 * 根据配件Id查询数据库最新信息
	 * @param partsIds
	 * @return
	 */
	public PartsDesListDto checkShopCart(@Param("partsId") Integer partsId);
	
	/**
	 * 	中台按 条件查询配件的信息
	 * 	@User liud
	 * 	@Date 2017年8月17日下午1:18:10
	 * 	@param partsDto
	 * 	@return
	 */
	public List<PartsDto> findPartsByCondition(PartsQueryDto partsDto);
	
	/**
	 * 	中台新增配件
	 * 	@User liud
	 * 	@Date 2017年8月17日下午1:19:42
	 * 	@param partsOperDto
	 */
	public void insert(HqlsParts hqlsParts);
	
	/**
	 * 	中台修改配件 
	 * 		1.包括上下架
	 * 		2.配件配置参数修改
	 * 		3.配件图片修改
	 * 	@User liud
	 * 	@Date 2017年8月17日下午1:19:30
	 * 	@param partsOperDto
	 */
	public void update(PartsOperDto partsOperDto);
	
	/**
	 *  根据采购订单ID查询，查询配件信息
	 * 	@User liud
	 * 	@Date 2017年8月18日下午4:00:55
	 * 	@param purchaseOrderId
	 * 	@return
	 */
	@Select("select hp.* from hqls_order_detail hod "
			+ "inner join hqls_parts hp on hod.parts_id=hp.parts_id "
			+ "where hod.purchase_order_id=#{purchaseOrderId}")
	public List<HqlsParts> findPartsByPurchOrderId(@Param("purchaseOrderId") Integer purchaseOrderId);
	
	/**
	 *  普通查询商品树
	 * 	@User liud
	 * 	@Date 2017年8月19日下午12:57:07
	 * 	@param pid
	 * 	@return
	 */
	@Select("select * from hqls_parts_type where pid=#{pid}")
	public List<PartsTreeDto> partsTree(@Param("pid")Integer pid);
	
	/**
	 *  父类查询子类菜单树
	 * 	@User liud
	 * 	@Date 2017年8月19日下午12:57:07
	 * 	@param pid
	 * 	@return
	 */
	@Select("select parts_type_id as id , type_name as name from hqls_parts_type where pid=#{pid}")
	public List<PartsTreeRecursionDto> partsChildTreeByPid(@Param("pid")Integer pid);
	
	/**
	 * 
	 *  父类菜单
	 * 	@User liud
	 * 	@Date 2017年8月22日下午4:56:12
	 * 	@param partsTypeId
	 * 	@return
	 */
	@Select("select parts_type_id as id , type_name as name from hqls_parts_type where parts_type_id=#{partsTypeId}")
	public PartsTreeRecursionDto partsParent(@Param("partsTypeId")Integer partsTypeId);
	
	/**
	 * 根据配件ID查询配件的扩展属性集合
	 * 	@User liud
	 * 	@Date 2017年8月21日下午2:33:46
	 * 	@param partId
	 * 	@return
	 */
	@Select("select * from hqls_parts_attr_extr where parts_id=#{partId}")
	public List<HqlsPartsAttrExtr> findPartsAttrExtrsByPartsId(@Param("partId")Integer partId);
	
	/**
	 * 	
	 * 	@User liud
	 * 	@Date 2017年8月23日下午4:12:15
	 * 	@param onelevel 第一等级
	 * 	@param twolevel 第二等级
	 * 	@param threelevel 第三等级
	 * 	@return
	 */
	public List<PartsLevelDto> findPartsLevel(@Param("onelevel") Integer onelevel,@Param("twolevel") Integer twolevel,@Param("threelevel") Integer threelevel);

	/**
	 * 根据配件id查询配件类型id
	 * @param partId
	 * @return
	 */
	@Select("SELECT parts_type_id FROM hqls_parts WHERE parts_id=#{1}")
	public Integer findPartsTypeIdByPartsId(Integer partId);
	
	/**
	 * 查询父级ID
	 * @param partsTypeId
	 * @return
	 */
	@Select("select * from hqls_parts_type where parts_type_id=#{partsTypeId}")
	public HqlsPartsType findPidByPtId(@Param("partsTypeId")Integer partsTypeId);
	
	@Select("select cur_price from hqls_parts where parts_id = #{1}")
	public Double getCurPriceById(Integer partsId);
	
}