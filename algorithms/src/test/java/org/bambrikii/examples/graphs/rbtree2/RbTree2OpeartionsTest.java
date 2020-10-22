package org.bambrikii.examples.graphs.rbtree2;

import org.bambrikii.examples.graphs.rbtree.IntegerComparator;
import org.bambrikii.examples.graphs.treeutils.RBTreePrinter;
import org.junit.jupiter.api.Test;

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
        tree.delete(4);
        RBTreePrinter.print(tree.getRoot());
    }
}
