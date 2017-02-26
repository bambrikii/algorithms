package org.bambrikii.examples.graphs.permutations;

/**
 * Created by Alexander Arakelyan on 26/02/17 13:22.
 */
public class PermutationsNonRecursive extends AbstractPermutations implements Permutations {
	public static void main(String[] args) {
		test(new PermutationsNonRecursive());
	}

	@Override
	public void permute(int[] a, int k) {
		int[] c = new int[a.length];
		printArray(a);
		int i = 0;
		while (i < a.length) {
			if (c[i] < i) {
				if (i % 2 == 0) {
					swap(a, 0, i);
				} else {
					swap(a, c[i], i);
				}
				printArray(a);
				c[i] += 1;
				i = 0;
			} else {
				c[i] = 0;
				i += 1;
			}
		}
	}
}
