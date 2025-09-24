package practice;
import java.util.Arrays;
import java.util.Scanner;
import java.io.IOException;
import java.io.File;

public class Scratch {
	public static void main(String[] args) {
		String empty = "";
		System.out.println((int)empty.charAt(0));
		
		
	}
	
	/**
	 * reverses all elements of the array.
	 * @param inputList
	 * 
	 */
	public static void reverseArray(int[] inputList) {
		for (int index = 0; index < inputList.length / 2; index++) {
			int temp = inputList[index];
			inputList[index] = inputList[inputList.length - 1 - index];
			inputList[inputList.length - 1 - index] = temp;
		}	
	}
}
