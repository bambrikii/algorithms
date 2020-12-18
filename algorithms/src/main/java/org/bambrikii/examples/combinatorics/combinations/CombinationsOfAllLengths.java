package org.bambrikii.examples.combinatorics.combinations;

/**
 * https://www.ktbyte.com/java-tutorial/book/combinatorics
 */
public class CombinationsOfAllLengths {
    public static void main(String[] args) {
        String[] animals = {"1", "2", "3"};
        comb2(animals, new boolean[3], 0);
    }

    static void comb2(String[] x, boolean[] inc, int pos) {
        if (pos == inc.length) {
            for (int i = 0; i < inc.length; i++) {
                if (inc[i]) System.out.print(x[i] + " ");
            }
            System.out.println();
            return;
        }
        inc[pos] = true;
        comb2(x, inc, pos + 1);
        inc[pos] = false;
        comb2(x, inc, pos + 1);
    }
}
