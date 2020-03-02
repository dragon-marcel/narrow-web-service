package com.example.Narrow.service;

import java.io.IOException;
import java.util.Collection;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import io.jsonwebtoken.Claims;

public interface JWTService {
    public String create(Authentication auth) throws JsonProcessingException;

    public boolean isValid(String token);

    public Claims getClaims(String token);

    public String getUserName(String token);

    public Collection<? extends GrantedAuthority> getRoles(String token)
	    throws JsonParseException, JsonMappingException, IOException;

    public String resolve(String token);

    public Long getExpired(String token);

}
