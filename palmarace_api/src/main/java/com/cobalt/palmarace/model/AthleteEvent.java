package com.cobalt.palmarace.model;

import java.time.LocalTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class AthleteEvent {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int athleteEventId;
	private boolean finisher;
	private LocalTime time;
	private int overallRank;
	private int bibNumber;
	private int totalParticipants;
	private int totalFinishers;
	
	@ManyToOne
	@JoinColumn(name = "event_id")
	private Event event;
	
	@ManyToOne
	@JoinColumn(name = "athlete_id")
	private Athlete athlete;
}
