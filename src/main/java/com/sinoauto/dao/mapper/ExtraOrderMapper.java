package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sinoauto.dao.bean.HqlsExtraOrder;

@Mapper
public interface ExtraOrderMapper {

	@Insert("insert into hqls_extra_order (service_order_id,order_no,extra_order_no,extra_project_desc,order_amount,pay_status,create_time,dml_time) "
			+ "values (#{serviceOrderId},#{orderNo},#{extraOrderNo},#{extraProjectDesc},#{orderAmount},1,now(),now())")
	public int insertExtraOrder(HqlsExtraOrder order);
	
	@Select("select * from hqls_extra_order where extra_order_no = #{extraOrderNo}")
	public List<HqlsExtraOrder> getExtraOrderCountByExtraOrderNo(@Param("extraOrderNo")String extraOrderNo);
	
	@Update("update hqls_extra_order set pay_status = 2 where extra_order_no = #{extraOrderNo}")
	public int updateExtraOrderPayStatus(@Param("extraOrderNo")String extraOrderNo);

}
