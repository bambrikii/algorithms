package org.bambrikii.examples.combinatorics.permutations.josephus;

public interface JosephusPermutationsAlgoListener {
    void onAdd(int i, Integer elem);

    void onRemove(int i, Integer elem);

    void onLast(Integer last);
}
