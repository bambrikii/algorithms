package org.bambrikii.examples.graphs.balancing.avl.avl1;

import org.bambrikii.examples.graphs.balancing.avl.core.AbstractAVLNode;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:53.
 */
public class AVLNode extends AbstractAVLNode<AVLNode> {
	public AVLNode(int value) {
		super(value);
	}

	public AVLNode(int value, AVLNode left, AVLNode right) {
		super(value, left, right);
	}
}