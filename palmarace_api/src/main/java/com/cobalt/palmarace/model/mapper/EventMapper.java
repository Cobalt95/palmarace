package com.cobalt.palmarace.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cobalt.palmarace.model.Event;
import com.cobalt.palmarace.model.dto.event.EventCreationDTO;

@Mapper
public interface EventMapper {

	EventMapper INSTANCE = Mappers.getMapper(EventMapper.class);
	
	EventCreationDTO toDTO(Event event);
	Event toEntity(EventCreationDTO eventCreationDTO);
}
