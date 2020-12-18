package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsRecursive<T> {
    private final List<T> elements;
    private final int k;
    private List<List<Integer>> results;

    public static void main(String[] args) {
        new CombinationsRecursive(Arrays.asList(1, 2, 3, 4, 5), 3)
                .combine()
                .print();
    }

    public CombinationsRecursive(List<T> elements, int k) {
        this.elements = elements;
        this.k = k;
    }

    public void print() {
        for (List<Integer> combination : results) {
            for (Integer i : combination) {
                System.out.printf(elements.get(i) + " ");
            }
            System.out.println();
        }
    }

    public CombinationsRecursive combine() {
        results = new ArrayList<>();
        combine(0, new ArrayList<>());
        return this;
    }

    private List<List<Integer>> combine(int elemIx, List<Integer> prev) {
        for (int i = elemIx; i < elements.size(); i++) {
            List<Integer> next = new ArrayList<>(prev);
            next.add(i);
            if (next.size() == k) {
                results.add(next);
            } else {
                combine(i + 1, next);
            }
        }
        return results;
    }
}
