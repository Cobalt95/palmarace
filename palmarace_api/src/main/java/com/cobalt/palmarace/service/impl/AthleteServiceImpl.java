package com.cobalt.palmarace.service.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.Country;
import com.cobalt.palmarace.model.dto.AthleteDTO;
import com.cobalt.palmarace.repository.AthleteDAO;
import com.cobalt.palmarace.service.abst.AthleteService;
import com.cobalt.palmarace.service.abst.CountryService;

@Service
public class AthleteServiceImpl implements AthleteService {

	@Autowired
	private AthleteDAO athleteDAO;
	@Autowired
	private CountryService countryService;
	
	@Override
	public Optional<Athlete> getById(int athleteId) {
		return athleteDAO.findById(athleteId);
	}

	@Override
	public Athlete save(AthleteDTO athleteDTO) {
		Athlete athlete = entityFromDTO(athleteDTO);
		
		// Retrieve country
		Country country = countryService.getById(athleteDTO.getCountryCode());
		if(country == null) {
			// TODO : throw error
		}
		
		athlete.setCountry(country);
		athlete.setCreationDate(new Timestamp(System.currentTimeMillis()));
		// TODO : Retrieve profile picture
		return athleteDAO.save(athlete);
	}
	
	private Athlete entityFromDTO(AthleteDTO athleteDTO) {
		Athlete athlete = new Athlete();
		athlete.setLastName(athleteDTO.getLastName());
		athlete.setFirstName(athleteDTO.getFirstName());
		athlete.setDateBirth(athleteDTO.getDateBirth());
		athlete.setEmail(athleteDTO.getEmail());
		athlete.setPassword(athleteDTO.getPassword());
		athlete.setBio(athleteDTO.getBio());
		return athlete;
	}

}
