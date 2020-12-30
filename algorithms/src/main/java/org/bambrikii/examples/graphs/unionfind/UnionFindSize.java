package org.bambrikii.examples.graphs.unionfind;

import java.util.HashMap;
import java.util.Map;

public class UnionFindSize {
    private Map<Integer, Integer> sizes = new HashMap<>();
    private Map<Integer, Integer> values = new HashMap<>();

    public UnionFindSize add(int val) {
        values.put(val, val);
        sizes.put(val, 1);
        return this;
    }

    public UnionFindSize join(int left, int right) {
        Integer parentLeft = parent(left);
        Integer sizeLeft = sizes.get(parentLeft);
        Integer parentRight = parent(right);
        Integer sizeRight = sizes.get(parentRight);
        if (sizeLeft > sizeRight) {
            values.put(right, left);
            adjustSizes(parentLeft, left, parentRight, right);
        } else {
            values.put(left, right);
            adjustSizes(parentRight, right, parentLeft, left);
        }
        return this;
    }

    private void adjustSizes(Integer parentLeft, Integer left, Integer parentRight, Integer right) {
        sizes.put(parentLeft, sizes.get(parentLeft) + 1);
        sizes.put(parentRight, sizes.get(parentRight) - 1);
        if (parentLeft != left) {
            sizes.put(left, 0);
        }
        if (parentRight != right) {
            sizes.put(right, 0);
        }
    }

    private Integer parent(Integer left) {
        Integer right = values.get(left);
        while (right != left) {
            left = right;
            right = values.get(right);
            values.put(left, right);
        }
        return right;
    }

    public boolean find(int left, int right) {
        return parent(values.get(left)) == parent(values.get(right));
    }

    public static void main(String[] args) {
        UnionFindSize algo = new UnionFindSize();
        algo
                .add(1).add(2).add(3).join(1, 2)
                .add(4).add(5).join(1, 4).join(4, 5);
        System.out.println(algo.find(1, 2) == true);
        System.out.println(algo.find(1, 5) == true);
        System.out.println(algo.find(1, 3) == false);
    }
}
