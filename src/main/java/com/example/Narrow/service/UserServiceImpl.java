package com.example.Narrow.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Role;
import com.example.Narrow.model.User;
import com.example.Narrow.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Autowired
	private RoleService roleService;
	@Autowired
	private SessionRegistry sessionRegistry;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<User> findAll() {
		@SuppressWarnings("unchecked")
		List<User> users = em.createQuery("from User").getResultList();

		return users;
	}

	@Override
	public void save(User user) {
		
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(true);
		userRepository.save(user);

	}

	@Override
	public User findByUsername(String username) {
		@SuppressWarnings("unchecked")
		List<User> user = em.createQuery("from User where UPPER(username) = UPPER(:username)")
				.setParameter("username", username).getResultList();
		if (user.size() > 0) {
			return user.stream().findFirst().get();
		} else {
			return null;
		}
	}

	@Override
	public List<UserDetails> getCurrentLoggedUsers() {
		List<Object> prencipals = sessionRegistry.getAllPrincipals().stream().collect(Collectors.toList());

		List<UserDetails> usersDetails = new ArrayList<>();

		for (Object principal : prencipals) {
			if (principal instanceof UserDetails) {
				UserDetails user = (UserDetails) principal;
				usersDetails.add(user);
			}
		}

		return usersDetails;

	}

	@Override
	public int getCountCurrentLoggedUsers() {
		return getCurrentLoggedUsers().size();
	}

	@Override
	public void editUser(User editeUser) {
		User user = findById(editeUser.getId());
		user.setName(editeUser.getName());
		user.setUsername(editeUser.getUsername());
		user.setSurname(editeUser.getSurname());
		user.setEmail(editeUser.getEmail());
		user.setWorkplace(editeUser.getWorkplace());
		user.setAvatar(editeUser.getAvatar());
		user.setActive(editeUser.isActive());
		user.setRoles(editeUser.getRoles());
		if (!editeUser.getPassword().equals("")) {
			user.setPassword(bCryptPasswordEncoder.encode(editeUser.getPassword()));
		}
		userRepository.save(user);

	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id).orElse(null);
	}

	@Override
	public void delete(User user) {
		userRepository.delete(user);
	}

}
