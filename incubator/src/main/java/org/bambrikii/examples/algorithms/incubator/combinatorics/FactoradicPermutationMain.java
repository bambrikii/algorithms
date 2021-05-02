package org.bambrikii.examples.algorithms.incubator.combinatorics;

import static org.bambrikii.examples.algorithms.incubator.combinatorics.FactoradicPermutation.getFactoradic;
import static org.bambrikii.examples.algorithms.incubator.combinatorics.FactoradicPermutation.getPermutation;

public class FactoradicPermutationMain {
    public static void main(String[] args) {
        int t, n, l;
        t = 10;
        char[] str = "abcdefghijklm".toCharArray();
        int[] f = new int[13];
        for (int i = 0; i < t; i++) {
            n = 5;
            f = getFactoradic(n - 1);
            System.out.println(getPermutation(str, f));
        }
    }
}
