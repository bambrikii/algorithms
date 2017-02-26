package org.bambrikii.examples.graphs.permutations;

/**
 * https://vasanthexperiments.wordpress.com/tag/algorithm-to-find-permutation-of-given-array/
 *
 * @author Vasanth Raja Chittampally
 */

public class PermutationsRecursive extends AbstractPermutations implements Permutations {

	public static void main(String[] args) {
		test(new PermutationsRecursive());
	}

	@Override
	public void permute(int[] a, int k) {
		if (k == a.length)
			printArray(a);
		else
			for (int i = k; i < a.length; i++) {
				swap(a, k, i);
				permute(a, k + 1);
				swap(a, k, i);
			}
	}

}
