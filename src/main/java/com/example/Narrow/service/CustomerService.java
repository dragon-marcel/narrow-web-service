package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.Customer;
import com.example.Narrow.model.StatisticProfitMonthByUser;

public interface CustomerService {
	List<Customer> findAll();

	void save(Customer customer);

	void editCustomer(Customer customer);

	Customer findById(Long id);

	void delete(Customer customer);

	Customer findByName(String name);

}
