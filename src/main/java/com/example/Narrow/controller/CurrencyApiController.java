package com.example.Narrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Narrow.model.Currency;
import com.example.Narrow.model.ErrorApi;
import com.example.Narrow.service.CurrencyService;

@RestController
@RequestMapping(value = "/api/currency")

public class CurrencyApiController {

    @Autowired
    private CurrencyService currencyService;

    @GetMapping
    public ResponseEntity<List<Currency>> getCurrency() {
	List<Currency> currency = currencyService.getAllCurency();
	return new ResponseEntity<List<Currency>>(currency, HttpStatus.OK);

    }

    @GetMapping(value = "/recent")
    public ResponseEntity<?> getRecentActualCurrency() {

	Currency currency = currencyService.getActualCurrency();
	if (currency == null) {
	    ErrorApi error = new ErrorApi("Brak aktualnego kursu walut.", "error");
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Currency>(currency, HttpStatus.OK);
    }
}
