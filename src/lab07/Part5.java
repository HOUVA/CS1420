package lab07;

/**
 * This class contains a very buggy rangeSize method.
 * 
 * @author CS 1420 course staff
 * @version September 30, 2025
 */
public class Part5 {
	/**
	 * This method calculates the range size for a collection of Fractions.
	 * The range size is the difference between the greatest and least values.
	 * For example, the range size of [1/4, 4/5, 1/2] is 4/5 - 1/4 = 11/20.
	 * The result is another Fraction object that is returned.
	 * 
	 * @param arr - array of Fractions
	 * @return the range size Fraction
	 */
	public static Fraction rangeSize(Fraction[] arr) {
		if(arr.length == 0)
			return new Fraction(0, 1);
		Fraction min = minimum(arr);
		Fraction max = maximum(arr);
		// returns max + (-1 * min) which is max - min
		return max.add(min.multiply(new Fraction(-1, 1)));
	}
	
	/**
	 * This method returns the minimum Fraction in the given array.
	 * 
	 * @param arr - array of Fractions
	 * @return minimum fraction
	 */
	private static Fraction minimum(Fraction[] arr) {
		Fraction min = arr[0];
		for(int index = 1; index < arr.length - 1; index++)
			if(arr[index].toDouble() < min.toDouble())
				return arr[index];
		return min;
	}
	
	/**
	 * This method returns the maximum Fraction in the given array.
	 * 
	 * @param arr - array of Fractions
	 * @return maximum fraction
	 */
	private static Fraction maximum(Fraction[] arr) {
		Fraction max = arr[0];
		for(int index = 1; index < arr.length - 1; index++)
			if(arr[index].toDouble() < max.toDouble())
				return arr[index];
		return max;
	}
}