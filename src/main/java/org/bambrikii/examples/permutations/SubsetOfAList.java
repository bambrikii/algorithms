package org.bambrikii.examples.permutations;

/**
 * Created by Alexander Arakelyan on 02/03/17 20:26.
 * <p>
 * http://stackoverflow.com/questions/7206442/printing-all-possible-subsets-of-a-list
 * <p>
 * 1 = 001 = {1}
 * 2 = 010 = {2}
 * 3 = 011 = {1, 2}
 * 4 = 100 = {3}
 * 5 = 101 = {1, 3}
 * 6 = 110 = {2, 3}
 * 7 = 111 = {1, 2, 3}
 */
public class SubsetOfAList {
	public static void main(String[] args) {
		printAllSubSets(new int[]{1, 2, 3});
	}

	public static void printAllSubSets(int[] arr) {
		int n = arr.length;
		int allMasks = (1 << n);
		for (int i = 1; i < allMasks; i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) { //The j-th element is used
					System.out.print((j + 1) + " ");
				}
			}
			System.out.println();
		}
	}
}
