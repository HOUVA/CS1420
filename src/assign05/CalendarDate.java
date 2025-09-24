package assign05;

/**
 * CS 1420 Accelerated Object-Oriented Programming 
 * Assignment 6: CalendarDate Class
 * 
 * @author Matthew Suggars U1067447
 * @version September 29, 2025
 * 
 * This class represents a Calendar Date. e.g. - June 9, 1999
 * 
 */

public class CalendarDate {
	private Month month;
	private int day;
	private int year;

	/**
	 * Default Constructor if no arguments are given. Sets the date to January 1,
	 * 1000.
	 */
	public CalendarDate() {
		this.month = new Month(1);
		this.day = 1;
		this.year = 1000;
	}

	/**
	 * Constructor for a new Calendar Date. given values for the day, month, and
	 * year.
	 * 
	 * @param monthNumber must be between 1 and 12.
	 * @param day         must be a valid day in the month.
	 * @param year        must be between 1000 - 9999.
	 */
	public CalendarDate(int monthNumber, int day, int year) {
		this.month = new Month(monthNumber);
		this.day = day;
		this.year = year;
	}

	/**
	 * Accesses the Month object of this CalendarDate Object.
	 * 
	 * @return the Month object.
	 */
	public Month getMonth() {
		return this.month;
	}

	/**
	 * Accesses the day of this CalendarDate Object.
	 * 
	 * @return the day of the month.
	 */
	public int getDay() {
		return this.day;
	}

	/**
	 * Accesses the year of this CalendarDate Object.
	 * 
	 * @return the year.
	 */
	public int getYear() {
		return this.year;
	}

	/**
	 * This method checks if the CalendarDate date comes before the given Calendar
	 * Date.
	 * 
	 * @param other - the other Calendar Date to compare to.
	 * @return true if CalendarDate date is before the date provided. Otherwise
	 *         false.
	 */
	public boolean comesBefore(CalendarDate other) {
		boolean beforeValidator = false;

		if (this.year < other.year) {
			beforeValidator = true;
		} else if (this.year == other.year) {
			if (this.month.getMonthNumber() < other.month.getMonthNumber()) {
				beforeValidator = true;
			} else if (this.month.getMonthNumber() == other.month.getMonthNumber()) {
				if (this.day < other.day) {
					beforeValidator = true;
				}
			}
		}

		return beforeValidator;
	}

	/**
	 * This method checks if the CalendarDate date comes after the given Calendar
	 * Date.
	 * 
	 * @param other - the other Calendar Date to compare to.
	 * @return true if CalendarDate date is after the date provided. Otherwise
	 *         false.
	 */
	public boolean comesAfter(CalendarDate other) {
		return !comesBefore(other) && !equals(other);
	}

	/**
	 * This method increases the Calendar Date by one.
	 * 
	 * @return new instance variables for the Calendar Date.
	 */
	public void advanceOneDay() {
		this.day += 1;
		if (!(this.month.validDay(this.day, isLeapYear()))) {
			this.month = new Month(this.month.getMonthNumber() + 1);
			this.day = 1;
			if (this.month.getMonthNumber() > 12) {
				this.year += 1;
				this.month = new Month(1);
			}
		}

	}

	/**
	 * This method provides the CalendarDate as the day of the year.
	 * 
	 * @return day of the year between 1 - 365.
	 */
	public int dayOfYear() {
		int sumOfDays = 0;

		for (int monthIndex = 1; monthIndex < this.month.getMonthNumber(); monthIndex++) {
			Month previousMonth = new Month(monthIndex);
			sumOfDays += previousMonth.lastDay(isLeapYear());
		}

		sumOfDays += this.day;

		return sumOfDays;
	}

	/**
	 * This method checks if the year is a leap year.
	 * 
	 * @return true if the year is a leap year. Otherwise false.
	 */
	public boolean isLeapYear() {
		boolean leapYearValid = false;

		if (this.year % 4 == 0)
			if (this.year % 100 != 0 || this.year % 400 == 0)
				leapYearValid = true;

		return leapYearValid;
	}

	/**
	 * This method returns a text representing the Calendar Date.
	 * 
	 * @return a string containing a text representation.
	 */
	public String toString() {
		return (String) (this.month.toString() + " " + this.day + ", " + this.year);

	}

	/**
	 * This method checks if this CalendarDate and the given object are equal.
	 * 
	 * @param other - the object to compare.
	 * @return true if the other object is the same type and equal. Otherwise false.
	 */
	public boolean equals(Object other) {
		if (!(other instanceof CalendarDate))
			return false;
		CalendarDate otherCalendarDate = (CalendarDate) other;

		return this.year == otherCalendarDate.year
				&& this.month.getMonthNumber() == otherCalendarDate.month.getMonthNumber()
				&& this.day == otherCalendarDate.day;
	}

}
