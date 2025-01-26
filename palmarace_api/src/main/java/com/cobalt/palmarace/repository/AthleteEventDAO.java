package com.cobalt.palmarace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.AthleteEvent;

public interface AthleteEventDAO extends JpaRepository<AthleteEvent, Integer>{

}
