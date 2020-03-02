package com.example.Narrow.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;
import com.example.Narrow.model.Customer;
import com.example.Narrow.model.User;
import com.example.Narrow.service.CustomerService;

@Component
public class CustomerValidator implements Validator {
	@Autowired
	private CustomerService customerService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Customer customer = (Customer) target;
		Long id = customer.getId();
		if (id != null) {
			String oldName = customerService.findById(id).getName().toUpperCase();
			if (oldName != null && !oldName.equals(customer.getName().toUpperCase())
					&& customerService.findByName(customer.getName().toUpperCase()) != null) {
				errors.rejectValue("name", "Nazwa klienta już istnieje.");
			}
		} else {
			if (customerService.findByName(customer.getName().toUpperCase()) != null) {
				errors.rejectValue("name", "Nazwa klienta już istnieje.");
			}

		}

	}

}
