package com.cobalt.palmarace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.Place;

public interface PlaceDAO extends JpaRepository<Place, Integer>{

}
