package com.cobalt.palmarace.model;

import java.time.LocalDateTime;

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
/**
 * 
 * @author Romain
 * 
 * Because both athleteFollower and athleteFollowed target the same FK,
 * we must prevent JPA from directly handling these columns by using
 * the parameters insertable = false, updatable = false.
 *
 */
public class Connection {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int connectionId;
	private LocalDateTime creationDate;
	
	@ManyToOne
	@JoinColumn(name = "athlete_id", insertable = false, updatable = false)
	private Athlete athleteFollower;
	
	@ManyToOne
	@JoinColumn(name = "athlete_id", insertable = false, updatable = false)
	private Athlete athleteFollowed;
}
