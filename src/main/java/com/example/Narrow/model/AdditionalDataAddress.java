package com.example.Narrow.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import lombok.Data;

@Data
@Entity
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
@Table(name = "ADDITIONAL_DATA_ADRRESS")
public class AdditionalDataAddress extends Geolocation {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private static final long serialVersionUID = 1L;

	public AdditionalDataAddress() {
	}

	public AdditionalDataAddress(Double lat, Double lng, String city, String country) {
		super(lat, lng, city, country);
	}
}
