package com.example.Narrow.service;

import java.util.List;

import org.springframework.security.core.userdetails.UserDetails;

import com.example.Narrow.model.User;

public interface UserService {
	List<User> findAll();

	void save(User user);

	void editUser(User user);

	User findById(Long id);

	User findByUsername(String username);

	List<UserDetails> getCurrentLoggedUsers();

	int getCountCurrentLoggedUsers();

	void delete(User user);
	
}
