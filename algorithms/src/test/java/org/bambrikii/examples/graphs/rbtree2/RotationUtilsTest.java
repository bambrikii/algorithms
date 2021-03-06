package org.bambrikii.examples.graphs.rbtree2;

import org.bambrikii.examples.graphs.rbtree.RBNode;
import org.bambrikii.examples.graphs.rbtree2.utils.RotationUtils;
import org.bambrikii.examples.graphs.treeutils.RBTreePrinter;
import org.junit.jupiter.api.Test;

public class RotationUtilsTest {
    @Test
    public void shouldRotateLeft() {
        RBNode<Integer> left1Left1 = new RBNode<>(1);
        RBNode<Integer> left1 = new RBNode<>(2);
        RBNode<Integer> left1Right1 = new RBNode<>(3);
        left1Left1.setParent(left1);
        left1.setLeft(left1Left1);
        left1Left1.setRight(left1Right1);
        left1Right1.setParent(left1Left1);

        RBTreePrinter.print(left1);
        RBNode<Integer> x = RotationUtils.rotateUp(left1Left1);
        RBTreePrinter.print(x);
    }
}
