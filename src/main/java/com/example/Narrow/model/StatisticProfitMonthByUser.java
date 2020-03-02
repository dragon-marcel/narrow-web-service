package com.example.Narrow.model;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class StatisticProfitMonthByUser {
	private String date;
	private int quantity;
	private BigDecimal profitPLN;
	private BigDecimal profitEUR;



}
