package org.bambrikii.examples.permutations.josephus;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class JosephusPermutationsAlgo {
    private int n;
    private int m;
    private final List<JosephusPermutationsAlgoListener> listeners;

    public JosephusPermutationsAlgo(int n, int m) {
        this.n = n;
        this.m = m;
        listeners = new ArrayList<>();
    }

    public void addListener(JosephusPermutationsAlgoListener listener) {
        this.listeners.add(listener);
    }

    public Integer permute() {
        LinkedList<Integer> arr = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            Integer elem = Integer.valueOf(i);
            arr.add(elem);
            onAdd(i, elem);
        }
        int i = 0;
        while (arr.size() > 1) {
            i = (i + m) % arr.size();
            Integer elem = arr.remove(i);
            onRemove(i, elem);
        }
        Integer last = arr.get(0);
        onLast(last);
        return last;
    }

    private void onAdd(int i, Integer elem) {
        for (JosephusPermutationsAlgoListener listener : listeners) {
            listener.onAdd(i, elem);
        }
    }

    private void onRemove(int i, Integer elem) {
        for (JosephusPermutationsAlgoListener listener : listeners) {
            listener.onRemove(i, elem);
        }
    }

    private void onLast(Integer last) {
        for (JosephusPermutationsAlgoListener listener : listeners) {
            listener.onLast(last);
        }
    }
}
