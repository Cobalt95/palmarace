package com.cobalt.palmarace.service.abst;

import java.util.NoSuchElementException;

import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;

public interface EventService {

	Event createEvent(EventCreationDTO eventCreationDTO) throws NoSuchElementException;
}
