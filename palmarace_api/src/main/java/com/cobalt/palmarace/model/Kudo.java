package com.cobalt.palmarace.model;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Kudo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int kudoId;
	private LocalDateTime creationDate;
	
	@ManyToOne
	@JoinColumn(name = "athlete_event_id")
	private AthleteEvent athleteEvent;
	
	@ManyToOne
	@JoinColumn(name = "athlete_id")
	private Athlete author;
	
}
