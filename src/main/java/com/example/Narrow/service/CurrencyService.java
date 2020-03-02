package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.Currency;

public interface CurrencyService {
    public List<Currency> getAllCurency();

    public void save(Currency currency);

    public Currency getActualCurrency();

    public void getActualCurrencyFromNBP();
}
