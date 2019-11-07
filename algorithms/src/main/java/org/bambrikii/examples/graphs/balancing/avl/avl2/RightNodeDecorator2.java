package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AbstractAVLNode;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:46.
 */
public class RightNodeDecorator2<T extends AbstractAVLNode> extends NodeDecorator<T> {

	public RightNodeDecorator2(String name) {
		super(name);
	}

	@Override
	public T getLeft(T node) {
		return (T) node.right;
	}

	@Override
	public T getRight(T node) {
		return (T) node.left;
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
