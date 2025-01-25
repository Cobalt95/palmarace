package com.cobalt.palmarace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AthleteEventPhoto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int athleteEventPhotoId;
	
	@OneToOne
	@JoinColumn(name = "image_id")
	private Image image;
	
	@OneToOne
	@JoinColumn(name = "athlete_event_id")
	private AthleteEvent athleteEvent;
	
}
