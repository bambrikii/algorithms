package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:47.
 */
class LeftNodeDecorator extends NodeDecorator {

	public LeftNodeDecorator(String name) {
		super(name);
	}

	@Override
	public AVLNode getLeft(AVLNode node) {
		return node.left;
	}

	@Override
	public AVLNode getRight(AVLNode node) {
		return node.right;
	}

	@Override
	public void setRight(AVLNode rightmost, AVLNode node) {
		rightmost.right = node;
	}

	@Override
	public void setLeft(AVLNode parent, AVLNode node) {
		parent.left = node;
	}
}
