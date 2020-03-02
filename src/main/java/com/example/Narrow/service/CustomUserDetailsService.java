package com.example.Narrow.service;

import java.util.Collection;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.User;
import com.example.Narrow.model.UserPrincipal;
import com.example.Narrow.repository.UserRepository;

@Service
@Transactional
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private UserRepository userRepository;

	@Override
	public UserPrincipal loadUserByUsername(String userName) throws UsernameNotFoundException {

		User user = userRepository.findByUsername(userName)
				.orElseThrow(() -> new UsernameNotFoundException("User name " + userName + " not found."));

		if (!user.isActive()) {
			return null;
		}
		
		boolean enabled = true;
		boolean accountNonExpired = true;
		boolean credentialsNotExpired = true;
		boolean accountNonLocked = true;
		UserPrincipal principal = new UserPrincipal(user, enabled, accountNonExpired, credentialsNotExpired,
				accountNonLocked, getAuthorities(user));
		return principal;

	}

	private static Collection<? extends GrantedAuthority> getAuthorities(User user) {
		String[] userRoles = user.getRoles().stream().map((role) -> role.getName()).toArray(String[]::new);
		Collection<GrantedAuthority> authorities = AuthorityUtils.createAuthorityList(userRoles);
		return authorities;
	}
}
