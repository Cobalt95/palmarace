package com.cobalt.palmarace.service.abst;

import java.util.Optional;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.dto.athlete.AthleteCreationDTO;
import com.cobalt.palmarace.model.dto.athlete.AthleteViewDTO;

public interface AthleteService {

	Optional<Athlete> getById(int athleteId);
	
	Athlete getByEmail(String email);
	
	AthleteViewDTO getAthleteInfos(String email);
	
	/**
	 * Register a new athlete
	 * 
	 * @param athleteCreationDTO - Required data for athlete registration
	 * @return The athlete object that has just been saved
	 */
	Athlete registerAthlete(AthleteCreationDTO athleteCreationDTO);
}
