package lab02;

import java.util.Scanner;

/**
 * This class contains a program for the practice and exploration of the char data type.
 * 
 * @author Matthew Suggars u1067447
 * @version August 26, 2025
 */
public class CharCodeDemo {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Type something and press enter.");
        
        String line = input.nextLine();
        char firstLetter = line.charAt(0);
        
        System.out.println(firstLetter + " has code " + (int)firstLetter);

        input.close();
        
        int key = 4;
        System.out.println("Original: " + line);
        System.out.print("Encoded:  ");

        // TODO write the for loop here
        for (int i = 0; i < line.length(); i++){
        	if(line.charAt(i) + key > 126)
        	    System.out.print((char)((line.charAt(i) + key) - 127 + 32));
        	else
        	    System.out.print((char)(line.charAt(i) + key));
        }
        

        System.out.println();
    }
}