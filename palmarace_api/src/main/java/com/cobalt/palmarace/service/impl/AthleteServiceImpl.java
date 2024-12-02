package com.cobalt.palmarace.service.impl;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.Country;
import com.cobalt.palmarace.model.Profile;
import com.cobalt.palmarace.model.dto.AthleteDTO;
import com.cobalt.palmarace.repository.AthleteDAO;
import com.cobalt.palmarace.service.abst.AthleteService;
import com.cobalt.palmarace.service.exc.AthleteNotFoundException;

@Service
public class AthleteServiceImpl implements AthleteService {

	@Autowired
	private AthleteDAO athleteDAO;
	
	@Override
	public Optional<Athlete> getById(int athleteId) {
		return athleteDAO.findById(athleteId);
	}
	
	@Override
	public Athlete getByEmail(String email) {
		return athleteDAO.findByEmail(email).orElseThrow(() -> new AthleteNotFoundException());
	}

	@Override
	public Athlete save(AthleteDTO athleteDTO) {
		Athlete athlete = entityFromDTO(athleteDTO);
		
		// Country assignment
		Country country = new Country();
		country.setCountryCode(athleteDTO.getCountryCode());
		athlete.setCountry(country);
		
		// Profile assignment
		Profile profile = new Profile();
		profile.setProfileCode("USR");
		athlete.setProfile(profile);
		
		// TODO : Assign profile picture
		
		athlete.setCreationDate(new Timestamp(System.currentTimeMillis()));
		return athleteDAO.save(athlete);
	}
	
	/**
	 * Converts a DTO into a peristable Athlete object
	 * 
	 * @param athleteDTO - The DTO to be converted
	 * @return The persistable Athlete object
	 */
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
