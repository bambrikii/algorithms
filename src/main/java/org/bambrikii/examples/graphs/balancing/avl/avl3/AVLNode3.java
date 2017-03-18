package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLNode2;

/**
 * Created by Alexander Arakelyan on 18/03/17 13:54.
 */
public class AVLNode3 extends AVLNode2 {
	public int height = 0;

	public AVLNode3(int value) {
		super(value);
	}

	public String toString() {
		return "(" + value + "." + height + ":" + (left != null ? left : "?") + "-" + (right != null ? right : "?") + ")";
	}
}
