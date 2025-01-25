package com.cobalt.palmarace.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
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
	
	@ManyToOne
	@JoinColumn(name = "athlete_event_id")
	private AthleteEvent athleteEvent;
	
	@ManyToMany
	@JoinTable(
	        name = "athlete_event_photo_tag",
	        joinColumns = @JoinColumn(name = "athlete_event_photo_id"),
	        inverseJoinColumns = @JoinColumn(name = "tag_code")
	)
	private List<Tag> tags;
	
}
