package com.cobalt.palmarace.model.mapper;

import org.mapstruct.Mapper;

import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;

@Mapper(componentModel = "spring")
public interface EventMapper {
	
	EventCreationDTO toDTO(Event event);
	Event toEntity(EventCreationDTO eventCreationDTO);
}
