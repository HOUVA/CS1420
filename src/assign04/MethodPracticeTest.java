package assign04;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Scanner;

import org.junit.jupiter.api.Test;

class MethodPracticeTest {

	@Test
	void centimetersToInchesTenPointEleven() {
		int result = MethodPractice.centimetersToInches(10.11);
		assertEquals(3, result, "Failed centimetersToInches 10.11 Test");
	}

	@Test
	void centimetersToInchesTwentyPointFive() {
		int result = MethodPractice.centimetersToInches(20.5);
		assertEquals(8, result, "Failed centimetersToInches 20.5 Test");
	}
	
	@Test
	void shiftCipherHello() {
		String result = MethodPractice.shiftCipher("hello", 3);
		assertEquals("khoor", result, "Failed shiftCipher Hello Test");
	}
	
	@Test
	void shiftCipherZest() {
		String result = MethodPractice.shiftCipher("(Zest!)", 15);
		assertEquals("7it#$08", result, "Failed shiftCipher Zest Test");
	}
	
	@Test
	void totalInRangeTwoThroughFiveNormalCase() {
		int result = MethodPractice.totalInRange(new int[] {1,2,3,4,5,6,7,8}, 2, 5);
		assertEquals(12, result, "Failed Normal Case with range 2-5");
	}
	
	@Test
	void totalInRangeFiveThroughTwoInvalidIndex() {
		int result = MethodPractice.totalInRange(new int[] {1,2,3,4,5,6,7,8}, 5, 2);
		assertEquals(0, result, "Failed Normal Case with invalid range");
	}
	
	@Test
	void generateEvenArrayLengthOfFive() {
		int[] result = MethodPractice.generateEvenArray(5);
		assertArrayEquals(new int[]{0,2,4,6,8}, result, "Failed generateEvenArray with length 5");
	}
	
	@Test
	void sumEvenFour() {
		int result = MethodPractice.sumEven(4);
		assertEquals(6, result, "Failed Sum Even - limit is 4");
	}
	
	@Test
	void sumEvenHundred() {
		int result = MethodPractice.sumEven(100);
		assertEquals(2550, result, "Failed Sum Even - limit is 100");
	}
	
	@Test
	void logBaseTwoFiveHundredTwelve() {
		int result = MethodPractice.logBaseTwo(512);
		assertEquals(9, result, "Failed logBaseTwo for 512");
	}
	
	@Test
	void logBaseTwoOne() {
		int result = MethodPractice.logBaseTwo(1);
		assertEquals(0, result, "Failed logBaseTwo for 1");
	}
	
	@Test
	void countPositiveNormalCase() {
		int result = MethodPractice.countPositive(new Scanner("hello 0 10 2.2 string4 0.0"));
	}
}
