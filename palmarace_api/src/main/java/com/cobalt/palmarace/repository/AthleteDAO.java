package com.cobalt.palmarace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.Athlete;

public interface AthleteDAO extends JpaRepository<Athlete, Integer> {

}
