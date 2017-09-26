package com.sinoauto.dto;

import java.util.List;
import java.util.Map;

import io.swagger.annotations.ApiModelProperty;

public class FinanceLogDto {

	@ApiModelProperty("总支出")
	private Double totalExpenditure;
	@ApiModelProperty("总收入")
	private Double totalIncome;
	@ApiModelProperty("详细日流水数据")
	private List<FinanceDetailDto> finances;
	@ApiModelProperty("具体到日流水详情")
	private Map<String, DailyFlowDto> flowList;

	public Double getTotalExpenditure() {
		return totalExpenditure;
	}

	public void setTotalExpenditure(Double totalExpenditure) {
		if (totalExpenditure != null) {
			this.totalExpenditure = totalExpenditure;
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

	public List<FinanceDetailDto> getFinances() {
		return finances;
	}

	public void setFinances(List<FinanceDetailDto> finances) {
		if (finances != null) {
			this.finances = finances;
		}
	}

	public Map<String, DailyFlowDto> getFlowList() {
		return flowList;
	}

	public void setFlowList(Map<String, DailyFlowDto> flowList) {
		if (flowList != null) {
			this.flowList = flowList;
		}
	}

}
