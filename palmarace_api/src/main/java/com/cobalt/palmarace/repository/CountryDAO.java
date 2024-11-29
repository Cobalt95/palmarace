package com.cobalt.palmarace.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cobalt.palmarace.model.Country;

public interface CountryDAO extends JpaRepository<Country, String> {

}
