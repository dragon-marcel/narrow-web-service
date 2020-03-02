package com.example.Narrow.filter;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import com.example.Narrow.model.User;
import com.example.Narrow.model.UserPrincipal;
import com.example.Narrow.service.JWTService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private JWTService jWTService;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTService jWTService) {
	this.authenticationManager = authenticationManager;
	setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/api/login", "POST"));
	this.jWTService = jWTService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
	    throws AuthenticationException {
	String username = obtainUsername(request);
	String password = obtainPassword(request);
	if (username == null) {
	    username = "";
	}

	if (password == null) {
	    password = "";
	}
	if (username != null && password != null && !password.equals("") && !username.equals("")) {
	} else {

	    User user = null;
	    try {
		user = new ObjectMapper().readValue(request.getInputStream(), User.class);
		username = user.getUsername();
		password = user.getPassword();

	    } catch (JsonParseException e) {
		e.printStackTrace();
	    } catch (JsonMappingException e) {
		e.printStackTrace();
	    } catch (IOException e) {
		e.printStackTrace();
	    }
	}

	username = username.trim();
	UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
	return authenticationManager.authenticate(authToken);
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
	    Authentication authResult) throws IOException, ServletException {
    	
    	
	String token = jWTService.create(authResult);
	User user = ((UserPrincipal) authResult.getPrincipal()).getUser();
	response.addHeader("Authoriazation", "Bearer " + token);
	Map<String, Object> body = new HashMap<>();
	body.put("userName", user.getUsername());
	body.put("id", user.getId());
	body.put("token", token);
	body.put("name", user.getName());
	body.put("surname", user.getSurname());
	body.put("avatar", user.getAvatar());
	body.put("roles", user.getRoles());
	body.put("expiration", jWTService.getExpired(token));
	response.getWriter().write(new ObjectMapper().writeValueAsString(body));
	response.setStatus(200);
	response.setContentType("application/json");
    }

    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response,
	    AuthenticationException failed) throws IOException, ServletException {
	Map<String, Object> body = new HashMap<>();
	body.put("message", "Blad autoryzacji .Wprowadz poprawny login i hasło.");
	response.getWriter().write(new ObjectMapper().writeValueAsString(body));
	response.setStatus(401);
	response.setContentType("application/json");
    }

}
