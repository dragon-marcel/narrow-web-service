package com.example.Narrow.service;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;
import com.example.Narrow.model.Geolocation;

@Service
public class GeolocationServiceImpl implements GeolocationService {

	public final String PATH_FILE = ClassLoader.getSystemClassLoader()
			.getResource("static/geolocaltion/worldcities.csv").getPath();

	public List<Geolocation> getolocations = null;

	@PostConstruct
	@Override
	public List<Geolocation> getGeolocations() {
		if (getolocations == null) {
			System.out.print("TWORZEEE");
			getolocations = new ArrayList<>();
			CSVParser parser = null;
			try {
				parser = getCSVParser();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			for (CSVRecord records : parser) {

				Double lat = Double.parseDouble(records.get(2).trim());
				Double lng = Double.parseDouble(records.get(3).trim());
				String city = records.get(0).trim();
				String country = records.get(4).trim();
				String symbol = records.get(6).trim();

				Geolocation geolocation = new Geolocation(lat, lng, city, country, symbol);
				getolocations.add(geolocation);
			}

		}
		return getolocations;
	}

	@Override
	public Geolocation getGeoLocation(String city, String country) {
		Geolocation geolocation = null;
		List<Geolocation> getolocation = getGeolocations();
		if (getolocation != null) {
			String countryGeo = country != null ? country : "";
			for (int a = 0; a < getolocation.size(); a++) {

				Geolocation geo = getolocation.get(a);
				if (geo.getCity().equals(city) && (countryGeo.equals("") || geo.getCountry().equals(country))) {
					System.out.print("Znalezionu");
					geolocation = geo;
					break;
				}
			}
		}
		return geolocation;
	}

	@Override
	public CSVParser getCSVParser() throws IOException {

		FileReader file = new FileReader(PATH_FILE);
		CSVParser csvParser = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(file);

		return csvParser;
	}

}
