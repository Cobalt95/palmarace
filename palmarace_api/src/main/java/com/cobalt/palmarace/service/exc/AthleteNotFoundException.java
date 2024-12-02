package com.cobalt.palmarace.service.exc;

public class AthleteNotFoundException extends RuntimeException {
	
	@java.io.Serial
	private static final long serialVersionUID = 3955311003361518951L;

	public AthleteNotFoundException() {
		super("Could not retrieve any matching athlete");
	}

}
