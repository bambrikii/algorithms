package org.bambrikii.examples.graphs.unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFind {
    private Map<Integer, Integer> values = new HashMap<>();

    public UnionFind add(int val) {
        values.put(val, val);
        return this;
    }

    public UnionFind join(int left, int right) {
        values.put(left, right);
        return this;
    }

    private int parent(int child) {
        int parent = values.get(child);
        if (parent == child) {
            return parent;
        }
        Integer parent2 = parent(parent);
        if (parent != parent2) {
            values.put(child, parent2);
        }
        return parent2;
    }

    public boolean find(int left, int right) {
        return parent(left) == right;
    }

    public static void main(String[] args) {
        UnionFind algo = new UnionFind();
        algo.add(1).add(2).add(3).add(4).add(5)
                .join(1, 2).join(2, 4).join(4, 5);
        System.out.println("1 -> 5 should be true: " + algo.find(1, 5));
        System.out.println("1 -> 3 should be false: " + algo.find(1, 3));
        System.out.println("1 -> 5 should be fast and true: " + algo.find(1, 5));
    }
}
