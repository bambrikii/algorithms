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

    public boolean find(int left, int right) {
        Integer leftVal = values.get(left);
        if (leftVal.equals(right)) {
            return true;
        }
        if (leftVal.equals(left)) {
            return leftVal.equals(right);
        }
        boolean found = find(leftVal, right);
        if (found) {
            values.put(left, right);
        }
        return found;
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
