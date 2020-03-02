package com.example.Narrow.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.Narrow.model.Provider;
import com.example.Narrow.service.ProviderService;

@Component
public class ProviderValidator implements Validator {
	@Autowired
	private ProviderService providerService;

	@Override
	public boolean supports(Class<?> clazz) {
		return Provider.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		Provider provider = (Provider) target;
		Long id = provider.getId();

		if (id != null) {
			String oldName = providerService.findById(id).getName().toUpperCase();
			if (oldName != null && !oldName.equals(provider.getName().toUpperCase())
					&& providerService.findByName(provider.getName().toUpperCase()) != null) {
				errors.rejectValue("name", "Nazwa dostawcy już istnieje.");
			}
		} else {
			if (providerService.findByName(provider.getName().toUpperCase()) != null) {
				errors.rejectValue("name", "Nazwa dostawcy już istnieje.");
			}
		}
	}

}
