package org.bambrikii.examples.algorithms.incubator.redblackrtee.print;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.junit.Test;

public class RBTreePrinterTest {
    @Test
    public void testPrint() {
        RBNode<Integer> root = new RBNode<>(7);

        RBNode<Integer> left1Left1 = new RBNode<>(1);
        RBNode<Integer> left1 = new RBNode<>(2);
        RBNode<Integer> left1Right1 = new RBNode<>(3);
        left1.setLeft(left1Left1);
        left1.setRight(left1Right1);

        RBNode<Integer> left2Left1 = new RBNode<>(4);
        RBNode<Integer> left2 = new RBNode<>(5);
        RBNode<Integer> left2Right1 = new RBNode<>(6);
        left2.setLeft(left2Left1);
        left2.setRight(left2Right1);

        left2Left1.setLeft(left1);

        RBNode<Integer> right1Left1 = new RBNode<>(8);
        RBNode<Integer> right1 = new RBNode<>(9);
        RBNode<Integer> right1Right1 = new RBNode<>(10);
        right1.setLeft(right1Left1);
        right1.setRight(right1Right1);

        RBNode<Integer> right2Left1 = new RBNode<>(11);
        RBNode<Integer> right2 = new RBNode<>(12);
        RBNode<Integer> right2Right1 = new RBNode<>(13);
        right2.setLeft(right2Left1);
        right2.setRight(right2Right1);

        right2Left1.setLeft(right1);

        root.setLeft(left2);
        root.setRight(right2);

        RBTreePrinter.print(root);
    }

    @Test
    public void testPrint2() {
        RBNode<Integer> root = new RBNode<>(4);

        RBNode<Integer> left1Left1 = new RBNode<>(1);
        RBNode<Integer> left1 = new RBNode<>(2);
        RBNode<Integer> left1Right1 = new RBNode<>(3);
        left1.setLeft(left1Left1);
        left1.setRight(left1Right1);

        RBNode<Integer> right1Left1 = new RBNode<>(5);
        RBNode<Integer> right1 = new RBNode<>(6);
        RBNode<Integer> right1Right1 = new RBNode<>(7);
        right1.setLeft(right1Left1);
        right1.setRight(right1Right1);

        root.setLeft(left1);
        root.setRight(right1);

        RBTreePrinter.print(root);
    }
}
