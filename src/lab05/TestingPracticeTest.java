package lab05;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class TestingPracticeTest {

	@Test
	void testSubsequenceStringABCDE() {
		String result = TestingPractice.subsequenceString(new char[]{'a', 'b','c', 'd', 'e' }, 1, 3);
		String result2 = TestingPractice.subsequenceString(new char[]{'f', 'g','h', 'i', 'j' }, 1, 3);
		assertEquals("bcd", result2, "subsequence produced incorrect result in normal case");
		assertEquals("ghi", result, "subsequence produced incorrect result in normal case 2");
	}
	

}
