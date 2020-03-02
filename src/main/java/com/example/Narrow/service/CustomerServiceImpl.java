package com.example.Narrow.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Customer;
import com.example.Narrow.repository.CustomerRepository;

@Service
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	private CustomerRepository customerRepository;

	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Customer> findAll() {
		return customerRepository.findAll();
	}

	@Override
	public void save(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public void editCustomer(Customer customer) {
		customerRepository.save(customer);

	}

	@Override
	public Customer findById(Long id) {
		return customerRepository.findById(id).orElse(null);

	}

	@Override
	public void delete(Customer customer) {
		customerRepository.delete(customer);

	}

	@Override
	public Customer findByName(String name) {

		@SuppressWarnings("unchecked")
		List<Customer> customers = em.createQuery("from Customer where UPPER(name) = UPPER(:name)")
				.setParameter("name", name).getResultList();
		if (customers.size() > 0) {
			return customers.stream().findFirst().get();
		} else {
			return null;
		}

	}

}
