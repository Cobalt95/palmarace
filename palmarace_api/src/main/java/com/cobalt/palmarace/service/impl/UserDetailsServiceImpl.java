package com.cobalt.palmarace.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cobalt.palmarace.model.Athlete;
import com.cobalt.palmarace.repository.AthleteDAO;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	
	@Autowired
	private AthleteDAO athleteDAO;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Athlete athlete = athleteDAO.findByEmail(username)
				.orElseThrow(() -> new UsernameNotFoundException("The provided email address does not match any registered user : " + username));
		List<GrantedAuthority> authorities = List.of(new SimpleGrantedAuthority(athlete.getProfile().getProfileCode()));
		
		return new User(username, athlete.getPassword(), authorities);
	}

}
