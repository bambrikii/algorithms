package org.bambrikii.examples.algorithms.incubator.combinatorics;

public class PermutationsWithNoDuplicates {
    public static void main(String[] args) {
        new PermutationsWithNoDuplicates()
//                .permute("ABC", 0)
                .permute("ABB", 0)
        ;
    }

    public PermutationsWithNoDuplicates permute(String str, int k) {
        if (k == str.length()) {
            System.out.println(str);
            return null;
        }
        for (int i = k; i < str.length(); i++) {
            String permutation = swap(str, k, i);
            permute(permutation, k + 1);
        }
        return this;
    }

    private String swap(String str, int k, int i) {
        char chi = str.charAt(i);
        char chk = str.charAt(k);
        return str.substring(0, k) + (k >= i ? "" : chi + str.substring(k + 1, i)) + chk + str.substring(i + 1);
    }
}
