package org.bambrikii.examples.graphs.unionfind;

import java.util.HashMap;
import java.util.Map;

/**
 * https://www.cs.princeton.edu/~rs/AlgsDS07/01UnionFind.pdf
 */
public class UnionFindImproved {
    private Map<Integer, Integer> values = new HashMap<>();

    public UnionFindImproved add(int val) {
        values.put(val, val);
        return this;
    }

    private Integer parent(Integer left) {
        Integer tail = values.get(left);
        while (tail != left) {
            Integer left0 = left;
            left = tail;
            tail = values.get(left);
            values.put(left0, tail);
        }
        return tail;
    }

    // Or alternative to parent(Integer left)
    private Integer parent2(Integer left) {
        while (left != values.get(left)) {
            values.put(left, values.get(values.get(left)));
            left = values.get(left);
        }
        return left;
    }


    public UnionFindImproved join(int left, int right) {
        values.put(parent(right), parent(left));
        return this;
    }

    public boolean find(int left, int right) {
        return values.get(parent(left)) == values.get(parent(right));
    }

    public static void main(String[] args) {
        UnionFindImproved algo = new UnionFindImproved();
        algo
                .add(1).add(2).join(1, 2)
                .add(3)
                .add(4).add(5).join(4, 5).join(1, 4);
        System.out.println(algo.find(1, 2) == true); // true
        System.out.println(algo.find(1, 5) == true); // true
        System.out.println(algo.find(3, 5) == false); // false
    }
}
