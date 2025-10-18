package assign07;
import java.util.Scanner;
import java.io.*;

public class CandidateSearchApplication {

	public static void main(String[] args) {
		if (args.length < 3)
			System.out.printf("At least three arguments are required.%n"
					+ "This application requires the following: %n"
					+ "A file path containing a list of candidates.%n"
					+ "A Candidate to search for (Do not include any spaces.)%n"
					+ "The age of the Candidate.");
		Scanner fileInput = null;
		Candidate[] candidateList = null;
		Candidate applicant = null;
		
		try {
			fileInput = new Scanner(new File(args[0]));
		} catch (IOException e) {
			System.out.println("ERROR: File path does not exist.");
		}
		Candidate targetCandidate = new Candidate(args[1], Integer.parseInt(args[2]));

		
		while (fileInput.hasNext()) {
			candidateList = new Candidate[fileInput.nextInt()];
			for (int index = 0; index < candidateList.length; index++) {
				String name = fileInput.next();
				int age = fileInput.nextInt();
				int rating = fileInput.nextInt();
				candidateList[index] = new Candidate(name, age, rating);
			}
			applicant = CandidateSearch.binarySearch(candidateList, targetCandidate);
	
		}
				
		if (applicant != null) 
			System.out.printf("%s has rating %d.%n", applicant.toString(), applicant.getRating());
		else
			System.out.printf("%s was not found.", targetCandidate.toString());
			
	}

}
