package org.bambrikii.examples.combinatorics.permutations.josephus;

import java.text.MessageFormat;

public class JosephusPermutationsMain {
    public static void main(String[] args) {
        JosephusPermutationsAlgo algo = new JosephusPermutationsAlgo(5, 1);
        algo.addListener(new JosephusPermutationsAlgoListener() {
            @Override
            public void onAdd(int i, Integer elem) {
                System.out.println(MessageFormat.format("Adding element {0}, {1}", i, elem));
            }

            @Override
            public void onRemove(int i, Integer elem) {
                System.out.println(MessageFormat.format("Removing element {0}, {1}", i, elem));
            }

            @Override
            public void onLast(Integer last) {
                System.out.println(MessageFormat.format("Last element {0}", last));
            }
        });

        Integer leftElement = algo.permute();
        System.out.println(MessageFormat.format("Left element {0}", leftElement));
    }
}
