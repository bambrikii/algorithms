package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:46.
 */
abstract class NodeDecorator {
	public abstract AVLNode getLeft(AVLNode node);

	public abstract AVLNode getRight(AVLNode left);

	public abstract void setRight(AVLNode rightmost, AVLNode node);
}
