package com.example.Narrow.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.example.Narrow.service.JWTService;


public class JWTAuthorizationToken extends BasicAuthenticationFilter {

    private JWTService jWTService;

    public JWTAuthorizationToken(AuthenticationManager authenticationManager, JWTService jWTService) {
	super(authenticationManager);
	this.jWTService = jWTService;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
	    throws IOException, ServletException {

	String header = request.getHeader("Authorization");
	if (!requiresAuthentication(header)) {

	    chain.doFilter(request, response);
	    return;
	}
	UsernamePasswordAuthenticationToken authentication = null;
	if (jWTService.isValid(header)) {

	    authentication = new UsernamePasswordAuthenticationToken(jWTService.getUserName(header), null,
		    jWTService.getRoles(header));
	}
	SecurityContextHolder.getContext().setAuthentication(authentication);
	chain.doFilter(request, response);
    }

    protected boolean requiresAuthentication(String header) {
	if (header == null || !header.startsWith("Bearer ")) {
	    return false;
	} else {
	    return true;
	}

    }

}
