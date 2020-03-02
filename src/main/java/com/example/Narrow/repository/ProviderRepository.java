package com.example.Narrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Narrow.model.Provider;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long>{

}
