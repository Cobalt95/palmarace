package com.cobalt.palmarace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.model.Sport;
import com.cobalt.palmarace.repository.SportDAO;
import com.cobalt.palmarace.service.abst.SportService;

@Service
public class SportServiceImpl implements SportService {
	
	@Autowired
	private SportDAO sportDAO;

	@Override
	public List<Sport> getAll() {
		return sportDAO.findAll();
	}

}
