package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.ChartBarOrder;
import com.example.Narrow.model.ChartDonutOrder;
import com.example.Narrow.model.ChartLineOrder;
import com.example.Narrow.model.StatisticData;
import com.example.Narrow.model.StatisticProfitMonthByUser;

public interface ChartOrderService {
	public List<ChartLineOrder> getChartOrderLasWeekByUser(Long id);

	public List<ChartDonutOrder> getChartDonutData();

	public List<StatisticData> getStatistic(Long idUser);
	
	public List<ChartBarOrder> getChartBarData();
	
	public List<StatisticProfitMonthByUser> getProfitMonthsByUser(Long id);


}
