package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the Word class.
 * 
 * @author CS 1420 course staff and UPDATE WITH YOUR NAME
 * @version UPDATE WITH MOST RECENT DATE
 */
public class WordTest {
	// Provided
	@Test
	public void testFirstConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> { new Word("hel!o"); });
	}
	
	// Provided
	@Test
	public void testSecondConstructorException() {
		assertThrows(IllegalArgumentException.class, () -> 
			{ new Word(new char[] { 'W', 'h', 'o', '?' }); });
	}
	
	// Provided
	@Test
	public void testToStringNormal() {
		Word normal = new Word("Normal");
		assertEquals("Normal", normal.toString());
	}
	
	// New
	@Test
	public void testFirstConstructorEmptyString() {
		assertThrows(IllegalArgumentException.class, () -> {new Word(" ");});
	}
	
	//New
	@Test
	public void testSecondConstructorNullArray() {
		char[] charArray = null;
		assertThrows(IllegalArgumentException.class, () -> {new Word(charArray);});
	}
	
	//New
	@Test
	public void testToStringName() {
		Word name = new Word("Matthew");
		assertEquals("Matthew", name.toString());
	}
		
	// Provided
	@Test
	public void testCountOccurrencesOneLetter() {		
		Word oneLetter = new Word("a");
		assertEquals(1, oneLetter.countOccurrences('a'));
	}
	
	// Provided
	@Test
	public void testIsCountOccurrencesMultiple() {
		Word multiplePs = new Word("saippuakivikauppias");
		assertEquals(4, multiplePs.countOccurrences('p'));
	}
	
	
	// TODO: Write at least three more tests for countOccurrences, especially edge cases, exceptions, and how to ensure the method does not change object state.

	// TODO: Uncomment in Step 6 to test replaceLastOccurrence.
	
	// Provided
	@Test
	public void testReplaceLastOccurrenceExceptionFirstArgument() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLastOccurrence(' ', 'l'); });
	}
	
	// Provided
	@Test
	public void testReplaceLastOccurrenceHello() {
		Word hello = new Word("hello");
		hello.replaceLastOccurrence('l', 's');
		assertEquals("helso", hello.toString());
	}
	
	
	// TODO: Write at least three more tests for replaceLastOccurrence, considering cases untested by provided tests.

	// TODO: Uncomment in Step 8 to test reverse.
	
	// Provided
	@Test
	public void testReverseHello() {
		Word hello = new Word("hello");
		assertEquals("olleh", hello.reverse().toString());
	}
	
	// Provided
	@Test
	public void testReverseEmpty() {
		Word empty = new Word("");
		assertEquals("", empty.reverse().toString());
	}
	
	
	// TODO: Write at least three more tests for reverse, considering cases untested by provided tests.
}