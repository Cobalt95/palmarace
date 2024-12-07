package com.cobalt.palmarace.service.exc;

public class AthleteAlreadyExistingException extends RuntimeException {
	
	@java.io.Serial
	private static final long serialVersionUID = 3955311003361518951L;

	public AthleteAlreadyExistingException() {
		super("There is already an account associated to the provided email");
	}
}
