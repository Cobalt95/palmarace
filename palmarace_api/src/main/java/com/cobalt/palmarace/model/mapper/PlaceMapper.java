package com.cobalt.palmarace.model.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.cobalt.palmarace.model.Place;
import com.cobalt.palmarace.model.dto.place.PlaceCreationDTO;

@Mapper
public interface PlaceMapper {

	PlaceMapper INSTANCE = Mappers.getMapper(PlaceMapper.class);
	
	Place toEntity(PlaceCreationDTO placeCreationDTO);
}
