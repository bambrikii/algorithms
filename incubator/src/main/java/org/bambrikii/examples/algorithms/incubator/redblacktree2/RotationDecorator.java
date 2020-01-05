package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;

public interface RotationDecorator {
    <T> RBNode<T> getRight(RBNode<T> x);

    <T> RBNode<T> getLeft(RBNode<T> x);

    <T> void setLeft(RBNode<T> x, RBNode<T> l);

    <T> void setRight(RBNode<T> x, RBNode<T> r);
}
