package com.cobalt.palmarace.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Sport {

	@Id
	private String sport_code;
	private String name;
}
