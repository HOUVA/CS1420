package assign05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import java.util.Scanner;

/**
 * This class contains unit tests to check the correctness of the CalendarDate class.
 * 
 * @author CS 1420 course staff and Matthew Suggars
 * @version September 23, 2025.
 */
public class CalendarDateTest {
		
	/*
	 * Test Cases for Constructors
	 */
	@Test
	public void testNoParameterConstructor() {
		CalendarDate date = new CalendarDate();		
		assertEquals(1, date.getDay(), "No-parameter constructor does not set day number to 1");
		assertEquals(1, date.getMonth().getMonthNumber(), "No-parameter constructor does not set month number to 1");
		assertEquals(1000, date.getYear(), "No-parameter constructor does not set year number to 1000");
	}
	
	@Test
	public void finalExamConstructor() {
		CalendarDate date = new CalendarDate(12, 8, 2025);
		assertEquals(8, date.getDay(), "Constructor does not set day number to 8");
		assertEquals(12, date.getMonth().getMonthNumber(), "Constructor does not set month number to 12");
		assertEquals(2025, date.getYear(), "Constructor does not set year number to 2025");
	}
	
	/*
	 * Test cases for getDay, getYear, and getMonth
	 */
	@Test
	public void testGetDay() {
		CalendarDate date = new CalendarDate(3, 15, 1950);
		assertEquals(15, date.getDay(), "getDay method incorrect");
	}
	
	@Test 
	public void testGetYear() {
		CalendarDate date = new CalendarDate(3,15,1950);
		assertEquals(1950, date.getYear(), "getYear method is incorrect");
	}
	
	@Test 
	public void testGetMonth() {
		CalendarDate date = new CalendarDate(3,15,1950);
		assertEquals(3, date.getMonth().getMonthNumber(), "getMonth method is incorrect");
	}
	
	/*
	 * Test cases for toString
	 */
	@Test
	public void testToStringNormalCase() {
		CalendarDate date = new CalendarDate(8, 1, 1970);
		assertEquals("August 1, 1970", date.toString(),
				"toString does not return correct String -- check for typos");
	}
	
	@Test
	public void testToStringDefaultConstructor() {
		CalendarDate date = new CalendarDate();
		assertEquals("January 1, 1000", date.toString(),
				"toString does not return correct String -- check for typos");
	}
	
	@Test
	public void testToStringBirthday() {
		CalendarDate date = new CalendarDate(6, 9, 1999);
		assertEquals("June 9, 1999", date.toString(),
				"toString does not return correct String -- check for typos");
	}
	
	/*
	 * Test cases for comesBefore
	 */
	@Test
	public void testComesBeforeDifferentMonthTrue() {
		CalendarDate date = new CalendarDate(5, 19, 1985);
		assertTrue(date.comesBefore(new CalendarDate(6, 9, 1985)),
				"comesBefore does not return true when date on which method is called comes before argument");
	}
	
	@Test
	public void testComesBeforeDifferentMonthFalse() {
		CalendarDate date = new CalendarDate(5, 19, 1985);
		assertFalse(date.comesBefore(new CalendarDate(4, 9, 1985)),
				"comesBefore does not return false when date on which method is called comes before argument");
	}
	
	@Test
	public void testComesBeforeDifferentDayTrue() {
		CalendarDate date = new CalendarDate(5, 19,1985);
		assertTrue(date.comesBefore(new CalendarDate(5, 20, 1985)),
				"comesBefore does not return true when date on which method is called comes after argument");
	}
	
	@Test 
	public void testComesBeforeDifferentDayFalse() {
		CalendarDate date = new CalendarDate(5, 19,1985);
		assertFalse(date.comesBefore(new CalendarDate(5, 18, 1985)),
				"comesBefore does not return false when date on which method is called comes after argument");
	}
	
