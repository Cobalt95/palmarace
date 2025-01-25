package com.cobalt.palmarace.helper;

import java.time.LocalDate;
import java.time.Period;

public final class AgeHelper {

	public static int getAgeFromDateBirth(LocalDate dateBirth) {
		return Period.between(dateBirth, LocalDate.now()).getYears();
	}
}
