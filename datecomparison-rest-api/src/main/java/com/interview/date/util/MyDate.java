package com.interview.date.util;

/**
 * @author naveen
 *
 */
public class MyDate {
	/**
	 * variable for day
	 */
	private int day;

	/**
	 * variable for month
	 */
	private int month;

	/**
	 * variable for year
	 */
	private int year;

	/**
	 * Constructor takes a date string in DD MM YYYY format
	 * 
	 * @param strDate
	 * @throws IllegalArgumentException
	 *             if invalid
	 */
	public MyDate(String strDate) throws IllegalArgumentException {
		String[] s = strDate.split(" ");
		if (s.length != 3)
			throw new IllegalArgumentException("Please enter correct date format as command line arg DD MM YYYY, DD MM YYYY");
		int yy = Integer.parseInt(s[2]);
		int mm = Integer.parseInt(s[1]);
		int dd = Integer.parseInt(s[0]);
		if (!isDateValid(yy, mm, dd))
			throw new IllegalArgumentException("Invalid dates. Please verify the day, month and year");
		this.year = yy;
		this.month = mm;
		this.day = dd;
	}

	/**
	 * Getter for day
	 * 
	 * @return day
	 */
	public int getDay() {
		return day;
	}

	/**
	 * @param day
	 *            the day to set
	 */
	public void setDay(int day) {
		this.day = day;
	}

	/**
	 * Getter for month
	 * 
	 * @return month
	 */
	public int getMonth() {
		return month;
	}

	/**
	 * @param month
	 *            the month to set
	 */
	public void setMonth(int month) {
		this.month = month;
	}

	/**
	 * Getter for year
	 * 
	 * @return year
	 */
	public int getYear() {
		return year;
	}

	/**
	 * @param year
	 *            the year to set
	 */
	public void setYear(int year) {
		this.year = year;
	}

	/**
	 * Check if given year/month/day is valid
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @return true if it is valid date
	 */
	private boolean isDateValid(int year, int month, int day) {
		if ((year < 1900) || (year > 2010))
			return false;
		if ((month < 1) || (month > 12))
			return false;
		if ((day < 1) || (day > 31))
			return false;
		switch (month) {
		case 1:
			return true;
		case 2:
			return (isLeap(year) ? day <= 29 : day <= 28);
		case 3:
			return true;
		case 4:
			return day < 31;
		case 5:
			return true;
		case 6:
			return day < 31;
		case 7:
			return true;
		case 8:
			return true;
		case 9:
			return day < 31;
		case 10:
			return true;
		case 11:
			return day < 31;
		default:
			return true;
		}
	}

	/**
	 * Checks if given year is leap year
	 * 
	 * @param year
	 * @return true if year is leap year
	 */
	public boolean isLeap(int year) {
		if (year % 4 != 0) {
			return false;
		} else if (year % 400 == 0) {
			return true;
		} else if (year % 100 == 0) {
			return false;
		} else {
			return true;
		}
	}

	public int calculateDays() {
		//start from month of march if its Jan or Feb 
		if (month <= 2) {
			month = month + 12;
			year = year - 1;
		}
		//365.2424 days in a year, which can be written as 365.0000 + 0.2500 -0.0100 + 0.0025 
		//or represented as fractions 365 + 1/4 -1/100 +1/100
		int totalDays = 365*year + year/4 - year/100 + year/400 + day + (153*month + 8) / 5;

		return totalDays;
	}
}
