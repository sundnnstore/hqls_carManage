package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sinoauto.dao.bean.HqlsPartsType;
import com.sinoauto.dto.PartsTypeDto;

@Mapper
public interface PartsTypeMapper {
	
	/**
	 *  新增节点树
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:02:55
	 * 	@param hqlsPartsType
	 */
	public void insert(HqlsPartsType hqlsPartsType);
	
	/**
	 *  查询配件类型
	 * 	@User liud
	 * 	@Date 2017年8月23日下午12:07:52
	 * 	@return
	 */
	@Select("select parts_type_id as id,type_name as name,pid from hqls_parts_type")
	public List<PartsTypeDto> partsType();
	
	/**
	 * 修改节点树的关系
	 * 	@User liud
	 * 	@Date 2017年8月24日下午4:08:16
	 * 	@param pt
	 */
	public void update(HqlsPartsType pt);
	
	/**
	 *  当父节点树修改,则子节点配件类型统统改变
	 * 	@User liud
	 * 	@Date 2017年9月11日上午11:20:08
	 * 	@param pt
	 */
	@Update("update hqls_parts_type set parts_type =#{partsType},oper_time =now() where pid=#{pid} ")
	public void updatePartsType(HqlsPartsType pt);
	/**
	 * 查询配件类型父类
	 * @param partsTypeId
	 * @return
	 */
	@Select("select `pid` from  `hqls_parts_type` where parts_type_id=#{1}")
	public Integer findPidByPartsTypeId(Integer partsTypeId);
	
	/**
	 * 查询配件类型对象
	 * @param partsTypeId
	 * @return
	 */
	@Select("select * from  `hqls_parts_type` where parts_type_id=#{1}")
	public HqlsPartsType findPartsTypeByPartsTypeId(Integer partsTypeId);
	
	@Delete("delete from `hqls_parts_type` where `parts_type_id` = #{1}")
	public void delete(Integer partTypeId);
}