package com.cobalt.palmarace.model;

import java.time.LocalTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AthleteEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer athleteEventId;
	private boolean finisher;
	private LocalTime finishTime;
	private int overallRank;
	private int bibNumber;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "athlete_id")
	private Athlete athlete;
	
	@OneToMany(
			mappedBy = "athleteEvent",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Kudo> kudos;

	@OneToMany(
			mappedBy = "athleteEvent",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Comment> comments;
	
	// Constructors
	
	public AthleteEvent(Athlete athlete) {
		setAthlete(athlete);
	}
	
	public AthleteEvent() {}
}
