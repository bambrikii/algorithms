package org.bambrikii.examples.graphs.balancing.avl.core;

import org.bambrikii.examples.graphs.balancing.Node;

/**
 * Created by Alexander Arakelyan on 12/02/17 20:40.
 */
public abstract class AbstractAVLNode<T extends AbstractAVLNode> extends Node<T> {
	public int height;

	public AbstractAVLNode(int value) {
		super(value);
	}

	public AbstractAVLNode(int value, T left, T right) {
		super(value, left, right);
	}

	public String toString() {
		return "(" + value + "[" + height + "]:" + (left != null ? left : "?") + "-" + (right != null ? right : "?") + ")";
	}
}
