package com.cobalt.palmarace.model;

import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int eventId;
	private String name;
	private Date date;
	private float distance;
	
	@ManyToOne
	@JoinColumn(name = "place_id")
	private Place place;
	
	@ManyToOne
	@JoinColumn(name = "sport_code")
	private Sport sport;
	
	@ManyToOne
	@JoinColumn(name = "gpx_trk_id")
	private GpxTrack gpxTrack;
	
	@OneToOne
	@JoinColumn(name = "image_id")
	private Image logo;
	
	@ManyToOne
	@JoinColumn(name = "parent_event_id")
	private Event event;
}
