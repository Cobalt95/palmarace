package com.cobalt.palmarace.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;
import com.cobalt.palmarace.service.abst.AthleteService;
import com.cobalt.palmarace.service.abst.EventService;

@RestController
@RequestMapping("/event")
public class EventController {
	
	@Autowired
	private EventService eventService;
	@Autowired
	private AthleteService athleteService;

	@PostMapping("/create")
	Event createEvent(@RequestBody EventCreationDTO eventCreationDTO) {
		// Retrieve user from current active session
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Retrieve the associated Athlete object
		Athlete athlete = athleteService.getByEmail(authentication.getName());
		return eventService.createEvent(eventCreationDTO, athlete);
	}
}
