package com.example.Narrow.service;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Narrow.model.AdditionalDataAddress;
import com.example.Narrow.model.Geolocation;
import com.example.Narrow.model.Provider;
import com.example.Narrow.repository.ProviderRepository;

@Service
public class ProviderServiceImpl implements ProviderService {

	@Autowired
	private ProviderRepository providerRepository;
	@Autowired
	private GeolocationService geolocationService;
	@PersistenceContext
	private EntityManager em;

	@Override
	public List<Provider> findAll() {

		return providerRepository.findAll();
	}

	@Override
	public void save(Provider provider) {
		AdditionalDataAddress addAdrress = provider.getAdditionalDataAddress();
		AdditionalDataAddress latLng = getGeo(provider, addAdrress);

		if (latLng != null) {
			provider.setAdditionalDataAddress(addAdrress);
		}
		providerRepository.save(provider);

	}

	@Override
	public void editProvider(Provider provider) {
		AdditionalDataAddress addAdrress = provider.getAdditionalDataAddress();
		AdditionalDataAddress latLng = getGeo(provider, addAdrress);

		if (latLng != null) {
			provider.setAdditionalDataAddress(addAdrress);
		}
		providerRepository.save(provider);

	}

	@Override
	public Provider findById(Long id) {
		Provider provider = providerRepository.findById(id).orElse(null);
		System.out.print("UZYTKOWNIKA :" + provider.getAdditionalDataAddress().getCity());
		return providerRepository.findById(id).orElse(null);
	}

	@Override
	public Provider findByName(String name) {
		@SuppressWarnings("unchecked")
		List<Provider> providers = em.createQuery("from Provider where UPPER(name) = UPPER(:name)")
				.setParameter("name", name).getResultList();
		if (providers.size() > 0) {
			return providers.stream().findFirst().get();
		} else {
			return null;
		}
	}

	@Override
	public void delete(Provider provider) {
		providerRepository.delete(provider);

	}

	private AdditionalDataAddress getGeo(Provider provider, AdditionalDataAddress addAdrress) {
		Geolocation latLng = geolocationService.getGeoLocation(addAdrress.getCity(), addAdrress.getCountry());
		if (latLng != null) {
			addAdrress.setLat(latLng.getLat());
			addAdrress.setLng(latLng.getLng());
		}
		return addAdrress;
	}
}
