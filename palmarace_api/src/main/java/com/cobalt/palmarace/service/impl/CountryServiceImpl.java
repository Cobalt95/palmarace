package com.cobalt.palmarace.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.model.Country;
import com.cobalt.palmarace.repository.CountryDAO;
import com.cobalt.palmarace.service.abst.CountryService;

@Service
public class CountryServiceImpl implements CountryService {

	@Autowired
	private CountryDAO countryDAO;
	
	@Override
	public Country getById(String countryCode) {
		Optional<Country> optCountry = countryDAO.findById(countryCode);
		if (optCountry.isPresent()) {
			return optCountry.get();
		} else {
			return null;
		}
	}

}
