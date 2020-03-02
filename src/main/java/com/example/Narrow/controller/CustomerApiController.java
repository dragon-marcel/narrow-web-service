package com.example.Narrow.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.Narrow.model.Customer;
import com.example.Narrow.model.ErrorApi;
import com.example.Narrow.validator.CustomerValidator;
import com.example.Narrow.service.CustomerService;

@RestController
@RequestMapping(value = "/api/customers")
public class CustomerApiController {

	@Autowired
	private CustomerService customerService;
	@Autowired
	private CustomerValidator customerValidator;

	@GetMapping
	public ResponseEntity<List<Customer>> getCustomers() {
		List<Customer> customers = customerService.findAll();
		return new ResponseEntity<List<Customer>>(customers, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> addCustomer(@RequestBody Customer customer, BindingResult bindingResult) {
		customerValidator.validate(customer, bindingResult);
		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();
			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			customerService.save(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
	}

	@PatchMapping
	public ResponseEntity<?> editUser(@RequestBody Customer customer, BindingResult bindingResult) {
		customerValidator.validate(customer, bindingResult);
		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();
			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			customerService.save(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getCustomer(@PathVariable Long id) {

		Customer customer = customerService.findById(id);
		if (customer == null) {
			ErrorApi error = new ErrorApi("Brak użytkownika o takim numrze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Customer>(customer, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

		Customer customer = customerService.findById(id);
		if (customer == null) {
			ErrorApi error = new ErrorApi("Brak klienta o takim numerze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			customerService.delete(customer);
			return new ResponseEntity<Customer>(customer, HttpStatus.OK);
		} catch (Exception ex) {
			ErrorApi error = new ErrorApi("Klient jest w użyciu", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
