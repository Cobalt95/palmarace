package com.cobalt.palmarace.service.abst;

import java.util.NoSuchElementException;

import com.cobalt.palmarace.model.Place;
import com.cobalt.palmarace.model.dto.place.PlaceCreationDTO;

public interface PlaceService {

	Place createPlace(PlaceCreationDTO placeCreationDTO) throws NoSuchElementException;
}
