package com.cobalt.palmarace.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.model.Country;
import com.cobalt.palmarace.model.Place;
import com.cobalt.palmarace.model.dto.place.PlaceCreationDTO;
import com.cobalt.palmarace.model.mapper.PlaceMapper;
import com.cobalt.palmarace.repository.CountryDAO;
import com.cobalt.palmarace.repository.PlaceDAO;
import com.cobalt.palmarace.service.abst.PlaceService;

@Service
public class PlaceServiceImpl implements PlaceService {

	@Autowired
	private CountryDAO countryDAO;
	@Autowired
	private PlaceDAO placeDAO;
	
	@Override
	public Place createPlace(PlaceCreationDTO placeCreationDTO) throws NoSuchElementException{
		Place place = PlaceMapper.INSTANCE.toEntity(placeCreationDTO);
		// Country is missing at this point
		// TODO : custom error if element not found
		Country country = countryDAO.findById(placeCreationDTO.getCountryCode()).orElseThrow();
		place.setCountry(country);
		return placeDAO.save(place);
	}

}
