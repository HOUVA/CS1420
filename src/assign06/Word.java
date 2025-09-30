package assign06;

public class Word {

	private char[] letters;

	public Word(String word) {
		letters = word.toCharArray();
		validateCharArray();	
	}

	public Word(char[] word) {
		letters = word;
		validateCharArray();
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
	
	public int countOccurrences(char letter) {
		if (!((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z')))
			throw new IllegalArgumentException("argument passed is not a letter");
		return countOccurrences(letter, 0);
	}
	
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
	 * Validates all characters in the letters array are between A-Z or a-z.
	 * If any characters are not in that range, throws IllegalArgumentException.
	 */
	private void validateCharArray() {
		if (letters == null)
			throw new IllegalArgumentException("null passed as argument");
		
		for (int letterIndex = 0; letterIndex < letters.length; letterIndex++) {
			char letter = letters[letterIndex];
			if((letter >= 'A' && letter <= 'Z') || (letter >= 'a' && letter <= 'z'))
				letters[letterIndex] = letter;
			else
				throw new IllegalArgumentException("Letters must be between A-Z or a-z");
		}
		
	}
}
