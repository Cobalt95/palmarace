package com.cobalt.palmarace.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.dto.AthleteDTO;
import com.cobalt.palmarace.service.abst.AthleteService;

@RestController
public class AthleteController {

	@Autowired
	private AthleteService athleteService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@GetMapping("/athlete/{athleteID}")
	public ResponseEntity<String> sayHi(@PathVariable(value = "athleteID") int athleteId) {
		Optional<Athlete> optAthlete = athleteService.getById(athleteId);
		if(optAthlete.isPresent()) {
			Athlete athlete = optAthlete.get();
			return ResponseEntity
					.status(HttpStatus.FOUND)
					.body("Hi " + athlete.getFirstName() + " " + athlete.getLastName() + ", welcome to Palmarace !");
		} else {
			return ResponseEntity
					.status(HttpStatus.NOT_FOUND)
					.body("The provided ID does not match any athlete");
		}
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerAthlete(@RequestBody AthleteDTO athleteDTO) {
		try {
			String hashPassword = passwordEncoder.encode(athleteDTO.getPassword());
			athleteDTO.setPassword(hashPassword);
			Athlete savedAthlete = athleteService.save(athleteDTO);
			
			if(savedAthlete.getAthleteId() > 0) {
				return ResponseEntity
						.status(HttpStatus.CREATED)
						.body("Athlete successfully registered.");
			} else {
				return ResponseEntity
						.status(HttpStatus.BAD_REQUEST)
						.body("Athlete registration failed.");
			}
		} catch (Exception e) {
			return ResponseEntity
					.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("An exception occured : " + e.getMessage());
		}
	}
}
