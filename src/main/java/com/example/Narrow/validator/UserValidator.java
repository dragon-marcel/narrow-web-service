package com.example.Narrow.validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.example.Narrow.model.User;
import com.example.Narrow.service.UserServiceImpl;

@Component
public class UserValidator implements Validator {

	@Autowired
	private UserServiceImpl userService;

	@Override
	public boolean supports(Class<?> clazz) {
		return User.class.equals(clazz);
	}

	@Override
	public void validate(Object o, Errors errors) {
		User user = (User) o;
		Long id = user.getId();

		if (id != null) {
			String oldUserName = userService.findById(id).getUsername().toUpperCase();
			if (!oldUserName.equals(user.getUsername().toUpperCase())		
					&& userService.findByUsername(user.getUsername()) != null) {
				errors.rejectValue("username", "Użytkownik o loginie "+user.getUsername()+" już istnieje. Proszę wybrać inny.");
			}
		} else {
			if (userService.findByUsername(user.getUsername()) != null && userService.findByUsername(user.getUsername())
					.getName().toUpperCase().equals(user.getUsername().toUpperCase())) {

				errors.rejectValue("username"," Użytkownik o loginie "+user.getUsername()+" już istnieje. Proszę wybrać inny.");
			}
		}
	}
}
