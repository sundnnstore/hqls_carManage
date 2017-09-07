package com.sinoauto.util;


import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;

import com.sinoauto.dao.bean.HqlsCashBack;
import com.sinoauto.dao.bean.HqlsPurchaseOrder;
import com.sinoauto.dao.bean.HqlsRebate;
import com.sinoauto.dao.bean.HqlsStore;
import com.sinoauto.dao.mapper.CashBackMapper;
import com.sinoauto.dao.mapper.PurchaseOrderMapper;
import com.sinoauto.dao.mapper.RebateMapper;
import com.sinoauto.dao.mapper.StoreMapper;


@Component
public class Scheduler {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	@Autowired
	private PurchaseOrderMapper purchaseOrderMapper;
	@Autowired
	private StoreMapper storeMapper;
	@Autowired
	private CashBackMapper cashBackMapper;
	@Autowired
	private RebateMapper rebateMapper;
	
	/**
	 * 每月1号0时1分查看上个月的所有订单，计算总额是否有返利优惠并按期插入返利记录
	 * @throws ParseException 
	 */
	@Transactional
	@Scheduled(cron = "0 1 0 1 * *")
	public void handlOrderCashBack() throws ParseException {
		Calendar cal = Calendar.getInstance();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		/*
		 *  上个月1号0时0分
		 */
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH)-1, 1);
        String lastMonthFirstDay = sdf.format(cal.getTime());
        /*
         *  这个月1号0时0分
         */
        cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
        String thisMonthFirstDay = sdf.format(cal.getTime());
        //查询所有门店
        List<HqlsStore> storeList = storeMapper.findAllStoreList();
        
        if (!storeList.isEmpty()) {
        	for (HqlsStore store: storeList) {
        		// 获取该门店上个月的所有已完成订单
                List<HqlsPurchaseOrder> orderList = purchaseOrderMapper.findAllCompletedOrder(store.getStoreId(), lastMonthFirstDay, thisMonthFirstDay);
                if (orderList.isEmpty()) {
                	logger.info("{0}年{1}月份没有产生已完成的订单！", cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1)); 
                	continue ;
                }
                // 订单总额
                BigDecimal total = new BigDecimal(0.00);
            	for (HqlsPurchaseOrder order: orderList) {
            		total.add(new BigDecimal(order.getPayFee()));
            	}
            	total.setScale(2, RoundingMode.HALF_UP);
            	// 查询是否满足返利条件
                HqlsCashBack cashBack = cashBackMapper.getCashBackByMoney(total.doubleValue());
            	// 判断
            	if (cashBack == null) { // 不满足
            		logger.info("{0}年{1}月份已完成的订单不满足返利条件！", cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1));
            		continue ;
            	}
            	// 每期返还金额
            	BigDecimal periodBackMoney;
            	// 返利率
            	try {
					if (cashBack.getReturnType() == 1) {
						periodBackMoney = total.multiply(new BigDecimal(cashBack.getDiscount())).divide(new BigDecimal(cashBack.getReturnCycle()));
						periodBackMoney.setScale(2, RoundingMode.HALF_UP);
						// 插入24期数据
						insertRebate(cashBack, store, periodBackMoney, sdf);
					}
					// 返利金额
					else if (cashBack.getReturnType() == 2) {
						periodBackMoney = new BigDecimal(cashBack.getReturnMoney()).divide(new BigDecimal(cashBack.getReturnCycle()));
						// 插入24期数据
						insertRebate(cashBack, store, periodBackMoney, sdf);
					}
				} catch (Exception e) {
					logger.error("{0}{1}{2}{3}插入还款记录发生异常！", store.getProvinceName(), store.getCityName(), 
							store.getCountyName(), store.getStoreName());
					TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
					e.printStackTrace();
				}
        	}
        	logger.info("{0}年{1}月份返利操作完成！", cal.get(Calendar.YEAR), (cal.get(Calendar.MONTH)+1));
        }
	}
	
	public void insertRebate(HqlsCashBack cashBack, HqlsStore store, BigDecimal periodBackMoney, SimpleDateFormat sdf) throws ParseException {
		// 获取24个月的还款日期
		String[] period = getTwentyFourPeriod(new Date(), cashBack.getReturnCycle());
		for (int i = 0; i < cashBack.getReturnCycle(); i++) {
			HqlsRebate rebate = new HqlsRebate();
			rebate.setIsRebate(false);
			rebate.setRebateMoney(periodBackMoney.doubleValue());
			rebate.setRebatePeriod(i+1);
			rebate.setStoreId(store.getStoreId());
			rebate.setRebateTime(sdf.parse(period[i]));
			rebateMapper.insert(rebate);
		}
	}
	
	/**
	 * 根据当前日期获取之后的24月，每月1号的日期
	 * @param date 当前日期
	 * @param cycle 期数
	 * @return
	 */
	public static String[] getTwentyFourPeriod(Date date, int cycle) {
		if (date == null || !(cycle > 0)) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		
		String[] period = new String[cycle];
		// 如果日期是1号当天，那就取当天为第一期
		if (cal.get(Calendar.DATE) == 1) {
			period[0] = sdf.format(cal.getTime());
		} else {
			// 下个月的第一天为第一期
			cal.add(Calendar.MONTH, 1);
			cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), 1);
			period[0] = sdf.format(cal.getTime());
		}
		for (int i = 1; i < period.length; i++) {
			cal.add(Calendar.MONTH, 1);
			period[i] = sdf.format(cal.getTime());
		}
		return period;
	}
	
}
