package com.example.Narrow.service;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import com.example.Narrow.filter.SimpleGrantedAuthoritiesMixin;
import com.example.Narrow.model.UserPrincipal;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Service
public class JWTServiceImpl implements JWTService {

    @Override
    public String create(Authentication auth) throws JsonProcessingException {
	String userName = ((UserPrincipal) auth.getPrincipal()).getUsername().toString();

	Collection<? extends GrantedAuthority> roles = auth.getAuthorities();
	Claims claims = Jwts.claims();

	claims.put("authorites", new ObjectMapper().writeValueAsString(roles));

	@SuppressWarnings("deprecation")
	String token = Jwts.builder().setClaims(claims).setSubject(userName)
		.signWith(SignatureAlgorithm.HS512,
			"Narrow.System.2019.Marcel.Dragon.Narrow.System.2019.Marcel.Dragon".getBytes())
		.setIssuedAt(new Date())
		.setExpiration(new Date(System.currentTimeMillis() + 43200000L))
		.compact();
	return token;

    }

    @Override
    public boolean isValid(String token) {
	try {
	    getClaims(token);
	    return true;
	} catch (JwtException e) {

	    e.printStackTrace();
	    return false;
	}
    }

    @Override
    public Claims getClaims(String token) {
	
	Claims claims = Jwts.parser()
		.setSigningKey("Narrow.System.2019.Marcel.Dragon.Narrow.System.2019.Marcel.Dragon".getBytes())
		.parseClaimsJws(resolve(token)).getBody();

	return claims;
    }

    @Override
    public String getUserName(String token) {
	return getClaims(token).getSubject();
    }

    @Override
    public Collection<? extends GrantedAuthority> getRoles(String token)
	    throws JsonParseException, JsonMappingException, IOException {
	Object roles = getClaims(token).get("authorites");
	Collection<? extends GrantedAuthority> authorities = Arrays
		.asList(new ObjectMapper().addMixIn(SimpleGrantedAuthority.class, SimpleGrantedAuthoritiesMixin.class)
			.readValue(roles.toString().getBytes(), SimpleGrantedAuthority[].class));
	return authorities;
    }

    @Override
    public String resolve(String token) {
	if (token != null && token.startsWith("Bearer")) {
	    return token.replace("Bearer", "");
	} else {
	    return token;
	}
    }

    @Override
    public Long getExpired(String token) {
	return getClaims(token).getExpiration().getTime();
    }
}
