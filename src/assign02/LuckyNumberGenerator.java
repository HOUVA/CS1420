package assign02;
import java.util.Scanner;

/* CS 1420 Accelerated Object-Oriented Programming
 * Assignment 2: Lucky Number Generator
 * @author Matthew Suggars U1067447
 * @version August 29, 2025
 * 
 * The program prompts the user to enter their name, birth month, and birth day 
 * in the console and then prints their lucky number.  
 */

public class LuckyNumberGenerator {

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		int nameSum = 0;
		String name = "", birthMonth = "", birthDay = "", birthMonthName = "";
		
		do{
			/* collects user input for name, birth month, and birth day
			* if user enters "exit" breaks loop */
			System.out.println("Enter a name: ");
			name = input.nextLine();			
			if (name.equals("exit"))
				break;
			
			System.out.println("Enter your birth month (1-12): ");
			birthMonth = input.nextLine();
			if (birthMonth.equals("exit"))
				break;
			
			System.out.println("Enter your birth day (1-31): ");
			birthDay = input.nextLine(); 
			if (birthDay.equals("exit"))
				break;
			
			int birthDayNum = Integer.parseInt(birthDay);
			char firstLetter = name.charAt(0);
			int firstLetterCode = (int)firstLetter;
			
			// checks if first letter in name is a vowel, checks for both upper and lower case
			if (firstLetter == 'a' || firstLetter == 'e' || firstLetter == 'i' ||
				firstLetter == 'o' || firstLetter == 'u' || firstLetter == 'A' || 
				firstLetter == 'E' || firstLetter == 'I' || firstLetter == 'O' || 
				firstLetter == 'U')
				for (int index = 0; index < name.length(); index++)
					nameSum += (int)name.charAt(index);
			// checks if first letter is within range B - L or b - l
			else if ((firstLetterCode >= 66 && firstLetterCode <= 76) || 
					 (firstLetterCode >= 98 && firstLetterCode <= 108)) 
				for (int index = 0; index < name.length(); index += 2)
					nameSum += (int)name.charAt(index);
			// else case for all other letters both upper case and lower case (M-Z or m-z)
			else
				for (int index = 1; index < name.length(); index += 2)
					nameSum += (int)name.charAt(index);
			
			// final calculations to generate lucky number
			int remainder = nameSum % birthDayNum;
			int luckyNumber = remainder + Integer.parseInt(birthMonth);
			
			// assigns a String value to birtMonthName based on user input to use in printf statement on 104
			switch (Integer.parseInt(birthMonth)) {
				case 1:
					birthMonthName = "January";
					break;
				case 2:
					birthMonthName = "February";
					break;
				case 3: 
					birthMonthName = "March";
					break;
				case 4: 
					birthMonthName = "April";
					break;
				case 5: 
					birthMonthName = "May";
					break;
				case 6: 
					birthMonthName = "June";
					break;
				case 7: 
					birthMonthName = "July";
					break;
				case 8: 
					birthMonthName = "August";
					break;
				case 9: 
					birthMonthName = "September";
					break;
				case 10: 
					birthMonthName = "October";
					break;
				case 11: 
					birthMonthName = "November";
					break;
				case 12: 
					birthMonthName = "Decemeber";
			}
			
			// final output showing user input and lucky number
			System.out.printf("For %s born on %s %d, the lucky number is %d.%n", name, birthMonthName, birthDayNum, luckyNumber);
			
		} while (true);
		
		System.out.println("Good luck!");
		input.close();
	}

}
