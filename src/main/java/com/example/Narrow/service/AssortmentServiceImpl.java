package com.example.Narrow.service;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.Assortment;
import com.example.Narrow.repository.AssortmentRepository;

@Service
public class AssortmentServiceImpl implements AssortmentService {

	@Autowired
	private AssortmentRepository assortmentRepository;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Assortment> findAll() {
		return assortmentRepository.findAll();
	}

	@Override
	public void save(Assortment assortment) {
		assortmentRepository.save(assortment);

	}

	@Override
	public void editAssortment(Assortment assortment) {
		assortmentRepository.save(assortment);

	}

	@Override
	public Assortment findById(Long id) {
		return assortmentRepository.findById(id).orElse(null);
	}

	@Override
	public Assortment findByName(String name) {

		@SuppressWarnings("unchecked")
		List<Assortment> assortment = em.createQuery("from Assortment where UPPER(name) = UPPER(:name)")
				.setParameter("name", name).getResultList();
		if (assortment.size() > 0) {
			return assortment.stream().findFirst().get();
		} else {
			return null;
		}
	}

	@Override
	public void delete(Assortment assortment) {
		assortmentRepository.delete(assortment);

	}

}
