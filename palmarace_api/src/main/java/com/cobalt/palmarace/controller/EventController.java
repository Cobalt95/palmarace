package com.cobalt.palmarace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;
import com.cobalt.palmarace.service.abst.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;

	@PostMapping("/create")
	Event createEvent(@RequestBody EventCreationDTO eventCreationDTO) {
		return eventService.createEvent(eventCreationDTO);
	}
}
