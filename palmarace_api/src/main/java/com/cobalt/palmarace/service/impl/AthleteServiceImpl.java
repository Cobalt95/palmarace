package com.cobalt.palmarace.service.impl;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.helper.AgeHelper;
import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.Country;
import com.cobalt.palmarace.model.Profile;
import com.cobalt.palmarace.model.dto.athlete.AthleteCreationDTO;
import com.cobalt.palmarace.model.dto.athlete.AthleteViewDTO;
import com.cobalt.palmarace.repository.AthleteDAO;
import com.cobalt.palmarace.service.abst.AthleteService;
import com.cobalt.palmarace.service.exc.AthleteAlreadyExistingException;
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
	public Athlete registerAthlete(AthleteCreationDTO athleteCreationDTO) {
		
		athleteDAO.findByEmail(athleteCreationDTO.getEmail()).ifPresent(existingAthlete -> {
			throw new AthleteAlreadyExistingException();
		});
		
		Athlete athlete = entityFromDTO(athleteCreationDTO);
		
		// Country assignment
		Country country = new Country();
		country.setCountryCode(athleteCreationDTO.getCountryCode());
		athlete.setCountry(country);
		
		// Profile assignment
		Profile profile = new Profile();
		profile.setProfileCode("USR");
		athlete.setProfile(profile);
		
		// TODO : Assign profile picture
		
		athlete.setCreationDate(LocalDateTime.now());
		return athleteDAO.save(athlete);
	}
	
	/**
	 * Converts a DTO into a peristable Athlete object
	 * 
	 * @param athleteRegisterDTO - The DTO to be converted
	 * @return The persistable Athlete object
	 */
	private Athlete entityFromDTO(AthleteCreationDTO athleteCreationDTO) {
		Athlete athlete = new Athlete();
		athlete.setLastName(athleteCreationDTO.getLastName());
		athlete.setFirstName(athleteCreationDTO.getFirstName());
		athlete.setDateBirth(athleteCreationDTO.getDateBirth());
		athlete.setEmail(athleteCreationDTO.getEmail());
		athlete.setPassword(athleteCreationDTO.getPassword());
		athlete.setBio(athleteCreationDTO.getBio());
		return athlete;
	}

	@Override
	public AthleteViewDTO getAthleteInfos(String email) {
		Athlete athlete = getByEmail(email);
		AthleteViewDTO athleteInfosDto = new AthleteViewDTO();
		
		athleteInfosDto.setFirstName(athlete.getFirstName());
		athleteInfosDto.setCountryCode(athlete.getCountry().getCountryCode());
		athleteInfosDto.setBio(athlete.getBio());
		athleteInfosDto.setAge(AgeHelper.getAgeFromDateBirth(athlete.getDateBirth()));
		
		return athleteInfosDto;
	}

}
