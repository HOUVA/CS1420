package assign04;

import java.util.Scanner;
import java.util.Arrays;


/** CS 1420 Accelerated Object-Oriented Programming
 *  Assignment 4: Method Practice
 *  @author Matthew Suggars
 *  @version September 16, 2025
 *  
 *  This Class contains 8 methods. main, and seven other methods that accomplish the following tasks: 
 *  - convert centimeters to inches.
 *  - shift the letters in a String by a given amount.
 *  - count the number of positive integers or doubles in a Scanner object.
 *  - calculate the total value of numbers in a range in an Array.
 *  - create an array of even numbers at a certain length.
 *  - calculate the sum of even numbers from 0 to an even number.
 *  - check the log base two of a number.
 */

public class MethodPractice {

	public static void main(String[] args) {
		// Test cases for centimetersToInches
		// Provided
		System.out.printf("Checking centimetersToInches(10.11). Expecting a result of 3." 
				+ " The actual result is %d.%n", centimetersToInches(10.11));
		// Provided
		System.out.printf("Checking centimetersToInches(20.5). Expecting a result of 8."
				+ " The actual result is %d.%n", centimetersToInches(20.5));
		// New - Normal Case
		System.out.printf("Checking centimetersToInches(50.4). Expecting a result of 19."
				+ " The actual result is %d.%n", centimetersToInches(50.4));
		// New - Edge Case
		System.out.printf("Checking centimetersToInches(0). Expecting a result of 0."
				+ " The actual result is %d.%n", centimetersToInches(0));
		// New - Normal Case
		System.out.printf("Checking centimetersToInches(30.2353). Expecting a result of 11."
				+ " The actual result is %d.%n", centimetersToInches(30.2353));
		System.out.println();
		
		// Test cases for shiftCipher
		// Provided
		System.out.printf("Checking shiftCipher(\"hello\", 3). Expecting a result of khoor. "
				+ "The actual result is %s.%n", shiftCipher("hello", 3));
		// Provided
		System.out.printf("Checking shiftCipher(\"(Zest!)\", 15). Expecting a result of 7it#$08. "
				+ "The actual result is %s.%n", shiftCipher("(Zest!)", 15));
		// New - Normal Case
		System.out.printf("Checking shiftCipher(\"Matthew\", 5). Expecting a result of Rfyymj|. "
				+ "The actual result is %s.%n", shiftCipher("Matthew", 5));
		// New - Normal Case
		System.out.printf("Checking shiftCipher(\"{Comp$}\", 50). Expecting a result of NuB@CVP. "
				+ "The actual result is %s.%n", shiftCipher("{Comp$}", 50));
		// New - Edge Case
		System.out.printf("Checking shiftCipher(\" \", 2). Expecting a result of \". "
				+ "The actual result is %s.%n", shiftCipher(" ", 2));
		System.out.println();
		
		// Test cases for countPositive
		// Provided
		System.out.printf("Checking countPositive(new Scanner(\"hello 0 10 2.2 string4 0.0\"). "
				+ "Expecting a result of 2. The actual result is %d.%n",
				countPositive(new Scanner("hello 0 10 2.2 string4 0.0")));
		// New - Normal Case
		System.out.printf("Checking countPositive(new Scanner(\"computer 5 20 0 a 0.1\"). "
				+ "Expecting a result of 3. The actual result is %d.%n",
				countPositive(new Scanner("computer 5 20 0 a 0.1")));
		// New - Normal Case
		System.out.printf("Checking countPositive(new Scanner(\"25 1 44444 98.343523 true false yes\"). "
				+ "Expecting a result of 4. The actual result is %d.%n",
				countPositive(new Scanner("25 1 44444 98.343523 true false yes")));
		// New - Edge Case
		System.out.printf("Checking countPositive(new Scanner(\" \"). "
				+ "Expecting a result of 0. The actual result is %d.%n",
				countPositive(new Scanner(" ")));
		System.out.println();		
		
		// Test cases for totalInRange
		// Provided
		System.out.printf("Checking totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 5)."
				+ " Expecting a result of 12. The actual result is %d.%n", 
				totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 2, 5));
		// Provided
		System.out.printf("Checking totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 5, 2)."
				+ " Expecting a result of 0. The actual result is %d.%n", 
				totalInRange(new int[]{1, 2, 3, 4, 5, 6, 7, 8}, 5, 2));
		// New - Normal Case
		System.out.printf("Checking totalInRange(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 1, 4)."
				+ " Expecting a result of 18. The actual result is %d.%n", 
				totalInRange(new int[]{8, 7, 6, 5, 4, 3, 2, 1}, 1, 4));
		// New - Normal Case
		System.out.printf("Checking totalInRange(new int[]{12, 50, 0, 7, 69, 5, 9, 100}, 2, 9)."
				+ " Expecting a result of 0. The actual result is %d.%n", 
				totalInRange(new int[]{12, 50, 0, 7, 69, 5, 9, 100}, 2, 9));
		// New - Edge Case
		System.out.printf("Checking totalInRange(new int[]{12, 50, 0, 7, 69, 5, 9, 100}, 5, 5)."
				+ " Expecting a result of 0. The actual result is %d.%n", 
				totalInRange(new int[]{12, 50, 0, 7, 69, 5, 9, 100}, 5, 5));
		System.out.println();
		
