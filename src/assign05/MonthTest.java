package assign05;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the Month class.
 * 
 * @author CS 1420 course staff and Matthew Suggars.
 * @version September 23, 2025.
 */
public class MonthTest {
	@Test
	public void testNoParameterConstructor() {
		Month jan = new Month();
		assertEquals(1, jan.getMonthNumber(), "No-parameter constructor does not set month number to 1");
	}
	
	@Test
	public void marchConstructor() {
		Month mar = new Month(3);
		assertEquals(3, mar.getMonthNumber(), "Constructor does not set month number to 3");
	}
	
	
	@Test
	public void testToString() {
		Month oct = new Month(10);
		assertEquals("October", oct.toString(), "toString does not return correct String -- check for typos");
	}
	
	@Test
	public void testLastDayJanuary() {
		Month jan = new Month(1);
		assertEquals(31, jan.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayFebruaryLeapYear() {
		Month feb = new Month(2);
		assertEquals(29, feb.lastDay(true), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayFebruaryNotLeapYear() {
		Month feb = new Month(2);
		assertEquals(28, feb.lastDay(false), "Last day of April is incorrect");
	}
	@Test
	public void testLastDayMarch() {
		Month mar = new Month(3);
		assertEquals(31, mar.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayApril() {
		Month apr = new Month(4);
		assertEquals(30, apr.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayMay() {
		Month may = new Month(5);
		assertEquals(31, may.lastDay(false), "Last day of May is incorrect");
	}
	
	@Test
	public void testLastDayJune() {
		Month jun = new Month(2);
		assertEquals(30, jun.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayJuly() {
		Month jul = new Month(7);
		assertEquals(31, jul.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayAugust() {
		Month aug = new Month(8);
		assertEquals(31, aug.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDaySeptember() {
		Month sep = new Month(9);
		assertEquals(30, sep.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayOctober() {
		Month oct = new Month(10);
		assertEquals(31, oct.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayNovember() {
		Month nov = new Month(11);
		assertEquals(30, nov.lastDay(false), "Last day of April is incorrect");
	}
	
	@Test
	public void testLastDayDecember() {
		Month dec = new Month(12);
		assertEquals(31, dec.lastDay(false), "Last day of May is incorrect");
	}
	
	@Test
	public void testValidDayNormal() {
		Month jun = new Month(6);
		assertTrue(jun.validDay(10, false), "validDay incorrect for valid day");
	}
	
	@Test
	public void testValidDayFebruaryLeapYear() {
		Month feb = new Month(2);
		assertTrue(feb.validDay(29, true), "validDay incorrect for leap year February");
	}
	
	@Test
	public void testValidDayFebruaryNotLeapYear() {
		Month feb = new Month(2);
		assertFalse(feb.validDay(29, false), "validDay incorrect for normal year February");
	}
	
	@Test
	public void testEqualsFalse() {
		Month sep = new Month(9);
		assertFalse(sep.equals(new Month(8)),
				"equals method does not return false when passed Month object that is the different");
	}
	
	@Test
	public void testEqualsTrue() {
		Month sep = new Month(9);
		assertTrue(sep.equals(new Month(9)),
				"equals method does not return true when passed Month object that is the same");
	}
	
	@Test
	public void testEqualsNonMonth() {
		Month mar = new Month(3);
		assertFalse(mar.equals(new Scanner(System.in)),
				"equals method does not return false when passed a non-Month object");
	}
}