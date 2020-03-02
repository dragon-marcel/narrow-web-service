package com.example.Narrow.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.Narrow.model.Assortment;
import com.example.Narrow.service.AssortmentService;

@Component
public class AssortmentValidator implements Validator {
	@Autowired
	private AssortmentService assortmentService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Assortment.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Assortment assortment = (Assortment) target;
		Long id = assortment.getId();

		if (id != null) {
			String oldName = assortmentService.findById(id).getName().toUpperCase();
			if (oldName != null && !oldName.equals(assortment.getName().toUpperCase())
					&& assortmentService.findByName(assortment.getName().toUpperCase()) != null) {
				errors.rejectValue("name", "Nazwa asortymentu już istnieje.");
			}
		} else {
			if (assortmentService.findByName(assortment.getName().toUpperCase()) != null) {
				errors.rejectValue("name", "Nazwa asortymentujuż istnieje.");
			}

		}
	}

}
