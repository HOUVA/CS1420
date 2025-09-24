package lab03;

import java.io.IOException;
import java.io.File;
import java.util.Scanner;

public class TreeDataAnalyzer {

	public static void main(String[] args)throws IOException {
		File filename = new File("forest.txt"); // A file object represents a path to a file.
		Scanner file = new Scanner(filename); // Open the file for reading (scanning).
		String treeName;
		int treeCount;
		int totalTreeCount = 0;
		int highestCount = 0;
		int lowestCount = 100;
		
		
		while (file.hasNext()) {
			treeName = file.next(); // Scan the next word from the file.
			treeCount = file.nextInt(); // Scan the next int from the file.
			totalTreeCount += treeCount;
			
			if(treeCount > highestCount)
			    highestCount = treeCount;
			else if (treeCount < lowestCount)
			    lowestCount = treeCount;
			
			System.out.println("Tree: " + treeName + " has a count of " + treeCount + ".");
		}
		
		System.out.println(" ");
		System.out.println("Total tree count is " + totalTreeCount + ".");
		System.out.println("Highest tree count is " + highestCount + ".");
		System.out.println("Lowest tree count is " + lowestCount + ".");
		
		file.close();

	}

}
