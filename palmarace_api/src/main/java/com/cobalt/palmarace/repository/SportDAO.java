package com.cobalt.palmarace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.Sport;

public interface SportDAO extends JpaRepository<Sport, String>{
}
