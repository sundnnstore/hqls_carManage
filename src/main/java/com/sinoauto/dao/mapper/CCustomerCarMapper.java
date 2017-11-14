package com.sinoauto.dao.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import com.sinoauto.dao.bean.c.CCustomerCar;

@Mapper
public interface CCustomerCarMapper {

	/**
	 * 根据客户 ID 查询该客户的默认车辆
	 * @param customerId
	 * @author Wuxiao
	 */
	@Select("SELECT * FROM c_customer_car WHERE customer_id = #{customerId} AND is_default = 1")
	public CCustomerCar getDefaultCarByCustomerId(@Param("customerId") Integer customerId);
	
	/**
	 * 查询用户的所有车辆数
	 * @param customerId
	 * @author Wuxiao
	 */
	@Select("select count(1) from c_customer_car where customer_id = #{customerId}")
	public int getCarCountByCustomerId(@Param("customerId") Integer customerId);
	
	/**
	 * 更改用户车辆是否是默认的状态
	 * @param isDefault
	 * @param customerId
	 * @author Wuxiao
	 */
	@Update("update c_customer_car set is_default = #{isDefault} where customer_id = #{customerId}")
	public int updateCarIsDefault(@Param("isDefault") Boolean isDefault, @Param("customerId") Integer customerId);
	
	/**
	 * 添加客户车辆
	 * @param car
	 * @author Wuxiao
	 */
	public int insert(@Param("car") CCustomerCar car);

	/**
	 * 查询客户所有车辆
	 * @param customerId
	 * @author Wuxiao
	 */
	@Select("select * from c_customer_car where customer_id = #{customerId}")
	public List<CCustomerCar> findAllCars(@Param("customerId") Integer customerId);

	/**
	 * 根据主键删除车辆
	 * @param customerCarId 主键ID
	 * @author Wuxiao
	 */
	@Delete("delete from c_customer_car where customer_car_id = #{customerCarId}")
	public int deleteCarByPrimaryKey(@Param("customerCarId") Integer customerCarId);
	
}