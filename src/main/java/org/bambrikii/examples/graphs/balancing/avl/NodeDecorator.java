package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:46.
 */
abstract class NodeDecorator {
	private final String name;
	private NodeDecorator other;

	public NodeDecorator(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract AVLNode getLeft(AVLNode node);

	public abstract AVLNode getRight(AVLNode left);

	public abstract void setRight(AVLNode rightmost, AVLNode node);

	public abstract void setLeft(AVLNode parent, AVLNode node);

	public void setOther(NodeDecorator other) {
		this.other = other;
	}

	public NodeDecorator getOther() {
		return other;
	}
}
