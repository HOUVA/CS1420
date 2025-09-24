package assign05;

/**
 * 
 */

public class CalendarDate {
	private Month month;
	private int day;
	private int year;
	
	public CalendarDate() {
		this.month = new Month(1);
		this.day = 1;
		this.year = 1000;
	}
	
	public CalendarDate(int monthNumber, int day, int year) {
		this.month = new Month(monthNumber);
		this.day = day;
		this.year = year;
	}
	
	public Month getMonth() {
		return this.month;
	}
	
	public int getDay() {
		return this.day;
	}
	
	public int getYear() {
		return this.year;
	}
	
	public boolean comesBefore(CalendarDate other) {
		boolean beforeValidator = false;
		
		if (this.year < other.year) {
			beforeValidator = true;
		}else if (this.year == other.year) {
			if (this.month.getMonthNumber() < other.month.getMonthNumber()) {
				beforeValidator = true;
			}else if (this.month.getMonthNumber() == other.month.getMonthNumber()) {
				if (this.day < other.day) {
					beforeValidator = true;
				}
			}
		}
		
		return beforeValidator;
	}
	
	public boolean comesAfter(CalendarDate other) {
		return !comesBefore(other) && !equals(other);
	}
	
	public void advanceOneDay() {
		this.day += 1;
		if (!(this.month.validDay(this.day, isLeapYear()))){
			this.month = new Month(this.month.getMonthNumber() + 1);
			this.day = 1;
			if (this.month.getMonthNumber() > 12) {
				this.year += 1;
				this.month = new Month(1);
			}
		}
		
	}
	
	public int dayOfYear() {
		int sumOfDays = 0;
		
		for (int monthIndex = 1; monthIndex < this.month.getMonthNumber(); monthIndex++) {
			Month previousMonth = new Month(monthIndex);
			sumOfDays += previousMonth.lastDay(isLeapYear());
		}
		
		sumOfDays += this.day;
	
		return sumOfDays;
	}
	
	public boolean isLeapYear() {
		boolean leapYearValid = false;
		
		if (this.year % 4 == 0)
			if (this.year % 100 != 0 || this.year % 400 == 0)
				leapYearValid = true;
		
		return leapYearValid;
	}
	
	public String toString() {
		return (String)(this.month.toString() + " " + this.day + ", " + this.year);
		
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof CalendarDate))
			return false;
		CalendarDate otherCalendarDate = (CalendarDate)other;
		
		return this.year == otherCalendarDate.year && this.month.getMonthNumber() 
				== otherCalendarDate.month.getMonthNumber() && this.day == otherCalendarDate.day;
	}
	
	

}
