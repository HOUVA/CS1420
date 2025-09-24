package lab04;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class SpellCheck {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		File myFile = new File("src/lab04/words.txt");
		//System.out.println(countWords(myFile));
		
		while (true) {
			Scanner input = new Scanner(System.in);
			System.out.print("Enter a word: ");
			String userWord = input.next();
			
			if (getClosestWord(userWord, myFile).equals(userWord)) {
				System.out.println("input is correct");
			}
			else
				System.out.println("false, closest word is: " + getClosestWord(userWord, myFile));

		}
		
	}
	
	/**
	 * Given a filename, this method returns a count of the number of
	 * words in the file. If the file cannot be opened, -1 is returned.
	 * 
	 * @param the name with path of a text file
	 * @return the count of words in the file or -1
	 */
	public static int countWords(File file) {
	    //TODO
		Scanner fileInput;
		try {
			fileInput = new Scanner(file);
		}catch(FileNotFoundException e) {
			return -1;
		}
		
		int wordCount = 0;
		
		while (fileInput.hasNext()) {
			wordCount++;
			fileInput.next();

		}
		
		fileInput.close();
		return wordCount;
	}
	
	public static String getClosestWord(String word, File file) {
		Scanner fileInput;
		try {
			fileInput = new Scanner(file);
		}catch(FileNotFoundException e) {
			return null;
		}
		
		String current = null;
		while (fileInput.hasNext()) {
			current = fileInput.next();
			if(word.compareTo(current) <= 0)
				break;
		}
		
		fileInput.close();
		return current;	
	}
	
	

}
