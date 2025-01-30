package com.cobalt.palmarace.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Event {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer eventId;
	private String name;
	private LocalDate eventDate;
	@Column(precision = 7, scale = 3)
	private BigDecimal distance;
	private int totalParticipants;
	private int totalFinishers;
	
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
	@JoinColumn(name = "logo")
	private Image logo;
	
	@ManyToOne
	@JoinColumn(name = "parent_event_id")
	private Event parentEvent;
	
	@OneToMany(
			mappedBy = "parentEvent",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Event> childEvents;
	
	@OneToMany(
			mappedBy = "event",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<AthleteEvent> athleteEvents;
	
	/**
	 * Adds a new athleteEvent link between this event and the provided athlete
	 * @param athlete
	 */
	public void addAthleteEvent(Athlete athlete) {
		AthleteEvent athleteEvent = new AthleteEvent(athlete);
		athleteEvent.setEvent(this);
		if(athleteEvents == null || athleteEvents.isEmpty()) {
			athleteEvents = List.of(athleteEvent);
		} else {
			athleteEvents.add(athleteEvent);
		}
	}
	
}
