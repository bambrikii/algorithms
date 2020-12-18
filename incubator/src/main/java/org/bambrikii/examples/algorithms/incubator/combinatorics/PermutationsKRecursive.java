package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationsKRecursive<T> {
    public static void main(String[] args) {
        List<Integer> elements = Arrays.asList(1, 2, 3);
        new PermutationsKRecursive(elements)
                .permute(2)
                .print();
        ;
    }

    private final List<T> elements;
    private List<List<Integer>> results;

    public PermutationsKRecursive(List<T> elements) {
        this.elements = elements;
    }

    private void print() {
        for (List<Integer> result : results) {
            for (Integer elem : result) {
                System.out.print(elements.get(elem) + " ");
            }
            System.out.println();
        }
    }

    public PermutationsKRecursive permute(int i) {
        results = new ArrayList<>();

        List<Integer> next = new ArrayList<>();
        List<Integer> nextElements = IntStream
                .range(0, elements.size())
                .boxed()
                .collect(Collectors.toList());

        permute(next, nextElements, i);

        return this;
    }

    private void permute(List<Integer> prev, List<Integer> elements, int k) {
        if (prev.size() == k) {
            results.add(prev);
            return;
        }
        for (int i = 0; i < elements.size(); i++) {
            List<Integer> nextElements = new ArrayList<>(elements);
            nextElements.remove(i);

            List<Integer> next = new ArrayList<>(prev);
            next.add(elements.get(i));

            permute(next, nextElements, k);
        }
    }
}
