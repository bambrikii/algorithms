package org.bambrikii.examples.graphs.balancing.avl;

import org.bambrikii.examples.graphs.balancing.TreePrinter;

/**
 * Created by Alexander Arakelyan on 11/02/17 15:37.
 */
public class AVLTreePrinter extends TreePrinter<AVLNode> {
	@Override
	protected String printImpl(AVLNode node) {
		StringBuilder sb = new StringBuilder();
		if (node != null) {
			sb
					.append("(")
					.append(node.value).append("[").append(node.height).append("]:")
					.append(printImpl(node.left)).append(",").append(printImpl(node.right))
					.append(")")
			;
		}
		return sb.toString();
	}
}
