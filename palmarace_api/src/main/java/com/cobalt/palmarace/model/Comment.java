package com.cobalt.palmarace.model;

import java.time.LocalDateTime;

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
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int commentId;
	private String content;
	private LocalDateTime creationDate;
	
	@OneToOne
	@JoinColumn(name = "athlete_event_id")
	private AthleteEvent athleteEvent;
	
	@OneToOne
	@JoinColumn(name = "athlete_id")
	private Athlete author;
}
