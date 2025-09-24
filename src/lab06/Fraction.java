package lab06;

/**
 * This class represents a fraction; e.g., 1/2.
 * 
 * @author CS 1420 course staff and Matthew Suggars
 * @version September 23, 2025
 */
public class Fraction {
	private int numerator;
	private int denominator;
	
	/**
	 * Builds a "default" Fraction object 0/1.
	 */
	public Fraction() {
		this.numerator = 0;
		this.denominator = 1;
	}
	
	/**
	 * Builds a Fraction object, given a numerator and denominator.
	 * 
	 * @param numerator - value for initializing the numerator
	 * @param denominator - value for initializing the denominator
	 */
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
	}
	
	public Fraction(int numerator) {
		this.numerator = numerator;
		this.denominator = 1;
	}
	
	public void reciprocal() {
		int numValue = this.numerator;
		this.numerator = this.denominator;
		this.denominator = numValue;
	}
	
	/**
	 * This method computes the greatest common divisor of this Fraction object's 
	 * numerator and denominator.
	 * 
	 * @return the greatest common divisor for this Fraction
	 */
	private int gcd() {
		int num = this.numerator;
		int denom = this.denominator;
		int number;
		
		while (denom != 0) {
			number = denom;
			denom = num % denom;
			num = number;
		}
		
		return num;
	}
		
	/**
	 * This method reduces this Fraction object to its simplest form.
	 */
	public void reduce() {
		int commonDenom = gcd();
		
		this.numerator /= commonDenom;
		this.denominator /= commonDenom;
	}
	
	/**
	 * Accesses the numerator of this Fraction object.
	 * 
	 * @return the numerator
	 */
	public int getNumerator() {
		return this.numerator;
	}
	
	/**
	 * Accesses the denominator of this Fraction object.
	 * 
	 * @return the denominator
	 */
	public int getDenominator() {
		return this.denominator;
	}
	
	/**
	 * Calculates the decimal-point equivalent of this Fraction object.
	 * 
	 * @return the decimal-point equivalent
	 */
	public double toDouble() {
		return this.numerator / (double) this.denominator;
	}
	
	/**
	 * Generates a textual representation of this Fraction object.
	 * 
	 * @return a string containing the textual representation
	 */
	public String toString() {
		return this.numerator + "/" + this.denominator;
	}
	
	/**
	 * Determines whether this Fraction object and the given
	 * object are equal.
	 * 
	 * @param other - the other object to compare
	 * @return true if the two objects are equal, false otherwise
	 */
	public boolean equals(Object other) {
		if (!(other instanceof Fraction))
			return false;
		Fraction otherFraction = (Fraction) other;
		return this.numerator == otherFraction.numerator && 
				this.denominator == otherFraction.denominator;
	}
}