package com.example.Narrow.service;

import java.io.IOException;
import java.util.List;

import org.apache.commons.csv.CSVParser;

import com.example.Narrow.model.Geolocation;

public interface GeolocationService {
	public List<Geolocation> getGeolocations();

	public Geolocation getGeoLocation(String city, String country);

	public CSVParser getCSVParser() throws IOException;

}