		// Test cases for generateEvenArray
		// Provided
		System.out.printf("Checking generateEvenArray(5). Expecting a result of [0, 2, 4, 6, 8]. "
				+ "The actual result is %s.%n", Arrays.toString(generateEvenArray(5)));
		// New - Edge Case
		System.out.printf("Checking generateEvenArray(0). Expecting a result of []. "
				+ "The actual result is %s.%n", Arrays.toString(generateEvenArray(0)));
		// New - Normal Case
		System.out.printf("Checking generateEvenArray(1). Expecting a result of [0]. "
				+ "The actual result is %s.%n", Arrays.toString(generateEvenArray(1)));
		// New - Normal Case
		System.out.printf("Checking generateEvenArray(10). Expecting a result of "
				+ "[0, 2, 4, 6, 8, 10, 12, 14, 16, 18].The actual result is %s.%n", 
				Arrays.toString(generateEvenArray(10)));
		System.out.println();
		
		//Test cases for sumEven
		// Provided
		System.out.printf("Checking sumEven(4). Expecting a result of 6. The actual result is %d.%n", sumEven(4));
		// Provided
		System.out.printf("Checking sumEven(100). Expecting a result of 2550. The actual result is %d.%n", sumEven(100));
		// New - Normal Case
		System.out.printf("Checking sumEven(2). Expecting a result of 2. The actual result is %d.%n", sumEven(2));
		// New - Normal Case
		System.out.printf("Checking sumEven(50). Expecting a result of 650. The actual result is %d.%n", sumEven(50));
		// New - Edge Case
		System.out.printf("Checking sumEven(0). Expecting a result of 0. The actual result is %d.%n", sumEven(0));
		System.out.println();
		
