package com.cobalt.palmarace.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
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
public class Athlete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer athleteId;
	private String lastName;
	private String firstName;
	private LocalDate dateBirth;
	private String email;
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	private String password;
	private String bio;
	private LocalDateTime creationDate;
	
	@OneToOne
	@JoinColumn(name = "profile_picture")
	private Image profilePicture;
	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;
	@ManyToOne
	@JoinColumn(name = "profile_code")
	private Profile profile;
	
	@OneToMany(
			mappedBy = "athlete",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<AthleteEvent> events;
	
	@OneToMany(
			mappedBy = "athleteFollower",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Connection> connectionsAsFollower;
	
	@OneToMany(
			mappedBy = "athleteFollowed",
			cascade = CascadeType.ALL,
			orphanRemoval = true
	)
	private List<Connection> connectionsAsFollowed;
}
