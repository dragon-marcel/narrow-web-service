package com.example.Narrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Narrow.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
