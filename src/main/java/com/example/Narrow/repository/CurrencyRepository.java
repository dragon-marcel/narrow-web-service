package com.example.Narrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Narrow.model.Currency;

public interface CurrencyRepository extends JpaRepository<Currency, Long>{
    

}
