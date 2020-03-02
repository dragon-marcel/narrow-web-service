package com.example.Narrow.service;

import java.util.List;

import com.example.Narrow.model.Assortment;

public interface AssortmentService {
    List<Assortment> findAll();

    void save(Assortment assortment);

    void editAssortment(Assortment assortment);

    Assortment findById(Long id);

    Assortment findByName(String name);

    void delete(Assortment assortment);
}
