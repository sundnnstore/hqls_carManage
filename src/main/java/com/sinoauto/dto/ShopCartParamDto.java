package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

/**
 * 购物车信息查询参数Dto
 * @author wuxiao
 * @version 1.0
 * @date 2017-08-17 17:14:21
 */
public class ShopCartParamDto {

	@ApiModelProperty("商品Id")
	private Integer partsId;
	@ApiModelProperty("购买数量")
	private Integer num;

	public Integer getPartsId() {
		return partsId;
	}

	public void setPartsId(Integer partsId) {
		if (partsId != null) {
			this.partsId = partsId;
		}
	}

	public Integer getNum() {
		return num;
	}

	public void setNum(Integer num) {
		if (num != null) {
			this.num = num;
		}
	}

}
