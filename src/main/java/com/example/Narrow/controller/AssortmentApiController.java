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

import com.example.Narrow.model.Assortment;
import com.example.Narrow.model.ErrorApi;
import com.example.Narrow.validator.AssortmentValidator;
import com.example.Narrow.service.AssortmentService;

@RestController
@RequestMapping(value = "/api/assortments")
public class AssortmentApiController {
    @Autowired
    private AssortmentService assortmentService;
    @Autowired
    private AssortmentValidator assortmentValidator;

    @GetMapping
    public ResponseEntity<List<Assortment>> getAssortments() {
	List<Assortment> assortments = assortmentService.findAll();
	return new ResponseEntity<List<Assortment>>(assortments, HttpStatus.OK);

    }

    @PostMapping
    public ResponseEntity<?> addAssortment(@RequestBody Assortment assortment, BindingResult bindingResult) {
    	assortmentValidator.validate(assortment, bindingResult);
		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();
			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
	    assortmentService.save(assortment);
	    return new ResponseEntity<Assortment>(assortment, HttpStatus.OK);
		}
    }

    @PatchMapping
    public ResponseEntity<?> editAssortment(@RequestBody Assortment assortment, BindingResult bindingResult) {
	assortmentValidator.validate(assortment, bindingResult);
	if (bindingResult.hasErrors()) {

	    String valid = bindingResult.getFieldError().getCode();
	    String pole = bindingResult.getFieldError().getField();
	    ErrorApi error = new ErrorApi(valid, pole);
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	} else {
	    assortmentService.save(assortment);
	    return new ResponseEntity<Assortment>(assortment, HttpStatus.OK);
	}
    }

    @GetMapping(value = "/{id}")
    public ResponseEntity<?> getAssortment(@PathVariable Long id) {

	Assortment assortment = assortmentService.findById(id);
	if (assortment == null) {
	    ErrorApi error = new ErrorApi("Brak asortymentu o takim numrze id", "error");
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

	return new ResponseEntity<Assortment>(assortment, HttpStatus.OK);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<?> deleteAssortment(@PathVariable Long id) {

	Assortment assortment = assortmentService.findById(id);
	if (assortment == null) {
	    ErrorApi error = new ErrorApi("Brak klienta o takim numerze id", "error");
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	try {
	    assortmentService.delete(assortment);
	    return new ResponseEntity<Assortment>(assortment, HttpStatus.OK);
	} catch (Exception ex) {
	    ErrorApi error = new ErrorApi("Klient jest w użyciu", "error");
	    return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
	}

    }
}
