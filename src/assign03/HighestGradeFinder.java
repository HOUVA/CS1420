package assign03;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
 
public class HighestGradeFinder {
	public static void main(String[] args) {
		String fileName = "src/studentScores.txt"; // Replace with your file path
        String topStudent = "";
        double highestGrade = Double.MIN_VALUE;
    
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {                
        	String line;
        	while ((line = br.readLine()) != null) {
        		String[] parts = line.split(",");
                String name = parts[0];
                double grade = Double.parseDouble(parts[1]);
    
                if (grade > highestGrade) {
                	highestGrade = grade;
                    topStudent = name;
                }
            }
    
            if (!topStudent.isEmpty()) {
            	System.out.println("Top student: " + topStudent + " with grade: " + highestGrade);
            } else {
            	System.out.println("No data found in the file.");
            }
    
        } catch (IOException e) {
                System.out.println("Error reading the file: " + e.getMessage());
        }
    }
}