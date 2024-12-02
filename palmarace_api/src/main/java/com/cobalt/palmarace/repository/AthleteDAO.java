package com.cobalt.palmarace.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.Athlete;

public interface AthleteDAO extends JpaRepository<Athlete, Integer> {
	
	public Optional<Athlete> findByEmail(String email);

}
