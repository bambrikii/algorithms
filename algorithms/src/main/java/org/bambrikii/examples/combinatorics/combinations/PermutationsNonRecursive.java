package org.bambrikii.examples.combinatorics.combinations;

import static org.bambrikii.examples.combinatorics.combinations.PermutationsRecursiveMain.swap;

public class PermutationsNonRecursive {
    public static void main(String[] args) {
        new PermutationsNonRecursive().permute(new int[]{1, 2, 3, 4});
    }

    public void permute(int[] a) {
        int[] q = new int[a.length];
        int i = 0;
        PermutationsRecursiveMain.print(a);
        while (i < a.length) {
            if (q[i] < i) {
                if (i % 2 == 0) {
                    swap(a, 0, i);
                } else {
                    swap(a, q[i], i);
                }
                print("", a);
                q[i]++;
                i = 0;
            } else {
                q[i] = 0;
                i++;
            }
        }
    }

    private void print(String str, int[] q) {
        System.out.printf(str);
        for (int i : q) {
            System.out.printf(i + " ");
        }
        System.out.println();
    }
}
