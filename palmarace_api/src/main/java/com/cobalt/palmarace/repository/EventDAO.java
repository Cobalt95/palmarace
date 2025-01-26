package com.cobalt.palmarace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.Event;

public interface EventDAO extends JpaRepository<Event, Integer>{

}
