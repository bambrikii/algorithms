package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.IntegerComparator;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.print.RBTreePrinter;

public class RBMain2 {
    public static void main(String[] args) {
        RBTree2<Integer> tree = new RBTree2<>(new IntegerComparator());
        for (int i = 0; i < 10; i++) {
            tree.insert(i);
        }
        RBTreePrinter printer = new RBTreePrinter();
        printer.print(tree.getRoot());

        for (int i = 3; i < 6; i++) {
            tree.delete(i);
        }
        printer.print(tree.getRoot());
    }
}
