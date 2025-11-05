package assign07;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.*;
import java.util.Scanner;

class CandidateTest {
	private Candidate matthew, angie, marquis;

	@BeforeEach
	public void setup() {
		matthew = new Candidate("Matthew", 26);
		angie = new Candidate("Angie", 24);
		marquis = new Candidate("Marquis", 28);
	}

	@Test
	public void testCandidateWithRatingNegativeAge() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Candidate("Matthew", -1, 10);
		});
	}

	@Test
	public void testCandidateWithRatingOutOfRange() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Candidate("Matthew", 26, 12);
		});
	}

	@Test
	public void testCandidateWithoutRatingNegativeAge() {
		assertThrows(IllegalArgumentException.class, () -> {
			new Candidate("Matthew", -1);
		});
	}

	@Test
	public void testGetRatingOne() {
		assertEquals(1, new Candidate("Matthew", 24).getRating());
	}

	@Test
	public void compareToNameBefore() {
		assertTrue(angie.compareTo(matthew) < 0);
	}

	@Test
	public void compareToNameAfter() {
		assertTrue(matthew.compareTo(angie) > 0);
	}

	@Test
	public void compareToNameEqualAgeBefore() {
		assertTrue(angie.compareTo(new Candidate("Angie", 26)) < 0);
	}

	@Test
	public void compareToNameEqualAgeAfter() {
		assertTrue(marquis.compareTo(new Candidate("Marquis", 26)) > 0);
	}

	@Test
	public void compareToNameEqualAgeEqual() {
		assertEquals(0, marquis.compareTo(new Candidate("Marquis", 28)));
	}

	@Test
	public void equalsDifferentObject() {
		assertFalse(matthew.equals(new Scanner(" ")));
	}

	@Test
	public void equalsSameNameDifferentAge() {
		assertFalse(matthew.equals(new Candidate("Matthew", 27)));
	}

	@Test
	public void equalsSameAgeDifferentName() {
		assertFalse(matthew.equals(new Candidate("Marquis", 26)));
	}

	@Test
	public void equalsDifferentNameDifferentAge() {
		assertFalse(matthew.equals(angie));
	}

	@Test
	public void equalsTrue() {
		assertTrue(matthew.equals(new Candidate("Matthew", 26)));
	}

	@Test
	public void testToStringMatthew() {
		assertEquals("Matthew(26).", matthew.toString());
	}

	@Test
	public void testToStringEmpty() {
		assertEquals("(1).", new Candidate("", 1).toString());
	}

}
