package com.example.Narrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Narrow.model.ChartBarOrder;
import com.example.Narrow.model.ChartDonutOrder;
import com.example.Narrow.model.ChartLineOrder;
import com.example.Narrow.model.StatisticData;
import com.example.Narrow.model.StatisticProfitMonthByUser;
import com.example.Narrow.service.ChartOrderService;

@RestController
@RequestMapping(value = "/api/charts")
public class ChartOrderApiController {
	@Autowired
	private ChartOrderService chartOrderService;

	@GetMapping(value = "/line/{id}")
	public ResponseEntity<List<ChartLineOrder>> getChartOrderLasWeekByUser(@PathVariable("id") Long id) {
		List<ChartLineOrder> data = chartOrderService.getChartOrderLasWeekByUser(id);
		return new ResponseEntity<List<ChartLineOrder>>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/donut")
	public ResponseEntity<List<ChartDonutOrder>> getChartDonut() {
		List<ChartDonutOrder> data = chartOrderService.getChartDonutData();
		return new ResponseEntity<List<ChartDonutOrder>>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/bar")
	public ResponseEntity<List<ChartBarOrder>> getChartBar() {
		List<ChartBarOrder> data = chartOrderService.getChartBarData();
		return new ResponseEntity<List<ChartBarOrder>>(data, HttpStatus.OK);
	}

	@GetMapping(value = "/statistic/{id}")
	public ResponseEntity<List<StatisticData>> getStatisticOrder(@PathVariable("id") Long id) {
		List<StatisticData> data = chartOrderService.getStatistic(id);
		return new ResponseEntity<List<StatisticData>>(data, HttpStatus.OK);
	}
	
	@GetMapping(value = "/profit/{id}")
	public ResponseEntity<List<StatisticProfitMonthByUser>> getMonthByUser(@PathVariable("id") Long id) {
		List<StatisticProfitMonthByUser> data = chartOrderService.getProfitMonthsByUser(id);
		return new ResponseEntity<List<StatisticProfitMonthByUser>>(data, HttpStatus.OK);
	}

}
