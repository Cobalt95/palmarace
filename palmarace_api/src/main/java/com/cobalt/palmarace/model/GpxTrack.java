package com.cobalt.palmarace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity(name = "GPX_TRK")
@Getter @Setter
public class GpxTrack {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int gpxTrkId;
	private String name;
	private String gpxVersion;
	private String creator;
}
