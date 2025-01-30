package com.cobalt.palmarace.service.abst;

import java.util.NoSuchElementException;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;

public interface EventService {

	/**
	 * Creates a new event and attaches it to the current session athlete
	 * @param eventCreationDTO
	 * @param athlete
	 * @return the persisted event
	 * @throws NoSuchElementException
	 */
	Event createEvent(EventCreationDTO eventCreationDTO, Athlete athlete) throws NoSuchElementException;
}
