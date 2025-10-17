package assign07;

import java.util.Arrays;

public class CandidateSearch {
	private static int counter;
	
	/**
	 * Returns the value of the static counter variable.
	 * 
	 * @return value of counter.
	 */
	public static int getCallCount() {
		return counter;
	}
	
	/**
	 * Driver method to check if a Candidate exists in a list. If so, then returns 
	 * that Candidate, otherwise returns false.
	 * 
	 * @param array - array of Candidate objects
	 * @param target - Candidate object to look for in array
	 * @return result of recursive method
	 */
	public static Candidate sequentialSearch(Candidate[] array, Candidate target){
		counter = 0;
		return sequentialSearch(array, target, 0);
	}

	/**
	 * Recursive method to sequentially check if a Candidate exists in a list.
	 * 
	 * @param array - array of Candidate objects
	 * @param target - Candidate object to search in array
	 * @param index - recursive index for array.
	 * @return null if Candidate does not exist in the array, otherwise returns the Candidate
	 */
	private static Candidate sequentialSearch(Candidate[] array, Candidate target, int index) {
		counter++;
		if (index == array.length) 
			return null;
		else if (array[index].equals(target)) 
			return array[index];
		return sequentialSearch(array, target, index + 1);
	}
	
	/**
	 * 
	 * @param array
	 * @param target
	 * @return
	 */
	public static Candidate binarySearch(Candidate[] array, Candidate target) {
		counter = 0;
		Arrays.sort(array);
		return binarySearch(array, target, 0);
	}
	
	private static Candidate binarySearch(Candidate[] array, Candidate target, int index) {
		counter++;
		if (index == array.length)
			return  null;
		else if (array[index].equals(target))
			return array[index];
		
		if (array[index].compareTo(target) == 1)
			return binarySearch(Arrays.copyOfRange(array, array.length / 2, array.length - 1), target);
		else
			return binarySearch(Arrays.copyOfRange(array, 0, array.length / 2), target);

	}
	
	
}
