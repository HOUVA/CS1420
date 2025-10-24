package assign07;
import java.util.Scanner;
import java.io.*;

/**
 * This class allows the user to check if a Candidate exists in a list using terminal.
 * 
 * Requires three different arguments:
 * - a file path to a list of candidates, the list must contain a name with no spaces, an age, and a rating between 1- 10
 * - the name of candidate to search for
 * - the age of candidate
 * 
 * Checks if the candidate exists in the list using CandidateSearch and prints a message depending on the result.
 * 
 * @author Matthew Suggars
 * @version 16 October 2025
 */
public class CandidateSearchApplication {

	public static void main(String[] args) {
		if (args.length < 3) {
			System.out.printf("At least three arguments are required.%n"
					+ "This application requires the following: %n"
					+ "A file path containing a list of candidates.%n"
					+ "A Candidate to search for (Do not include any spaces.)%n"
					+ "The age of the Candidate.");
			return;
		}
		Scanner fileInput = null;
		
		try {
			fileInput = new Scanner(new File(args[0]));
		} catch (IOException e) {
			System.out.println("ERROR: File path does not exist.");
			return;
		}
		Candidate targetCandidate = new Candidate(args[1], Integer.parseInt(args[2]));
		
		Candidate[] candidateList = new Candidate[fileInput.nextInt()];
		for (int index = 0; index < candidateList.length; index++) {
			String name = fileInput.next();
			int age = fileInput.nextInt();
			int rating = fileInput.nextInt();
			candidateList[index] = new Candidate(name, age, rating);
		}
		
		Candidate applicant = CandidateSearch.binarySearch(candidateList, targetCandidate);
				
		if (applicant != null) 
			System.out.printf("%s has rating %d.%n", applicant.toString(), applicant.getRating());
		else
			System.out.printf("%s was not found.", targetCandidate.toString());
		
		fileInput.close();
			
	}

}
