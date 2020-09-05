package org.bambrikii.examples.graphs.rbtree2;

import org.bambrikii.examples.graphs.rbtree.RBNode;

public interface RotationDecorator {
	<T> RBNode<T> getRight(RBNode<T> x);

	<T> RBNode<T> getLeft(RBNode<T> x);

	<T> void setLeft(RBNode<T> x, RBNode<T> l);

	<T> void setRight(RBNode<T> x, RBNode<T> r);
}