	@Test
	public void testComesBeforeDifferentYearTrue() {
		CalendarDate date = new CalendarDate(5, 19,1985);
		CalendarDate otherDate = new CalendarDate(5, 19,1986);
		assertTrue(date.comesBefore(otherDate),
				"comesBefore does not return true when date on which method is called comes before argument");
	}
	
	@Test
	public void testComesBeforeDifferentYearFalse() {
		CalendarDate date = new CalendarDate(5, 19,1985);
		CalendarDate otherDate = new CalendarDate(5, 19,1984);
		assertFalse(date.comesBefore(otherDate),
				"comesBefore does not return false when date on which method is called comes before argument");
	}
	
	@Test
	public void testComesBeforeSameDate() {
		CalendarDate date = new CalendarDate(5, 19,1985);
		assertFalse(date.comesBefore(date),
				"comesBefore does not return false when dates are same");
	}
	
	/*
	 * Test cases for comesAfter
	 */
	@Test
	public void testComesAfterSameDate() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertFalse(date.comesAfter(new CalendarDate(2, 7, 1888)),
				"comesAfter does not return false when date on which method is called is the same as argument");
	}
	
	@Test
	public void testComesAfterDifferentDayTrue() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertTrue(date.comesAfter(new CalendarDate(2, 6, 1888)),
				"comesAfter does not return true when date on which method is called comes after argument");
	}
	
	@Test
	public void testComesAfterDifferentDayFalse() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertFalse(date.comesAfter(new CalendarDate(2, 8, 1888)),
				"comesAfter does not return false when date on which method is called does not come after argument");
	}
	
	@Test
	public void testComesAfterDifferentMonthTrue() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertTrue(date.comesAfter(new CalendarDate(1, 7, 1888)),
				"comesAfter does not return true when date on which method is called comes after argument");
	}
	
	@Test
	public void testComesAfterDifferentMonthFalse() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertFalse(date.comesAfter(new CalendarDate(3, 7, 1888)),
				"comesAfter does not return false when date on which method is called does not come after argument");
	}
	
	@Test
	public void testComesAfterDifferentYearTrue() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertTrue(date.comesAfter(new CalendarDate(2, 7, 1887)),
				"comesAfter does not return true when date on which method is called comes after argument");
	}
	
	@Test
	public void testComesAfterDifferentYearFalse() {
		CalendarDate date = new CalendarDate(2, 7, 1888);
		assertFalse(date.comesAfter(new CalendarDate(3, 7, 1889)),
				"comesAfter does not return false when date on which method is called does not come after argument");
	}
	
	/*
	 * Test cases for advanceOneDay
	 */
	@Test
	public void testAdvanceOneDay() {
		CalendarDate date = new CalendarDate(3, 29, 1200);
		date.advanceOneDay();
		assertEquals(3, date.getMonth().getMonthNumber(),
				"advanceOneDay changed month when not at end of month");
		assertEquals(30, date.getDay(),
				"advanceOneDay does not add 1 to day when not add end ");
		assertEquals(1200, date.getYear(),
				"advanceOneDay changed year when at the end of the month (not December)");
	}
	
	@Test
	public void testAdvanceOneDayEndOfMonth() {
		CalendarDate date = new CalendarDate(4, 30, 1200);
		date.advanceOneDay();
		assertEquals(5, date.getMonth().getMonthNumber(),
				"advanceOneDay does not add 1 to month when at the end of the month");
		assertEquals(1, date.getDay(),
				"advanceOneDay does set day to 1 when at the end of the month");
		assertEquals(1200, date.getYear(),
				"advanceOneDay changed year when at the end of the month (not December)");
	}
	
	@Test
	public void testAdvanceOneDayEndOfYear() {
		CalendarDate date = new CalendarDate(12, 31, 1200);
		date.advanceOneDay();
		assertEquals(1, date.getMonth().getMonthNumber(),
				"advanceOneDay does not set month to 1 when at the end of the year");
		assertEquals(1, date.getDay(),
				"advanceOneDay does set day to 1 when at the end of the year");
		assertEquals(1201, date.getYear(),
				"advanceOneDay does not add 1 to year when at the end of the year");
	}
	
	/*
	 * Test Cases for DayOfYear
	 */
	@Test
	public void testDayOfYearFirst() {
		CalendarDate date = new CalendarDate(1, 1, 3000);
		assertEquals(1, date.dayOfYear(), "dayOfYear does not return 1 for the first day of a year");
	}
	
	@Test
	public void testDayOfYearNormalCase() {
		CalendarDate date = new CalendarDate(1, 2, 3000);
		assertEquals(2, date.dayOfYear(), "dayOfYear does not return 2 for the January 2nd");
	}
	
	@Test
	public void testDayOfYearLastDayOfYear() {
		CalendarDate date = new CalendarDate(12, 31, 3000);
		assertEquals(365, date.dayOfYear(), "dayOfYear does not return 365 for the first day of a year");
	}
	
	@Test
	public void testDayOfYearLastDayOfYearLeapYear() {
		CalendarDate date = new CalendarDate(12, 31, 2000);
		assertEquals(366, date.dayOfYear(), "dayOfYear does not return 366 for the first day of a leap year");
	}
	
	@Test
	public void testDayOfYearBirthday() {
		CalendarDate date = new CalendarDate(6, 9, 1999);
		assertEquals(160, date.dayOfYear(), "dayOfYear does not return 160 for the birthday of a year");
	}
	
	@Test
	public void testDayOfYearBirthdayLeapYear() {
		CalendarDate date = new CalendarDate(6, 9, 2000);
		assertEquals(161, date.dayOfYear(), "dayOfYear does not return 161 for the birthday of a leap year");
	}
	
	/*
	 * Test cases for isLeapYear
	 * TODO test more cases
	 */
	@Test
	public void testIsLeapYearTrue() {
		CalendarDate date = new CalendarDate(1, 1, 2004);
		assertTrue(date.isLeapYear(), "isLeapYear does not return true for year divisible by 4 but not 100");
	}
	
	@Test
	public void testIsLeapYearFalse() {
		CalendarDate date = new CalendarDate(1, 1, 2005);
		assertFalse(date.isLeapYear(), "isLeapYear does not return false for year not divisible by 4");
	}
	
	@Test
	public void testIsLeapYearTrueTwoThousand() {
		CalendarDate date = new CalendarDate(1, 1, 2000);
		assertTrue(date.isLeapYear(), "isLeapYear does not return true for year divisible by 4 and 100 and 400");
	}
	
	@Test
	public void testIsLeapYearFalseEighteenHundree() {
		CalendarDate date = new CalendarDate(1, 1, 1800);
		assertFalse(date.isLeapYear(), "isLeapYear does not return true for year divisible by 4 and 100 but not 400");
	}
	
	/*
	 * Test cases for equals
	 */
	@Test
	public void testEqualsTrueSameDate() {
		CalendarDate date = new CalendarDate(10, 10, 3333);
		assertTrue(date.equals(new CalendarDate(10, 10, 3333)),
				"equals method does not return true for same dates");
	}
	
	@Test
	public void testEqualsFalseDifferentDates() {
		CalendarDate date = new CalendarDate(10, 10, 3333);
		assertFalse(date.equals(new CalendarDate(10, 10, 3334)),
				"equals method does not return false for different dates");
	}
	
	@Test
	public void testEqualsFalseDifferentObjectMonth() {
		CalendarDate date = new CalendarDate(10, 10, 3333);
		assertFalse(date.equals(new Month(3)),
				"equals method does not return false for different Object type");
	}
	
	@Test
	public void testEqualsFalseDifferentObjectScanner() {
		CalendarDate date = new CalendarDate(10, 10, 3333);
		assertFalse(date.equals(new Scanner(System.in)),
				"equals method does not return false for different Object type");
	}
}