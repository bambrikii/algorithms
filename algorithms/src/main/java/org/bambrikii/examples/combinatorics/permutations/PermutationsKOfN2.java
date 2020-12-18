package org.bambrikii.examples.combinatorics.permutations;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * https://stackoverflow.com/questions/52819397/k-permutation-of-elements-in-arraylist-java
 */
public class PermutationsKOfN2 {
    public static void main(String[] args) {
        permK(new ArrayList<>(Arrays.asList(1, 2, 3)), 0, 2);
    }

    static <E> void permK(List<E> p, int i, int k) {
        if (i == k) {
            System.out.println(p.subList(0, k));
            return;
        }

        for (int j = i; j < p.size(); j++) {
            Collections.swap(p, i, j);
            permK(p, i + 1, k);
            Collections.swap(p, i, j);
        }
    }

}
