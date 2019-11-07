package org.bambrikii.examples.graphs.balancing.avl.core;

import org.bambrikii.examples.graphs.balancing.TreePrinter;

/**
 * Created by Alexander Arakelyan on 11/02/17 15:37.
 */
public class AVLTreePrinter extends TreePrinter<AbstractAVLNode> {
	@Override
	protected String printImpl(AbstractAVLNode node) {
		StringBuilder sb = new StringBuilder();
		if (node != null) {
			sb
					.append("(")
					.append(node.value).append(":")
					.append(printImpl((AbstractAVLNode) node.left)).append("-").append(printImpl((AbstractAVLNode) node.right))
					.append(")")
			;
		} else {
			sb.append("?");
		}
		return sb.toString();
	}
}
