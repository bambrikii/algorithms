package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.TreePrinter;

/**
 * Created by Alexander Arakelyan on 11/02/17 15:37.
 */
public class AVLTree3Printer extends TreePrinter<AVLNode3> {
	@Override
	protected String printImpl(AVLNode3 node) {
		StringBuilder sb = new StringBuilder();
		if (node != null) {
			sb
					.append("(")
					.append(node.value).append(".").append(node.height).append(":")
					.append(printImpl((AVLNode3) node.left)).append("-").append(printImpl((AVLNode3) node.right))
					.append(")")
			;
		} else {
			sb.append("?");
		}
		return sb.toString();
	}
}
