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
		if (target == null)
			return null;
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
	 * Driver method for binarySearch. Sorts array and returns a Candidate if it exists in a list, 
	 * If not, returns null;
	 * 
	 * @param array - array of Candidate objects
	 * @param target - Candidate to search for in array
	 * @return Candidate if it exists in array, otherwise false.
	 */
	public static Candidate binarySearch(Candidate[] array, Candidate target) {
		counter = 0;
		if (target == null)
			return null;
		Arrays.sort(array);
		return binarySearch(array, target, 0, array.length - 1);
	}
	
	/**
	 * 
	 * @param array
	 * @param target
	 * @param lowIndex
	 * @param highIndex
	 * @return
	 */
	private static Candidate binarySearch(Candidate[] array, Candidate target, int lowIndex, int highIndex) {
		counter++;
		if (lowIndex > highIndex)
			return  null;
		
		int middleIndex = (lowIndex + highIndex) / 2;
		
		if (array[middleIndex].equals(target))
			return array[middleIndex];
		else if (array[middleIndex].compareTo(target) > 0)
			return binarySearch(array, target, lowIndex, middleIndex - 1);
		else
			return binarySearch(array, target, middleIndex + 1, highIndex);
	}
	
	
}
