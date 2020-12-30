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
        values.put(right, left);
        return this;
    }

    public boolean find(int left, int right) {
        return values.get(left) == values.get(right);
    }

    public static void main(String[] args) {
        UnionFind algo = new UnionFind();
        algo.add(1).add(2).add(3).join(1, 2);
        System.out.println(algo.find(1, 2)); // true
        System.out.println(algo.find(1, 3)); // false
    }
}
