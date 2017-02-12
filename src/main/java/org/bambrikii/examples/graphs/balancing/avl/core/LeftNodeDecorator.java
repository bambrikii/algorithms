package org.bambrikii.examples.graphs.balancing.avl.core;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:47.
 */
public class LeftNodeDecorator<T extends AbstractAVLNode> extends NodeDecorator<T> {

	public LeftNodeDecorator(String name) {
		super(name);
	}

	@Override
	public T getLeft(T node) {
		return (T) node.left;
	}

	@Override
	public T getRight(T node) {
		return (T) node.right;
	}

	@Override
	public void setRight(T rightmost, T node) {
		rightmost.right = node;
	}

	@Override
	public void setLeft(T parent, T node) {
		parent.left = node;
	}
}
