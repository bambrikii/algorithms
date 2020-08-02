package org.bambrikii.examples.permutations.permutations1;

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
		printAllSubSets(3);
		System.out.println("---");
		printAllSubSets(new int[]{4, 5, 6});
	}

	private static void printAllSubSets(int[] ints) {
		int n = 1 << ints.length;
		for (int i = 1; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if ((i & (1 << j)) > 0) {
					System.out.print(ints[j] + " ");
				}
			}
			System.out.println();
		}
	}

	public static void printAllSubSets(int n) {
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
