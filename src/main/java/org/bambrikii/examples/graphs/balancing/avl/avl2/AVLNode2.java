package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AbstractAVLNode;

/**
 * Created by Alexander Arakelyan on 12/02/17 20:39.
 */
public class AVLNode2<T extends AVLNode2> extends AbstractAVLNode<T> {
	private T parent;

	public T getParent() {
		return parent;
	}

	public void setParent(T parent) {
		this.parent = parent;
	}

	public AVLNode2(int value) {
		super(value);
	}

	public AVLNode2(int value, T left, T right) {
		super(value, left, right);
	}
}
