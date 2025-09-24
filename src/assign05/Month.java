package assign05;

/**
 * 
 */
public class Month {
	
	private int monthNumber;
	
	public Month() {
		this.monthNumber = 1;
	}
	
	public Month(int monthNumber) {
		this.monthNumber = monthNumber;
	}
	
	public int getMonthNumber() {
		return monthNumber;
	}
	
	public int lastDay(boolean isLeapYear) {
		int lastDayNum = 0;
		switch (monthNumber) {
			case 1:
				lastDayNum = 31;
				break;
			case 2:
				if (isLeapYear)
					lastDayNum = 29;
				else
					lastDayNum =  28;
				break;
			case 3: 
				lastDayNum =  31;
				break;
			case 4:
				lastDayNum =  30;
				break;
			case 5: 
				lastDayNum =  31;
				break;
			case 6:
				lastDayNum =  30;
				break;
			case 7: 
				lastDayNum =  31;
				break;
			case 8: 
				lastDayNum =  31;
				break;
			case 9: 
				lastDayNum =  30;
				break;
			case 10: 
				lastDayNum =  31;
				break;
			case 11: 
				lastDayNum =  30;
				break;
			case 12:
				lastDayNum =  31;
		}
		return lastDayNum;
		
	}
	
	public boolean validDay(int day, boolean isLeapYear) {
		return day <= lastDay(isLeapYear);
	}
	
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
			monthName =  "March";
			break;
		case 4:
			monthName =  "April";
			break;
		case 5: 
			monthName =  "May";
			break;
		case 6:
			monthName =  "June";
			break;
		case 7: 
			monthName =  "July";
			break;
		case 8: 
			monthName =  "August";
			break;
		case 9: 
			monthName =  "September";
			break;
		case 10: 
			monthName =  "October";
			break;
		case 11: 
			monthName =  "November";
			break;
		case 12:
			monthName =  "December";
	}
		
		return monthName;
		
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Month))
			return false;
		Month otherMonth = (Month)other;
		
		return this.monthNumber == otherMonth.monthNumber;
	}

}
