package practice;

public class RecursionPractice {

	public static void main(String[] args) {
		countdown(3);
		System.out.println(sumToN(4));
		System.out.println(factorial(5));
		printBackward("hey");
		System.out.println(countChars("hello"));
		
		System.out.println(sumArray(new int[]{1, 2, 3, 4, 5}, 0));
		
		System.out.print(max(new int[] {1, 2, 3, 4, 5}, 0));
	}
	
	/*
	 * Recursive function that prints numbers from n to 1.
	 */
	private static void countdown(int n) {
		if (n == 0) return;
		
		System.out.println(n);
		countdown(n- 1);
	}
	
	/*
	 * Recursive function to add all numbers from 1 to n.
	 */
	private static int sumToN(int n) {
		if (n == 0) return 0;
		return n  + sumToN(n - 1);
	}
	
	/*
	 * Recursive function that returns the factorial of n.
	 */
	private static int factorial(int n) {
		if (n == 0) return 1;
		return n  * sumToN(n - 1);
	}
	
	/*
	 * Recursive function to print the reverse of a string on 
	 */
	private static void printBackward(String s) {
		if (s.length() <= 0) return;
		System.out.println(s.charAt(s.length()-1));
		printBackward(s.substring(0, s.length()-1));
		
	}
	
	/*
	 * Recursive function that counts the number of characters in a String.
	 */
	private static int countChars(String s) {
		if (s.length() == 1) return 1;
		return 1 + countChars(s.substring(1));
	}
	
	/*
	 * Recursive function that adds all the values in an array.
	 */
	private static int sumArray(int[] nums, int index) {
		if (index == nums.length) return 0;
		return nums[index] + sumArray(nums, index + 1);
	}
	
	private static int max(int[] nums, int index) {
		if (index == nums.length)
			if (nums[index] > max(nums, index + 1))
				return nums[index];
			else
				return max(nums, index + 1);
		
		
		
		
	}

}
