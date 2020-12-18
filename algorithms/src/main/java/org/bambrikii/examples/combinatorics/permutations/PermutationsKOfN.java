package org.bambrikii.examples.combinatorics.permutations;

/**
 * https://stackoverflow.com/questions/52819397/k-permutation-of-elements-in-arraylist-java
 */
public class PermutationsKOfN {
    public static void main(String[] args) {
        String alphabet = "123";
        int n = alphabet.length();
        int k = 2;
        String elements = alphabet.substring(0, n);
        perm2(elements, k);
    }

    public static void perm2(String s, int k) {
        perm2("", s, k);
    }

    public static void perm2(String prefix, String s, int k) {
        int n = prefix.length();
        int m = s.length();

        if (n == k) {
            System.out.println(prefix);
        } else {
            for (int i = 0; i < m; i++) {
                perm2(prefix + s.charAt(i), s.substring(0, i) + s.substring(i + 1, m), k);
            }
        }
    }

}
