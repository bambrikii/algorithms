package org.bambrikii.examples.algorithms.incubator.combinatorics;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class PermutationsAll<T> {
    public static void main(String[] args) {
        List<Integer> elements = Arrays.asList(1, 2, 3);
        new PermutationsAll(elements)
                .permute()
                .print();
        ;
    }

    private final List<T> elements;
    private List<List<Integer>> results;

    public PermutationsAll(List<T> elements) {
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

    public PermutationsAll permute() {
        results = new ArrayList<>();

        List<Integer> next = new ArrayList<>();
        List<Integer> nextElements = IntStream
                .range(0, elements.size())
                .boxed()
                .collect(Collectors.toList());

        permute(next, nextElements);

        return this;
    }

    private void permute(List<Integer> prev, List<Integer> elements) {
        results.add(prev);
        if (prev.size() == elements.size()) {
            return;
        }
        for (int i = 0; i < elements.size(); i++) {
            List<Integer> nextElements = new ArrayList<>(elements);
            nextElements.remove(i);

            List<Integer> next = new ArrayList<>(prev);
            next.add(elements.get(i));

            permute(next, nextElements);
        }
    }
}
