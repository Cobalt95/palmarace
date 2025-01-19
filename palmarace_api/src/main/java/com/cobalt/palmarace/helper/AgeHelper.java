package com.cobalt.palmarace.helper;

import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Date;

public final class AgeHelper {

	public static int getAgeFromDateBirth(Date dateBirth) {
		LocalDate dateBirthLocal = dateBirth.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		System.out.println(dateBirthLocal);
		LocalDate todayLocal = LocalDate.now();
		
		return Period.between(dateBirthLocal, todayLocal).getYears();
	}
}
