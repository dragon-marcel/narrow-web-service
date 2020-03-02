package com.example.Narrow;

import java.util.Collections;

import java.io.IOException;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.boot.ApplicationRunner;

import org.springframework.boot.SpringApplication;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.boot.web.servlet.FilterRegistrationBean;

import org.springframework.context.annotation.Bean;

import org.springframework.core.Ordered;

import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import org.springframework.web.filter.CorsFilter;
import net.minidev.json.parser.ParseException;

@SpringBootApplication
@EnableScheduling
public class Narrow {

    public static void main(String[] args) throws IOException, ParseException, java.text.ParseException {
	SpringApplication.run(Narrow.class, args);
	
	

    }
    @Bean
    public FilterRegistrationBean simpleCorsFilter() {  

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();  

        CorsConfiguration config = new CorsConfiguration();  

        config.setAllowCredentials(true); 

        // *** URL below needs to match the Vue client URL and port ***

        config.setAllowedOrigins(Collections.singletonList("http://localhost:8080")); 

        config.setAllowedMethods(Collections.singletonList("*"));  

        config.setAllowedHeaders(Collections.singletonList("*"));  

        source.registerCorsConfiguration("/**", config);  

        FilterRegistrationBean bean = new FilterRegistrationBean<>(new CorsFilter(source));

        bean.setOrder(Ordered.HIGHEST_PRECEDENCE);  

        return bean;  

    }   

}
