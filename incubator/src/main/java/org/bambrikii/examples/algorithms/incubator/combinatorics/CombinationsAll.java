package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CombinationsAll<T> {
    private final List<T> elements;
    private List<List<Integer>> results;

    public static void main(String[] args) {
        new CombinationsAll(Arrays.asList(1, 2, 3))
                .combine()
                .print()
        ;
    }

    public CombinationsAll(List<T> elements) {
        this.elements = elements;
    }

    private void print() {
        for (List<Integer> combination : results) {
            for (Integer pos : combination) {
                System.out.print(" " + elements.get(pos));
            }
            System.out.println();
        }
    }

    private CombinationsAll combine() {
        results = new ArrayList<>();
        combine(0, new ArrayList<>());
        return this;
    }

    private CombinationsAll combine(int elemIx, List<Integer> prev) {
        results.add(prev);
        if (prev.size() == elements.size()) {
            return this;
        }
        for (int i = elemIx; i < elements.size(); i++) {
            List<Integer> next = new ArrayList<>(prev);
            next.add(i);
            combine(i + 1, next);
        }
        return this;
    }
}
