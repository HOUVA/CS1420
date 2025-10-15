package assign06;

public class Word {
	private char[] letters;

	/**
	 * Constructor if String is given. Sets the elements of the letters array to the elements in the String.
	 * 
	 * @param word - String to assign values to letters.
	 */
	public Word(String word) {
		letters = validatedCharArray(word.toCharArray());	
	}

	/**
	 * Constructor if char array is given. Sets the elements of the letters array accordingly.
	 * @param word - char array to assign to letters.
	 */
	public Word(char[] word) {	
		letters = validatedCharArray(word);
	}
	
	/**
	 * Generates and returns a String object to represent this Word object 
	 * (driver method).
	 * 
	 * @return a String object that represents this Word object
	 */
	public String toString() {
	   return toString(0);
	}
		
	/**
	 * Generates and returns a String object to represent the letters of 
	 * this Word object from a given index to the last index (recursive method).
	 * 
	 * @param startIndex - index at which to start
	 * @return the letters of this Word from startIndex to the last index, as a String
	 */
	private String toString(int startIndex) {
	   // base case
	   if(startIndex == letters.length)
	      return "";
	   // recursive case
	   return letters[startIndex] + toString(startIndex + 1);
	}
	
	/**
	 * Returns the number of times the given letter occurs in the word represented by this object.
	 * (driver method).
	 * 
	 * @param letter - must be between A-Z or a-z.
	 * @return the number of occurrences of letter in Word.
	 */
	public int countOccurrences(char letter) {
		validatedChar(letter);
		return countOccurrences(letter, 0);
	}
	
	/**
	 * Returns an integer representing the number of occurrences of the letter in the Word.
	 * (recursive method).
	 * 
	 * @param letter - must be between A-Z or a-z
	 * @param index the index of letters array to check the occurrence
	 * @return the number of occurrences in the entire array.
	 */
	private int countOccurrences(char letter, int index) {
		int counter = 0;
		// base case
		if (index == letters.length)
			return 0;
		else if (letter == letters[index])
			counter = 1;
		// recursive case
		return counter + countOccurrences(letter, index + 1);
	}
	
	/**
	 * Replaces the last occurrence of a letter in Word. If no occurrence exists, Word remains unchanged.
	 * (driver method).
	 * 
	 * @param letter - character to check occurrence of
	 * @param replacement - character to replace the last occurrence of letter in Word.
	 */
	public void replaceLastOccurrence(char letter, char replacement) {
		validatedChar(letter);
		validatedChar(replacement);
		
		replaceLastOccurrence(letter, replacement, letters.length - 1);
	}
	
	/**
	 * Replaces the a letter in Word with another letter. If no occurrence exists, Word remains unchanged.
	 * If the letter occurs more than once in Word, then only the last letter is changed.
	 * (recursive method).
	 * 
	 * @param letter - character to check occurrence. 
	 * @param replacement - character to replace letter in Word
	 * @param index - index of letters to check occurrence of character.
	 */
	private void replaceLastOccurrence(char letter, char replacement, int index) {
		if (index < 0)
			return;
	
		if (letters[index] == letter) {
			letters[index] = replacement;
			return;
		}
		
		replaceLastOccurrence(letter, replacement, index - 1);
	}
	
	/**
	 * Generates a new Word object with the same letters but reversed.
	 * (driver method)
	 * 
	 * @return new Word with letters reversed.
	 */
	public Word reverse() {
		char[] reversedArray = letters;
		Word reversed = new Word(reversedArray);
		

		reversed.reverse(reversedArray, 0);
		return reversed;
	}
	
	private void reverse(char[] reversedWord, int index) {
		if (reversedWord.length == 0)
			return;
		if (index > reversedWord.length / 2)
			return;
		
		char firstChar = reversedWord[index];
		reversedWord[index] = reversedWord[reversedWord.length - 1 - index];
		reversedWord[reversedWord.length - 1 - index] = firstChar;
		
		
		reverse(reversedWord, index + 1);
	}

	/**
	 * Private helper method that validates all characters in the letters array are between A-Z or a-z.
	 * If any characters are not in that range, throws IllegalArgumentException.
	 */
	private char[] validatedCharArray(char[] array) {
		if (array == null)
			throw new IllegalArgumentException("null passed as argument");
		
		char[] validArray = new char[array.length];
		for (int letterIndex = 0; letterIndex < validArray.length; letterIndex++) {
			char letter = array[letterIndex];
			validatedChar(letter);
			validArray[letterIndex] = letter;
		}
		
		return validArray;
		
	}
	
	/**
	 * Private helper method that validates if a letter is between A-Z or a-z.
	 * If letter is not in range, throws IllegalArgumentException.
	 * @param letter
	 */
	private void validatedChar(char letter) {
		if (!((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z')))
			throw new IllegalArgumentException("argument passed is not a letter");
	}
}
