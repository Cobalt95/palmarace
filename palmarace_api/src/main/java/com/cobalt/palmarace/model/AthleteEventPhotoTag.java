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
public class AthleteEventPhotoTag {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer athleteEventPhotoTagId;
	
	@OneToOne
	@JoinColumn(name = "tag_code")
	private Tag tag;
	
	@OneToOne
	@JoinColumn(name = "athlete_event_photo_id")
	private AthleteEventPhoto athleteEventPhoto;
}
