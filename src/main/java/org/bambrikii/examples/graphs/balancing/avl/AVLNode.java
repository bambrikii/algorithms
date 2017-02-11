package org.bambrikii.examples.graphs.balancing.avl;

import org.bambrikii.examples.graphs.balancing.Node;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:53.
 */
public class AVLNode extends Node<AVLNode> {
	public int height;

	public AVLNode(int value) {
		super(value);
	}

	public AVLNode(int value, AVLNode left, AVLNode right) {
		super(value, left, right);
	}
}