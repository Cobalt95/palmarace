package com.cobalt.palmarace.service.impl;

import java.util.NoSuchElementException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;
import com.cobalt.palmarace.model.mapper.EventMapper;
import com.cobalt.palmarace.repository.EventDAO;
import com.cobalt.palmarace.repository.SportDAO;
import com.cobalt.palmarace.service.abst.EventService;
import com.cobalt.palmarace.service.abst.PlaceService;

@Service
public class EventServiceImpl implements EventService {

	@Autowired
	private SportDAO sportDAO;
	@Autowired
	private EventDAO eventDAO;
	
	@Autowired
	private PlaceService placeService;
	
	@Autowired
	private EventMapper eventMapper;
	
	@Override
	public Event createEvent(EventCreationDTO eventCreationDTO) throws NoSuchElementException {
		
		// TODO : check that eventCreationDTO.distance > 0
		Event event = eventMapper.toEntity(eventCreationDTO);
		// Sport + Place are missing at this point
		// TODO : custom error if element not found
		event.setSport(sportDAO.findById(eventCreationDTO.getSportCode()).orElseThrow());
		// Place is missing at this point
		event.setPlace(placeService.createPlace(eventCreationDTO.getPlaceCreationDTO()));
		
		// TODO : handle GpxTracks, Images and Parent/Child complex events
		return eventDAO.save(event);
	}

}
