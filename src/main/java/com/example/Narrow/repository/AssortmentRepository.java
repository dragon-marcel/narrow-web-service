package com.example.Narrow.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Narrow.model.Assortment;
@Repository
public interface AssortmentRepository extends JpaRepository<Assortment, Long> {

}
