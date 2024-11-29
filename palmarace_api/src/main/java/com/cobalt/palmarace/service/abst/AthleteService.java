package com.cobalt.palmarace.service.abst;

import java.util.Optional;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.dto.AthleteDTO;

public interface AthleteService {

	public Optional<Athlete> getById(int athleteId);
	
	public Athlete save(AthleteDTO athleteDTO);
}
