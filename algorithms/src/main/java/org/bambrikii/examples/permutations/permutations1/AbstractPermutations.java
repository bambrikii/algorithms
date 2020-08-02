package org.bambrikii.examples.permutations.permutations1;

/**
 * Created by Alexander Arakelyan on 26/02/17 13:25.
 */
public class AbstractPermutations {
	protected static void test(Permutations p) {
		int a[] = {1, 2, 3, 4, 5, 6};
		p.permute(a, 0);
	}


	/**
	 * @param a the command line arguments
	 */
	void printArray(int[] a) {
		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");

		}
		System.out.println("");
	}

	protected void swap(int[] a, int k, int i) {
		int temp = a[k];
		a[k] = a[i];
		a[i] = temp;
	}

}
