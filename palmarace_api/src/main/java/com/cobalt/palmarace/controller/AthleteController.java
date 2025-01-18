package com.cobalt.palmarace.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cobalt.palmarace.constant.PalmaraceConstants;
import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.model.dto.AthleteLoginDTO;
import com.cobalt.palmarace.model.dto.AthleteRegisterDTO;
import com.cobalt.palmarace.service.abst.AthleteService;
import com.cobalt.palmarace.service.impl.UserDetailsServiceImpl;

@RestController
public class AthleteController {

	@Autowired
	private AthleteService athleteService;
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private UserDetailsServiceImpl userDetailsService;
	
	@GetMapping("/athlete")
	public ResponseEntity<String> getAthleteName() {
		// Retrieve user from current active session
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		// Retrieve the associated Athlete object
		// If not found, the exception is handled by com.cobalt.palmarace.config.GlobalExceptionHandler
		Athlete athlete = athleteService.getByEmail(authentication.getName());

		return ResponseEntity
				.status(HttpStatus.OK)
				.body(athlete.getFirstName() + " " + athlete.getLastName());
	}
	
	@PostMapping("/register")
	public ResponseEntity<String> registerAthlete(@RequestBody AthleteRegisterDTO athleteRegisterDTO) {
		try {
			String hashPassword = passwordEncoder.encode(athleteRegisterDTO.getPassword());
			athleteRegisterDTO.setPassword(hashPassword);
			Athlete registeredAthlete = athleteService.registerAthlete(athleteRegisterDTO);
			
			if(registeredAthlete.getAthleteId() > 0) {
				
				String jwtToken = userDetailsService.generateJwtToken(registeredAthlete.getEmail(),
						List.of(new SimpleGrantedAuthority(registeredAthlete
								.getProfile()
								.getProfileCode())
								)
						.stream()
						.map(GrantedAuthority::getAuthority)
						.collect(Collectors.joining(",")));
				
				MultiValueMap<String, String> headers = new HttpHeaders();
				headers.add(PalmaraceConstants.JWT_HEADER, jwtToken);
				
				
				return new ResponseEntity<String>(
						"Athlete successfully registered.", 
						headers, 
						HttpStatus.CREATED);
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
	
	@PostMapping("/login")
	public ResponseEntity<String> loginAthlete(@RequestBody AthleteLoginDTO athleteLoginDTO) {
		try {
			String jwtToken = userDetailsService.authenticateAthlete(athleteLoginDTO);
		
			MultiValueMap<String, String> headers = new HttpHeaders();
			headers.add(PalmaraceConstants.JWT_HEADER, jwtToken);
		
			return new ResponseEntity<String>("Login successful", headers, HttpStatus.OK);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid credentials");
		}
	}
	
	@GetMapping("/mustBeAuthenticated")
	public ResponseEntity<String> mustBeAuthenticated() {
		return ResponseEntity.status(HttpStatus.OK).body("Authenticated with JWT token");
	}
}
