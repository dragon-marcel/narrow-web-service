package com.example.Narrow.model;

import java.io.Serializable;

import javax.persistence.MappedSuperclass;

import lombok.Data;

@Data
@MappedSuperclass
public class Geolocation implements Serializable {

	/**1+8
	 * 
	 */
	public static final long serialVersionUID = 1L;
	public Double lat;
	public Double lng;
	public String city;
	public String country;
	private String symbol;

	public Geolocation() {

	}
	public Geolocation(Double lat, Double lng, String city, String country) {
		this.lat = lat;
		this.lng = lng;
		this.city = city;
		this.country = country;
	}

	public Geolocation(Double lat, Double lng, String city, String country, String symbol) {
		this.lat = lat;
		this.lng = lng;
		this.city = city;
		this.country = country;
		this.symbol = symbol;
	}

}
