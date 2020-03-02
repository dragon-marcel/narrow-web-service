package com.example.Narrow.service;

import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.Narrow.model.Currency;
import com.example.Narrow.repository.CurrencyRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import net.minidev.json.JSONObject;
import net.minidev.json.parser.JSONParser;

@Service
public class CurrencyServiceImpl implements CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;
    @PersistenceContext
    private EntityManager em;
    private final String URL = "http://api.nbp.pl/api/exchangerates/rates/c/eur/today/";

    @Override
    public List<Currency> getAllCurency() {

	return currencyRepository.findAll();
    }

    @Override
    public void save(Currency currency) {
	currencyRepository.save(currency);

    }

    @Override
    public Currency getActualCurrency() {
	return (Currency) em.createQuery("from Currency c order by c.id desc").setMaxResults(1).getSingleResult();
    }

    @Override
    @Scheduled(cron = "0 0 12 * * MON-FRI")
    public void getActualCurrencyFromNBP() {

	Currency currency = null;
	RestTemplate restTemplate = new RestTemplate();
	String result = restTemplate.getForObject(URL, String.class);
	@SuppressWarnings("deprecation")
	JSONParser parser = new JSONParser();
	JSONObject json = null;
	try {
	    json = (JSONObject) parser.parse(result);

	    String symbol = json.get("code").toString();
	    currency = new Currency();
	    currency.setSymbol(symbol);

	    ObjectMapper mapper = new ObjectMapper();

	    JSONObject[] wal = mapper.readValue(json.getAsString("rates"), JSONObject[].class);
	    for (JSONObject eur : wal) {
	    currency.setName("Euro");
		currency.setCreateDate(eur.getAsString("effectiveDate"));
		currency.setBuyPrice(new BigDecimal(eur.getAsString("ask")));
		currency.setSalesPrice(new BigDecimal(eur.getAsString("bid")));
	    }
	} catch (Exception e) {
	    System.out.print(e);
	}
	if (currency != null)
	    save(currency);

    }

}
