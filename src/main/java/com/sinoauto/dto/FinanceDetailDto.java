package com.sinoauto.dto;

import io.swagger.annotations.ApiModelProperty;

public class FinanceDetailDto {

	@ApiModelProperty("流水日期")
	private String financeDate;
	@ApiModelProperty("日流水总收入")
	private Double totalIncome= 0.0;

	public String getFinanceDate() {
		return financeDate;
	}

	public void setFinanceDate(String financeDate) {
		if (financeDate != null) {
			this.financeDate = financeDate;
		}
	}

	public Double getTotalIncome() {
		return totalIncome;
	}

	public void setTotalIncome(Double totalIncome) {
		if (totalIncome != null) {
			this.totalIncome = totalIncome;
		}
	}

}
