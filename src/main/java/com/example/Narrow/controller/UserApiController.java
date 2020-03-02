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
import com.example.Narrow.model.Role;
import com.example.Narrow.model.User;
import com.example.Narrow.validator.UserValidator;
import com.example.Narrow.service.RoleService;
import com.example.Narrow.service.UserService;

@RestController
@RequestMapping(value = "/api/users")
public class UserApiController {
	@Autowired
	private UserService userService;
	@Autowired
	private UserValidator userValidator;
	@Autowired
	private RoleService roleService;

	@PostMapping
	public ResponseEntity<?> addUser(@RequestBody User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);

		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();

			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			userService.save(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@PatchMapping
	public ResponseEntity<?> editUser(@RequestBody User user, BindingResult bindingResult) {
		userValidator.validate(user, bindingResult);
		if (bindingResult.hasErrors()) {

			String valid = bindingResult.getFieldError().getCode();
			String pole = bindingResult.getFieldError().getField();
			ErrorApi error = new ErrorApi(valid, pole);
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			userService.editUser(user);
			return new ResponseEntity<User>(user, HttpStatus.OK);
		}
	}

	@GetMapping
	public ResponseEntity<List<User>> getUsers() {
		List<User> users = userService.findAll();
		return new ResponseEntity<List<User>>(users, HttpStatus.OK);

	}

	@DeleteMapping(value = "/delete/{id}")
	public ResponseEntity<?> deleteUser(@PathVariable Long id) {
		User user = userService.findById(id);
		if (user == null) {
			ErrorApi error = new ErrorApi("Brak użytkownika o takim numrze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		} else {
			userService.delete(user);
			return new ResponseEntity<>(user, HttpStatus.OK);
		}
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getUser(@PathVariable Long id) {

		User user = userService.findById(id);
		if (user == null) {
			ErrorApi error = new ErrorApi("Brak użytkownika o takim numrze id", "error");
			return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		return new ResponseEntity<User>(user, HttpStatus.OK);
	}
	
	@GetMapping(value = "/roles")
	public ResponseEntity<List<Role>> getRoles() {
		List<Role> roles = roleService.findAll();
		return new ResponseEntity<List<Role>>(roles, HttpStatus.OK);

	}	
}
