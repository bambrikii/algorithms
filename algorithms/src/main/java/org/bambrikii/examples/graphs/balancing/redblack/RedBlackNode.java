package org.bambrikii.examples.graphs.balancing.redblack;

import org.bambrikii.examples.graphs.balancing.Node;

/**
 * Created by Alexander Arakelyan on 11/02/17 11:19.
 */
public class RedBlackNode extends Node<RedBlackNode> {
	public boolean balanced;

	public RedBlackNode(int value) {
		super(value);
	}

	public RedBlackNode(int value, RedBlackNode left, RedBlackNode right) {
		super(value, left, right);
	}
}
