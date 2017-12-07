package com.interview.date.service;

import org.springframework.stereotype.Service;

import com.interview.date.util.MyDate;

/**
 * @author naveen
 * 
 * DateComparisonService service class
 */

@Service
public class DateComparisonService {

	/**
	 * Calculates the number of days between two dates
	 * 
	 * @param inputDate1
	 * @param inputDate2
	 * @return earliest date, later date, number of day between inputDate1 and
	 *         inputDate2
	 */
	public String daysDifference(String inputDate1, String inputDate2) {

		final MyDate date1 = new MyDate(inputDate1);
		final MyDate date2 = new MyDate(inputDate2);
		
		final int totalDays1 = date1.calculateDays();
		final int totalDays2 = date2.calculateDays();
		int totalDays = 0;
		if (totalDays2 > totalDays1) {
			// date 1 is earliest
			totalDays = totalDays2 - totalDays1;
			return (inputDate1 + ", " + inputDate2 + ", " + totalDays);

		} else {
			// date 2 is earliest
			totalDays = totalDays1 - totalDays2;
			return (inputDate2 + ", " + inputDate1 + ", " + totalDays);
		}

	}

}
