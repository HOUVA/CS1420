package assign05;

/**
 * CS 1420 Accelerated Object-Oriented Programming Assignment 6: Month Class
 *
 * @author Matthew Suggars U1067447
 * @version September 29, 2025
 *
 *          This class represents a Month in the Calendar Year.
 *
 */
public class Month {
	private int monthNumber;

	/**
	 * Default constructor if no argument is given. Sets the month to 1 or
	 * "January."
	 */
	public Month() {
		this.monthNumber = 1;
	}

	/**
	 * Constructor for a new Month object, given a number.
	 *
	 * @param monthNumber - value for month in the calendar year, must be between 1
	 *                    and 12
	 */
	public Month(int monthNumber) {
		this.monthNumber = monthNumber;
	}

	/**
	 * Accesses the number of the Month.
	 *
	 * @return the number of the month
	 */
	public int getMonthNumber() {
		return monthNumber;
	}

	/**
	 * This method returns the last day of the month.
	 *
	 * @param isLeapYear - boolean value if the year as an integer is a leap year
	 * @return the last day of the month
	 */
	public int lastDay(boolean isLeapYear) {
		int lastDayNum = 0;

		switch (monthNumber) {
		case 1:
			lastDayNum = 31;
			break;
		case 2:
			if (isLeapYear) {
				lastDayNum = 29;
			} else {
				lastDayNum = 28;
			}
			break;
		case 3:
			lastDayNum = 31;
			break;
		case 4:
			lastDayNum = 30;
			break;
		case 5:
			lastDayNum = 31;
			break;
		case 6:
			lastDayNum = 30;
			break;
		case 7:
			lastDayNum = 31;
			break;
		case 8:
			lastDayNum = 31;
			break;
		case 9:
			lastDayNum = 30;
			break;
		case 10:
			lastDayNum = 31;
			break;
		case 11:
			lastDayNum = 30;
			break;
		case 12:
			lastDayNum = 31;
		}
		return lastDayNum;
	}

	/**
	 * This method checks if the day given exists in the month.
	 *
	 * @param day        - of the month
	 * @param isLeapYear - boolean value if the year as an integer is a leap year
	 * @return true if the day exists in the month. Otherwise false.
	 */
	public boolean validDay(int day, boolean isLeapYear) {
		return day > 0 && day <= lastDay(isLeapYear);
	}

	/**
	 * This method represents the month as a name.
	 *
	 * @return a string name for the month.
	 */
	@Override
	public String toString() {
		String monthName = null;

		switch (monthNumber) {
		case 1:
			monthName = "January";
			break;
		case 2:
			monthName = "February";
			break;
		case 3:
			monthName = "March";
			break;
		case 4:
			monthName = "April";
			break;
		case 5:
			monthName = "May";
			break;
		case 6:
			monthName = "June";
			break;
		case 7:
			monthName = "July";
			break;
		case 8:
			monthName = "August";
			break;
		case 9:
			monthName = "September";
			break;
		case 10:
			monthName = "October";
			break;
		case 11:
			monthName = "November";
			break;
		case 12:
			monthName = "December";
		}

		return monthName;

	}

	/**
	 * This method determines if the other object is both another Month object and
	 * is equal to the Month object
	 *
	 * @return true if both objects are the same type and value, otherwise false.
	 */
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof Month)) {
			return false;
		}
		Month otherMonth = (Month) other;

		return this.monthNumber == otherMonth.monthNumber;
	}

}