		//Test cases for logBaseTwo
		// Provided
		System.out.printf("Checking logBaseTwo(512). Expecting a result of 9. The actual result is %d.%n", logBaseTwo(512));
		// Provided
		System.out.printf("Checking logBaseTwo(1). Expecting a result of 0. The actual result is %d.%n", logBaseTwo(1));
		// New - Normal Case
		System.out.printf("Checking logBaseTwo(40). Expecting a result of 5. The actual result is %d.%n", logBaseTwo(40));
		// New - Normal Case
		System.out.printf("Checking logBaseTwo(69). Expecting a result of 6. The actual result is %d.%n", logBaseTwo(69));
		// New - Edge Case
		System.out.printf("Checking logBaseTwo(0). Expecting a result of 0. The actual result is %d.%n", logBaseTwo(0));
	
		
	}
	
	/**
	 * Takes a positive-value length of centimeters as a double, returns an integer
	 * of the equivalent value in inches, rounded down to the nearest inch.
	 * 
	 * @param amount in centimeters.
	 * @return amount converted to inches. 
	 */
	public static int centimetersToInches(double cmAmount) {
		return (int)(cmAmount / 2.54);
	}
	
	/**
	 * Takes a String of letters and returns the String with each letter shifted by the amount passed.
	 * 
	 * @param String message, can be any String.
	 * @param amount to shift each char in String, must be non-negative.
	 * @return new String with all letters shifted by shiftAmount.
	 */
	public static String shiftCipher(String message, int shiftAmount) {
		char[] messageArray = new char[message.length()];
		
		for (int charIndex = 0; charIndex < message.length(); charIndex++) {
			if (message.charAt(charIndex) + shiftAmount < 32) {
				messageArray[charIndex] = (char)(message.charAt(charIndex) + shiftAmount + 32);
			} else if(message.charAt(charIndex) + shiftAmount > 126) {
				messageArray[charIndex] = (char)(message.charAt(charIndex) + shiftAmount - 95);
			}else {
				messageArray[charIndex] = (char)(message.charAt(charIndex) + shiftAmount);
			}
		}
			
		String newMessage = new String(messageArray);
		return newMessage;
	}
	
	
	/**
	 * Takes a Scanner object containing an unknown amount of values of different data types.
	 * Then returns the amount of positive numbers in the String.
	 * 
	 * @param a Scanner object of any size.
	 * @return an integer representing the number of positive values in the Scanner object.
	 */
	public static int countPositive(Scanner input) {
		int positiveCounter = 0;
		while (input.hasNext()) {
			if (input.hasNextDouble()) {
				double numberDouble = input.nextDouble();
				if (numberDouble > 0.0) {
					positiveCounter++;
				}
			}else if (input.hasNextInt()) {
				int numberInt = input.nextInt();
				if (numberInt > 0) positiveCounter++;
			}else {
				String excess = input.next(); // used to collect any non int or double tokens.
			}				
		}
		return positiveCounter;
		
	}
	
	/**
	 * Takes an array of numbers, and two integers representing the inclusive start of
	 * the range and the exclusive end of range. 
	 * Returns the sum of all numbers in the range.
	 * 
	 * Range is invalid when:
	 * - end of range is larger than beginning
	 * - range contains no elements. Example: (0,0).
	 * 
	 * @param list of numbers
	 * @param beginning index
	 * @param ending index
	 * @return 0 if range is invalid, otherwise sum of all numbers in range.
	 */
	public static int totalInRange(int[] numbersList, int begin, int end) {
		int totalValue = 0;
		
		if (end < begin || end == begin)
			return 0;
		else if (begin > numbersList.length - 1 || end - 1 > numbersList.length - 1) 
			return 0;
		else 
			for (int index = begin; index < end; index++) 
				totalValue += numbersList[index];
	
		return totalValue;
	}
	
	/**
	 * Takes an integer representing the length of desired array, then returns 
	 * an array of given length, in which all values are even.
	 * 
	 * @param length of array, must be non-negative.
	 * @return array with only even elements.
	 */
	public static int[] generateEvenArray(int length) {
		int[] evenArray = new int[length];
		for (int evenIndex = 0; evenIndex < length; evenIndex++)
			evenArray[evenIndex] = evenIndex * 2;
		return evenArray;
	}
	
	/**
	 * Takes an positive integer, then sums all the even numbers from 0 to limit.
	 * 
	 * @param last number to add.
	 * @return final sum from 0 to limit as an integer.
	 */
	public static int sumEven(int limit) {
		int[] evenArray = generateEvenArray(limit);
		int limitIndex = 0;
		
		for (int index = 0; index < evenArray.length; index++)
			if (evenArray[index] == limit)
				limitIndex = index + 1;
		
		return totalInRange(evenArray, 0, limitIndex);
	}
	
	/**
	 * Takes a positive number, and returns the exponent 2 must be raised to reach it.
	 * if number = 1, then returns 0.
	 * if number is odd, returns closest exponent value without going over number.
	 * 
	 * @param number to check log base 2.
	 * @return exponent 2 must be raised to reach number.
	 */
	public static int logBaseTwo(int number) {
		int quotientCounter = 0;
		
		if (number < 2) 
			return 0;
		
		while(number > 1) {
			number /= 2;			
			quotientCounter++;
		}
		
		return quotientCounter;
	}

}