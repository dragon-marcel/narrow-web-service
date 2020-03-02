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

import com.example.Narrow.model.ErrorApi;
import com.example.Narrow.model.Provider;
import com.example.Narrow.validator.ProviderValidator;
import com.example.Narrow.service.ProviderService;

@RestController
@RequestMapping(value = "/api/providers")
public class ProviderApiController {

	@Autowired
	private ProviderService providerService;
	@Autowired
	private ProviderValidator providerValidator;

	@GetMapping
	public ResponseEntity<List<Provider>> getProviders() {
		List<Provider> provider = providerService.findAll();
		return new ResponseEntity<List<Provider>>(provider, HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> addProvider(@RequestBody Provider provider, BindingResult bindingResult) {
		providerValidator.validate(provider, bindingResult);
		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();
			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			providerService.save(provider);
			return new ResponseEntity<Provider>(provider, HttpStatus.OK);
		}
	}

	@PatchMapping
	public ResponseEntity<?> editProvider(@RequestBody Provider provider, BindingResult bindingResult) {
		providerValidator.validate(provider, bindingResult);
		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();
			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			try {
				providerService.save(provider);
				return new ResponseEntity<Provider>(provider, HttpStatus.OK);
			} catch (Exception e) {
				ErrorApi error = new ErrorApi(e.toString(), "error");
				return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
			}
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getProvider(@PathVariable Long id) {

		Provider provider = providerService.findById(id);
		if (provider == null) {
			ErrorApi error = new ErrorApi("Brak dostawcy o takim numrze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<Provider>(provider, HttpStatus.OK);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<?> deleteCustomer(@PathVariable Long id) {

		Provider provider = providerService.findById(id);
		if (provider == null) {
			ErrorApi error = new ErrorApi("Brak dostawcy o takim numerze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		try {
			providerService.delete(provider);
			return new ResponseEntity<Provider>(provider, HttpStatus.OK);
		} catch (Exception ex) {
			ErrorApi error = new ErrorApi("Dostawca jest w użyciu", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

}
