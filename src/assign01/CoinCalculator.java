package assign01;

/*  CS 1420 Accelerated Object-Oriented Programming
 *  Assignment 1: Coin Calculator
 *  @author Matthew Suggars u1067447
 *  @version August 22, 2025 
 *  
 *  This program uses my UID as a total number of pennies and then calculates
 *	the equivalent number of dollars, quarters, dimes, nickels, and pennies.
 */

public class CoinCalculator {

	public static void main(String[] args) {
		int uid = 1067447;
		int uid2 = 0065256;
		
		calculateModulus(uid);
		calculateCoins(uid2);
	}
	
	public static void calculateModulus(int amount) {
		int pennies = amount;
		int dollars = pennies / 100;
		pennies = pennies % 100;
		int quarters = pennies / 25;
		pennies = pennies % 25;
		System.out.printf("%d pennies is equivalent to %d dollar(s), %d quarter(s), and %d penny(ies).%n", 
				amount, dollars, quarters, pennies);
	}

	public static void calculateCoins(int amount) {
		int pennies = amount;
		int dollars, quarters;

		dollars = pennies / 100;
		pennies = pennies - dollars * 100;
		quarters = pennies / 25;
		pennies = pennies - quarters * 25;
	
		
		

		System.out.printf("%d pennies is equivalent to %d dollar(s), %d quarter(s), and %d penny(ies).%n", 
				amount, dollars, quarters, pennies);
	}
}
