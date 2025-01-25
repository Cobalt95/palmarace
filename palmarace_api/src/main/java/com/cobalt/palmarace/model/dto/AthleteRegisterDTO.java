package com.cobalt.palmarace.model.dto;

import java.time.LocalDate;
import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class AthleteRegisterDTO {

	private String lastName;
	private String firstName;
	private LocalDate dateBirth;
	private String email;
	private String password;
	private String bio;
	private String countryCode;
}
