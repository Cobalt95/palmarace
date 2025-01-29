package com.cobalt.palmarace.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cobalt.palmarace.model.Sport;
import com.cobalt.palmarace.service.abst.SportService;

@RestController
@RequestMapping("/sport")
public class SportController {
	
	@Autowired
	private SportService sportService;

	@GetMapping("/all")
	public List<Sport> getAll() {
		return sportService.getAll();
	}
}
