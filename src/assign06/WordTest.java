package assign06;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

/**
 * This class contains unit tests to check the correctness of the Word class.
 * 
 * @author CS 1420 course staff and Matthew Suggars.
 * @version September 30, 2025.
 */
public class WordTest {
	/*
	 * Test cases for Constructors.
	 */
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
	
	// New
	@Test
	public void testFirstConstructorNullString() {
		String nullString = null;
		assertThrows(IllegalArgumentException.class, () -> { new Word(nullString); });
	}
	
	//New
	@Test
	public void testSecondConstructorNullArray() {
		char[] charArray = null;
		assertThrows(IllegalArgumentException.class, () -> {new Word(charArray);});
	}
	
	
	
	/*
	 * Test cases for toString.
	 */
	// Provided
	@Test
	public void testToStringNormal() {
		Word normal = new Word("Normal");
		assertEquals("Normal", normal.toString());
	}
	
	//New
	@Test
	public void testToStringName() {
		Word name = new Word("Matthew");
		assertEquals("Matthew", name.toString());
	}
	
	//New
	@Test
	public void testToStringEmptyString() {
		Word empty = new Word("");
		assertEquals("", empty.toString());
	}
	
	//New
		@Test
		public void testToStringEmptyCharArray() {
			Word empty = new Word(new char[0]);
			assertEquals("", empty.toString());
		}
	
	/*
	 * Test cases for countOccurrences.
	 */
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
	
	//New
	@Test 
	public void testIsCountOccurrencesEmptyLetter() {
		Word hello = new Word("hello");
		assertThrows(IllegalArgumentException.class, () -> { hello.countOccurrences(' '); });
	}
	
	//New
	@Test
	public void testIsCountOccurrencesIncorrectChar() {
		Word hello = new Word("hello");
		assertThrows(IllegalArgumentException.class, () -> { hello.countOccurrences('~'); });
	}
	
	//New 
	@Test
	public void testIsCountOccurrencesNumber() {
		Word hello = new Word("hello"); 
		assertThrows(IllegalArgumentException.class, () -> { hello.countOccurrences('0'); });
	}
	
	/*
	 * Test cases for ReplaceLastOccurrence
	 */
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
	
	// New
	@Test
	public void testReplaceLastOccurrenceExceptionSecondArgument() {
		Word oneLetter = new Word("a");
     	assertThrows(IllegalArgumentException.class, () -> { oneLetter.replaceLastOccurrence('z', ' '); });
	}
	
	// New 
	@Test
	public void testReplaceLastOccurrenceNotFirstOccurrence() {
		Word hello = new Word("hello");
		hello.replaceLastOccurrence('l', 's');
		assertNotEquals("heslo", hello.toString());
	}
	
	// New 
	@Test
	public void testReplaceLastOccurrenceUnchanged() {
		Word hello = new Word("hello");
		hello.replaceLastOccurrence('z', 's');
		assertEquals("hello", hello.toString());
	}	
	
	/*
	 * Test cases for reverse.
	 */
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
	
	// New
	@Test
	public void testReverseDoesNotChangeWord() {
		Word hello = new Word("hello");
		assertNotEquals(hello.toString(), hello.reverse().toString());
	}
	
	// New 
	@Test 
	public void testReverseEvenLetterWord() {
		Word help = new Word("help");
		assertEquals("pleh", help.reverse().toString());
	}
	
	// New
	@Test
	public void testReverseDuplicateLetters() {
		Word comment = new Word("comment");
		assertEquals("tnemmoc", comment.reverse().toString());
	}
	
}