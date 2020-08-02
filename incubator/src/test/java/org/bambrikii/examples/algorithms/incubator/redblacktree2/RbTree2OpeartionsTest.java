package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.IntegerComparator;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.print.RBTreePrinter;
import org.junit.Test;

public class RbTree2OpeartionsTest {
    @Test
    public void shouldInset() {
        RBTree2<Integer> tree = new RBTree2<>(new IntegerComparator());
        for (int i = 0; i < 5; i++) {
            tree.insert(i);
        }
        RBTreePrinter.print(tree.getRoot());
    }

    @Test
    public void shouldDelete() {
        RBTree2<Integer> tree = new RBTree2<>(new IntegerComparator());
        for (int i = 0; i < 5; i++) {
            tree.insert(i);
        }
        tree.delete(3);
        RBTreePrinter.print(tree.getRoot());
    }
}
