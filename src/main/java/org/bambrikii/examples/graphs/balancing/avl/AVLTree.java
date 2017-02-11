package org.bambrikii.examples.graphs.balancing.avl;

import java.util.Comparator;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:55.
 */
public class AVLTree {
	private static final NodeDecorator LEFT_NODE_DECORATOR;
	private static final NodeDecorator RIGHT_NODE_DECORATOR;
	private static final Comparator<AVLNode> COMPARATOR;

	static {
		LEFT_NODE_DECORATOR = new LeftNodeDecorator("left");
		RIGHT_NODE_DECORATOR = new RightNodeDecorator("right");
		LEFT_NODE_DECORATOR.setOther(RIGHT_NODE_DECORATOR);
		RIGHT_NODE_DECORATOR.setOther(LEFT_NODE_DECORATOR);
		COMPARATOR = new AVLNodeComparator();
	}

	public AVLNode root;

	private boolean debug;

	public AVLTree(boolean debug) {
		this.debug = debug;
	}

	public AVLTree add(int value) {
		debug("adding: " + value);
		AVLNode node = new AVLNode(value);
		if (root == null) {
			root = node;
		} else {
			add(root, node);
		}
		return this;
	}

	private void debug(String message) {
		if (debug) {
			System.out.println(message);
		}
	}

	private void add(AVLNode parent, AVLNode node) {
		if (parent != null) {
			parent.height++;
			NodeDecorator nodeDecorator = COMPARATOR.compare(node, parent) < 0 ? LEFT_NODE_DECORATOR : RIGHT_NODE_DECORATOR;
			debug("walking into ", parent, node, nodeDecorator);
			if (nodeDecorator.getLeft(parent) == null) {
				debug("inserting into ", parent, node, nodeDecorator);
				nodeDecorator.setLeft(parent, node);
			} else {
				add(nodeDecorator.getLeft(parent), node);
			}
			balance(parent);
		}
	}

	private void debug(String message, AVLNode parent, AVLNode node, NodeDecorator nodeDecorator) {
		if (debug) {
			System.out.println(message + " parent: " + printNode(parent) + ", node: " + printNode(node) + ". " + nodeDecorator.getName() + ".");
		}
	}

	private String printNode(AVLNode node) {
		return "(" + node.value + "[" + node.height + "])";
	}

	private void balance(AVLNode parent) {
		int leftHeight = parent.left != null ? parent.left.height : 0;
		int rightHeight = parent.right != null ? parent.right.height : 0;
		int diff = leftHeight - rightHeight;
		if (diff > 1) {
			balance(LEFT_NODE_DECORATOR.getLeft(parent));
			rotate(LEFT_NODE_DECORATOR.getLeft(parent), LEFT_NODE_DECORATOR);
		} else if (diff < -1) {
			balance(RIGHT_NODE_DECORATOR.getLeft(parent));
			rotate(RIGHT_NODE_DECORATOR.getLeft(parent), RIGHT_NODE_DECORATOR);
		}
	}

	private void rotate(AVLNode node, NodeDecorator decorator) {
		AVLNode left = decorator.getLeft(node);
		node.height -= left.height;
		if (root == node) {
			root = left;
		}
		while (decorator.getRight(left) != null) {
			left = decorator.getRight(left);
		}
		AVLNode rightmost = left;
		decorator.setRight(rightmost, node);
		rightmost.height += node.height;
	}

}
