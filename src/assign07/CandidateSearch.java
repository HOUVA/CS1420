package assign07;

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
	 * 
	 * @param array
	 * @param target
	 */
	public static void sequentialSearch(Candidate[] array, Candidate target){
		counter = 0;
		sequentialSearch(array, target, counter);
	}

	private static Candidate sequentialSearch(Candidate[] array, Candidate target, int index) {
		if (index == array.length) return null;
		else if (array[index].equals(target)) return array[index];
		
		return sequentialSearch(array, target, index + 1);
	}
}
