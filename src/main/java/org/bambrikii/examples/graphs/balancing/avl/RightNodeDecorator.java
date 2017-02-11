package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:46.
 */
class RightNodeDecorator extends NodeDecorator {

	public RightNodeDecorator(String name) {
		super(name);
	}

	@Override
	public AVLNode getLeft(AVLNode node) {
		return node.right;
	}

	@Override
	public AVLNode getRight(AVLNode left) {
		return left.left;
	}

	@Override
	public void setRight(AVLNode rightmost, AVLNode node) {
		rightmost.left = node;
	}

	@Override
	public void setLeft(AVLNode parent, AVLNode node) {
		parent.right = node;
	}
}
