package com.sinoauto.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DateUtil {

	private static SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

	public static List<String> getDateList(String queryDate, Integer days) {
		List<String> dayDesc = new ArrayList<>();
		String[] nums = queryDate.split("-");
		int year = Integer.parseInt(nums[0]);
		int month = Integer.parseInt(nums[1]);
		if (month == 1) {
			month = 12;
		} else {
			month = month - 1;
		}
		int day = Integer.parseInt(nums[2]);
		for (int i = days; i >= 1; i--) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);
			calendar.add(Calendar.DATE, -i);
			Date date = new Date(calendar.getTimeInMillis());
			dayDesc.add(sdf.format(date));
		}
		return dayDesc;
	}
	
	public static List<String> getDateList(Integer days) {
		Date curDate = new Date();
		List<String> dayDesc = new ArrayList<>();
		String[] nums = sdf.format(curDate).split("-");
		int year = Integer.parseInt(nums[0]);
		int month = Integer.parseInt(nums[1]);
		if (month == 1) {
			month = 12;
		} else {
			month = month - 1;
		}
		int day = Integer.parseInt(nums[2]);
		for (int i = days-1; i >= 0; i--) {
			Calendar calendar = Calendar.getInstance();
			calendar.set(year, month, day);
			calendar.add(Calendar.DATE, -i);
			Date date = new Date(calendar.getTimeInMillis());
			dayDesc.add(sdf.format(date));
		}
		return dayDesc;
	}

}
