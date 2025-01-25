package com.cobalt.palmarace.model;

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
public class Place {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int placeId;
	private String region;
	private String city;
	
	@ManyToOne
	@JoinColumn(name = "country_code")
	private Country country;
}
