package assign03;

import java.util.Scanner;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;

/* CS 1420 Accelerated Object-Oriented Programming
 * Assignment 3: Grade Predictor
 * @author Matthew Suggars U1067447
 * @version September 6, 2025
 * 
 * Reads a student's mid-term exam score, final exam score, average lab score, average weekly-quiz score, 
 * and individual assignment scores from file and then reports information about the student's assignment 
 * scores, as well as predicts the average test scores needed to achieve a range of overall CS 1420 course 
 * grades. 
 */

public class GradePredictor {

	public static void main(String[] args) {
		/* Prompts the user to type a file name.
		* If the given file name and path cause an IOException, prints a message describing the error, 
		* then repeats loop */
		Scanner userInput = new Scanner(System.in);
		Scanner file = null;
		double midTerm, finalExam, labAverage, weeklyQuizAverage, averageAssignmentScore;
		int medianAssignmentScore;
		
		do {
			System.out.println("Enter a file name with path:");
			String filePath = userInput.nextLine();
			try {
				File fileName = new File(filePath);
				file = new Scanner(fileName);
			} catch (IOException e) {
				System.out.println("Error: File not found.");
			}
	
			// Reads the mid-term, final, lab average, and weekly-quiz average from file.
			
			int counter = 0;
			
			double assignmentSum = 0;
			double finalGrade = 0;
			if (file.hasNext()) {
				midTerm = file.nextDouble();
				finalExam = file.nextDouble();
				labAverage = file.nextDouble();
				weeklyQuizAverage = file.nextDouble();
				
				int assignmentQuantity = file.nextInt();
				int[] assignments = new int[assignmentQuantity];
				
				for (int index = 0; index < assignmentQuantity; index++) {
					assignments[index] = file.nextInt();
					if (assignments[index] == 0)
						counter++;
					assignmentSum += assignments[index];
				}
				
				Arrays.sort(assignments);
				
				// Computes and reports the information about the student's assignment scores
				averageAssignmentScore = assignmentSum / assignmentQuantity;
				medianAssignmentScore = assignments[assignments.length / 2];
				finalGrade = averageAssignmentScore * 0.2;
				finalGrade += (finalExam * 0.15) + (midTerm * 0.1) + (labAverage * 0.1) + (weeklyQuizAverage * 0.05);
												
				System.out.printf("The average assignment score is %.2f.%n", averageAssignmentScore);
				System.out.printf("The median assignment score is %d.%n", medianAssignmentScore);
				System.out.printf("The number of 0 assignment scores is %d.%n", counter);
				System.out.printf("The highest assignment score is %d.%n", assignments[assignments.length - 1]);
				System.out.printf("The lowest assignment score is %d.%n", assignments[0]);
				
				// Predicts the minimum average test scores needed to achieve overall CS 1420 course grades of 
				// C-, C, C+, B-, B, B+, A-, and A.  
				int[] gradeRanges = {70, 73, 77, 80, 83, 87, 90, 93};
				String[] letterGrade = {"C-", "C", "C+", "B-", "B", "B+", "A-", "A"};
				for (int index = 0; index < gradeRanges.length; index++) {
					double averageTestScore = (gradeRanges[index] - finalGrade) / 0.4;
					if (averageTestScore > 0 && averageTestScore <= 100)
						System.out.printf("A test average of %.2f is needed to achieve %s.%n", averageTestScore, letterGrade[index]);
					else
						System.out.printf("It is not possible to achieve %s with these scores.%n", letterGrade[index]);
				}
				
			}
			break;
		
		} while (true);	
		file.close();
		userInput.close();
	}
}
