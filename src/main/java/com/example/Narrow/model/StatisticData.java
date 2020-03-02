package com.example.Narrow.model;

import lombok.Data;

@Data
public class StatisticData {
	private String name;
	private String data;
	private String icon;
	public StatisticData(String name, String data, String icon) {
		super();
		this.name = name;
		this.data = data;
		this.icon = icon;
	}

}
