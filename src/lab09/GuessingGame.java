package lab09;

import java.util.Random;
import java.util.Scanner;

public class GuessingGame {

	public static void main(String[] args) {
		Random generator = new Random();
		Scanner userInput = new Scanner(System.in);

//		for(int i = 0; i < 100; i++)
//		   System.out.println(generator.nextInt(10));
		
		int randNum = generator.nextInt(100) + 1;
		
		if (randNum >= 1 && randNum <= 100)
			System.out.println("Number is in range");
		else
			System.out.println("Number is not in range");
		
		int guessCounter = 0;
		
		while(guessCounter < 10) {
			int validGuess = getUserGuess(userInput, 1, 100);
			guessCounter++;
			
			if (validGuess == randNum) {
				System.out.println("Your guess is correct");
				break;
			}else if (validGuess > randNum) {
				System.out.println("Your guess is too high, try again");
			}else {
				System.out.println("Your guess is too low, try again");
			}
		}
		
		if (guessCounter == 10)
			System.out.println("You lose, too many guesses");
		
		userInput.close();
		
	}
	
	public static int getUserGuess(Scanner input, int min, int max) {
		
		int inputNum = 0;
		while(true) {
			System.out.print("Enter a number between 1 and 100: ");
			inputNum = input.nextInt();
			if (inputNum >= min && inputNum <= max)
				break;
			else
				System.out.println("Input is out of range");
		}
		return inputNum;

	}

}
