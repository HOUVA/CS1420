package assign05;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the CalendarDateDemo class.
 *
 * @author Matthew Suggars U1067447.
 * @version September 23, 2025.
 */
class CalendarDateDemoTest {

	@Test
	public void testTwoDates() {
		CalendarDate[] array = new CalendarDate[2];
		array[0] = new CalendarDate(12, 1, 2001);
		array[1] = new CalendarDate(12, 3, 2001);
		assertEquals(2, CalendarDateDemo.countDatesBefore(array, new CalendarDate(1, 1, 2002)), "Failed when two dates come before target date");

	}

	@Test
	public void testZeroDates() {
		CalendarDate[] array = new CalendarDate[2];
		array[0] = new CalendarDate(12, 1, 2025);
		array[1] = new CalendarDate(12, 3, 2025);
		assertEquals(0, CalendarDateDemo.countDatesBefore(array, new CalendarDate(1, 1, 2002)), "Failed when zero dates come before target date");
	}


}
