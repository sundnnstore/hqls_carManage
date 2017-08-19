package com.sinoauto.dao.mapper;

import java.util.List;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.github.pagehelper.Page;
import com.sinoauto.dao.bean.HqlsParts;
import com.sinoauto.dto.CommonDto;
import com.sinoauto.dto.PartsDesListDto;
import com.sinoauto.dto.PartsDetailDto;
import com.sinoauto.dto.PartsDto;
import com.sinoauto.dto.PartsOperDto;
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

	/**
	 * 按配件类型查询第一级分类，pid = 0
	 * @param partsType（1，易损件；2通用件）
	 * @return
	 */
	@Select("select parts_type_id, type_name from hqls_parts_type where parts_type = #{1} and pid = 0")
	public Page<CommonDto> findPartsTypeListByType(Integer partsType);
	
	/**
	 * 按父级Id查询配件类型
	 * @param pid
	 * @return
	 */
	@Select("select parts_type_id, type_name from hqls_parts_type where pid = #{1}")
	public Page<CommonDto> findPartsTypeListByPid(Integer pid);
	
	/**
	 * 根据配件类型查询所有配件信息
	 * @param partTypeId
	 * @return
	 */
	public Page<PartsDesListDto> findPartsListByTypeId(@Param("partTypeId") Integer partTypeId);
	
	@Select("select count(1) from hqls_parts_type where type.pid = #{1}")
	public int getPartsCountByPid(Integer pid);
	
	/**
	 * 查询配件详情
	 * @param partsId
	 * @return
	 */
	public PartsDetailDto getPartsDetail(@Param("partsId") Integer partsId);
	
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
	public List<PartsDto> findPartsByCondition(PartsDto partsDto);
	
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
	 *  递归查询商品树
	 * 	@User liud
	 * 	@Date 2017年8月19日下午12:57:07
	 * 	@param pid
	 * 	@return
	 */
	@Select("select * from hqls_parts_type where pid=#{pid}")
	public List<PartsTreeRecursionDto> partsTreeRecursion(@Param("pid")Integer pid);
}