package com.cobalt.palmarace.model;

import java.sql.Date;
import java.sql.Timestamp;

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
public class Athlete {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int athleteId;
	private String lastName;
	private String firstName;
	private Date dateBirth;
	private String email;
	private String password;
	private String bio;
	private Timestamp creationDate;
	
	@OneToOne
	@JoinColumn(name = "profile_picture")
	private Image profilePicture;
	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;
}
