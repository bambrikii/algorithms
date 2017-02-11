package org.bambrikii.examples.graphs.balancing.redblack;

import org.bambrikii.examples.graphs.balancing.TreePrinter;

/**
 * Created by Alexander Arakelyan on 11/02/17 15:39.
 */
public class RedBlackTreePrinter extends TreePrinter<RedBlackNode> {
	@Override
	protected String printImpl(RedBlackNode node) {
		{
			StringBuilder sb = new StringBuilder();
			if (node != null) {
				sb
						.append("(")
						.append(node.value).append(":")
						.append(printImpl(node.left))
						.append(",")
						.append(printImpl(node.right))
						.append(")")
				;
			}
			return sb.toString();
		}
	}
}
