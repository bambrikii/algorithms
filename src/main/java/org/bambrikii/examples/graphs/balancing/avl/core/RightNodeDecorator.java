package org.bambrikii.examples.graphs.balancing.avl.core;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:46.
 */
public class RightNodeDecorator<T extends AbstractAVLNode> extends NodeDecorator<T> {

	public RightNodeDecorator(String name) {
		super(name);
	}

	@Override
	public T getLeft(T node) {
		return (T) node.right;
	}

	@Override
	public T getRight(T left) {
		return (T) left.left;
	}

	@Override
	public void setRight(T rightmost, T node) {
		rightmost.left = node;
	}

	@Override
	public void setLeft(T parent, T node) {
		parent.right = node;
	}
}
