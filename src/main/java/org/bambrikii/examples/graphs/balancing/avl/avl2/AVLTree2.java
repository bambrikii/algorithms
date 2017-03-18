package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Created by Alexander Arakelyan on 12/02/17 18:51.
 */
public class AVLTree2 extends AbstractAVLTree2<AVLTree2, AVLNode2> {

	public AVLTree2(AVLTreeListener... listeners) {
		super(listeners);
	}

	protected AVLNode2 createFromValue(int value) {
		AVLNode2 node = new AVLNode2(value);
		return node;
	}

	protected void balance(AVLNode2 node) {
		onBalancing(node);
		if (node == null) {
			return;
		}
		int leftHeightDiff = calcHeightDiff(node);
		if (leftHeightDiff > 1) {
			rotate(node, LEFT_NODE_DECORATOR);
		} else if (leftHeightDiff < -1) {
			rotate(node, RIGHT_NODE_DECORATOR);
		}
		balance(node.parent);
		onBalanced(node);
	}

	private int calcHeightDiff(AVLNode2 node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = node.left != null ? abs(calcMaxHeight(node.left)) + 1 : 0;
		int rightHeight = node.right != null ? abs(calcMaxHeight(node.right)) + 1 : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		return leftHeightDiff;
	}

	private int calcMaxHeight(AVLNode2 node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = node.left != null ? abs(calcMaxHeight(node.left)) + 1 : 0;
		int rightHeight = node.right != null ? abs(calcMaxHeight(node.right)) + 1 : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		int multiplier = 1;
		if (leftHeightDiff > 1) {
			multiplier = 1;
		} else if (leftHeightDiff < -1) {
			multiplier = -1;
		}
		return multiplier * max(leftHeight, rightHeight);
	}

	private void rotate(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		onRotating(node, nodeDecorator);
		if (node == null) {
			return;
		}
		// Decrease height of the node
		AVLNode2 left = nodeDecorator.getLeft(node);
		if (node.parent != null) {
			left.parent = node.parent;
			nodeDecorator.setLeft(left.parent, left);
		} else {
			root = left;
			left.parent = null;
		}
		AVLNode2 rightmost = left;
		while (nodeDecorator.getRight(rightmost) != null) {
			rightmost = nodeDecorator.getRight(rightmost);
		}
		nodeDecorator.setRight(rightmost, node);
		node.parent = rightmost;
		nodeDecorator.setLeft(node, null);

		onRotated(node, nodeDecorator);
	}

}
