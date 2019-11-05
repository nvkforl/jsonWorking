package com.example;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.Set;

public class RemoveDuplicatesFromStringArrayExample {

	public static void main(String[] args) {

		// sample test string
		String testStr = "SSS FFF GG RR s f gg";

		// Step 1: create LinkedHashSet to maintain insertion-order
		Set<Character> lhSet = new LinkedHashSet<Character>();

		// Step 2: convert String into character-array
		// using toCharArray() method
		char[] chArray = testStr.toCharArray();

		// Step 3: iterate through char[] array
		for (char ch : chArray) {

			// Step 4: leave spaces
			if (ch != ' ') {

				// Step 5: check whether char already present in LHSet
				boolean checkChar = lhSet.contains(ch);

				// Step 6: if not present, then add
				if (!checkChar) {
					lhSet.add(ch);
				}
			}
		}

		// Step 7: print to console
		System.out.println("After removing duplicate : " + lhSet);
	}

}
