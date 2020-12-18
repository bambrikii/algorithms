package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsNonRecursive<T> {
    private final List<T> elements;
    private final int k;
    private List<List<Integer>> results;

    public CombinationsNonRecursive(List<T> elements, int k) {
        this.elements = elements;
        this.k = k;
    }

    public static void main(String[] args) {
        new CombinationsNonRecursive<>(Arrays.asList(1, 2, 3, 4, 5), 3)
                .combine()
                .print()
        ;
    }

    public void print() {
        for (List<Integer> combination : results) {
            for (Integer elem : combination) {
                System.out.print(elements.get(elem) + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    public CombinationsNonRecursive<T> combine() {
        this.results = new ArrayList<>();
        int[] cIs = new int[k];
        int eSz = elements.size();
        int ciOffset = eSz - k;

        int ei = 0;
        int ci = 0;

        while (ci >= 0) {
            if (ei < ciOffset + ci) {
                cIs[ci] = ei;
                if (ci == k - 1) {
                    print(cIs);
                    ei++;
                    printPos("combination; ei++", ciOffset, ci, ei, cIs);
                } else {
                    ei = cIs[ci] + 1;
                    ci++;
                    printPos("ei+1; ci++", ciOffset, ci, ei, cIs);
                }
            } else {
                ci--;
                if (ci >= 0) {
                    ei = cIs[ci] + 1;
                    printPos("ci--; ei+1", ciOffset, ci, ei, cIs);
                }
            }
        }
        return this;
    }

    private void printPos(String text, int ciOffset, int ci, int ei, int[] pos) {
        System.out.print("Pos ciOffset(" + ciOffset + ") + ci(" + ci + ") = " + (ciOffset + ci) + ", ei(" + ei + ")  ");
        for (int i : pos) {
            System.out.print(i);
        }
        System.out.print(" ; " + text);
        System.out.println();
    }

    private void print(int[] pos) {
        List<Integer> positions = new ArrayList<>();
        for (int i : pos) {
            positions.add(i);
        }
        results.add(positions);
    }
}
