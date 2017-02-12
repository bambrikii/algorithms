package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AbstractAVLNode;

/**
 * Created by Alexander Arakelyan on 12/02/17 20:39.
 */
public class AVLNode2 extends AbstractAVLNode<AVLNode2> {
	public AVLNode2 parent;

	public AVLNode2(int value) {
		super(value);
	}

	public AVLNode2(int value, AVLNode2 left, AVLNode2 right) {
		super(value, left, right);
	}
}
