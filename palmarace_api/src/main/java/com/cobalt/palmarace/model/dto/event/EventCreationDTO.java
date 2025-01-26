package com.cobalt.palmarace.model.dto.event;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.cobalt.palmarace.model.dto.place.PlaceCreationDTO;

import jakarta.persistence.Column;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class EventCreationDTO {

	private String name;
	private LocalDate eventDate;
	@Column(precision = 7, scale = 3)
	private BigDecimal distance;
	private PlaceCreationDTO placeCreationDTO;
	private String sportCode;
	// TODO :
	// private GpxTrack gpxTrack;
	// private Image logo;
}
