package com.cobalt.palmarace.service.abst;

import java.util.Optional;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.dto.AthleteRegisterDTO;

public interface AthleteService {

	public Optional<Athlete> getById(int athleteId);
	
	public Athlete getByEmail(String email);
	
	
	/**
	 * Register a new athlete
	 * 
	 * @param athleteDTO - Required data for athlete registration
	 * @return The athlete object that has just been saved
	 */
	public Athlete registerAthlete(AthleteRegisterDTO athleteDTO);
}
