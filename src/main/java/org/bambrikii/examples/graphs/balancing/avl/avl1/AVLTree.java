package org.bambrikii.examples.graphs.balancing.avl.avl1;

import org.bambrikii.examples.graphs.balancing.avl.core.AVLNodeComparator;
import org.bambrikii.examples.graphs.balancing.avl.core.LeftNodeDecorator;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;
import org.bambrikii.examples.graphs.balancing.avl.core.RightNodeDecorator;

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

	private void add(AVLNode parent, AVLNode node) {
		if (parent != null) {
			NodeDecorator<AVLNode> nodeDecorator = COMPARATOR.compare(node, parent) < 0 ? LEFT_NODE_DECORATOR : RIGHT_NODE_DECORATOR;
			debug("walking into ", parent, node, nodeDecorator);
			if (nodeDecorator.getLeft(parent) == null) {
				debug("inserting into ", parent, node, nodeDecorator);
				nodeDecorator.setLeft(parent, node);
			} else {
				add(nodeDecorator.getLeft(parent), node);
			}
		}
	}

	// Balance 1

	public void balance() {
		balance(root);
	}

	private int balance(AVLNode parent) {
		if (parent == null) {
			return 0;
		}
		int heightDiff = getHeightDiff(parent);
		while (heightDiff < -1 || heightDiff > 1) {
			if (heightDiff > 1) {
				debug("balancing " + printNode(parent) + " " + heightDiff + ". " + LEFT_NODE_DECORATOR.getName());
				parent = rotate(parent, LEFT_NODE_DECORATOR);
				heightDiff = balance(parent);
			} else if (heightDiff < -1) {
				debug("balancing " + printNode(parent) + " " + heightDiff + ". " + RIGHT_NODE_DECORATOR.getName());
				parent = rotate(parent, RIGHT_NODE_DECORATOR);
				heightDiff = balance(parent);
			}
		}
		return heightDiff;
	}

	private int getHeightDiff(AVLNode parent) {
		int leftHeight = parent.left != null ? parent.left.height : 0;
		int rightHeight = parent.right != null ? parent.right.height : 0;
		return leftHeight - rightHeight;
	}

	private AVLNode rotate(AVLNode node, NodeDecorator<AVLNode> nodeDecorator) {
		if (node == null) {
			return node;
		}
		AVLNode left = nodeDecorator.getLeft(node);
		debug("rotating ", node, left, nodeDecorator);
		node.height -= (left.height + 1);
		if (root == node) {
			root = left;
		}
		AVLNode rightmost = retrieveRightmost(left, nodeDecorator);
		nodeDecorator.setRight(rightmost, node);
		nodeDecorator.setLeft(node, null);
		rightmost.height += (node.height + 1);
		return left;
	}

	private AVLNode retrieveRightmost(AVLNode left, NodeDecorator<AVLNode> nodeDecorator) {
		AVLNode rightmost = left;
		while (nodeDecorator.getRight(rightmost) != null) {
			rightmost = nodeDecorator.getRight(rightmost);
		}
		return rightmost;
	}

	// Balance 2

	public void balance2() {
		debug("balancing 2");
		balance2(root, root.left, LEFT_NODE_DECORATOR);
		balance2(root, root.right, RIGHT_NODE_DECORATOR);
	}

	private void balance2(AVLNode parent, AVLNode node, NodeDecorator<AVLNode> nodeDecorator) {
		if (node == null) {
			return;
		}
		debug("balancing 2", parent, node, nodeDecorator);
		balance2(node, node.left, LEFT_NODE_DECORATOR);
		balance2(node, node.right, RIGHT_NODE_DECORATOR);
		AVLNode left = nodeDecorator.getLeft(node);
		AVLNode right = nodeDecorator.getRight(node);
		if (left != null && right == null) {
			debug("balancing 2, swapping", node, left, nodeDecorator);
			nodeDecorator.setLeft(parent, left);
			AVLNode rightmost = retrieveRightmost(left, nodeDecorator);
			nodeDecorator.setRight(rightmost, node);
			nodeDecorator.setLeft(node, null);
			node.height -= left.height + 1;
			rightmost.height += node.height + 1;
			left.height += node.height + 1;
		} else if (left == null && right != null) {
			debug("balancing 2, swapping", node, right, nodeDecorator);
			nodeDecorator.setRight(parent, right);
			AVLNode rightmost = retrieveRightmost(left, nodeDecorator.getOther());
			nodeDecorator.setLeft(rightmost, node);
			nodeDecorator.setRight(node, null);
			node.height -= (right.height + 1);
			rightmost.height += node.height + 1;
			right.height += (node.height + 1);
		}
	}

	// Debugging

	private void debug(String message) {
		if (debug) {
			System.out.println(message);
		}
	}

	private void debug(String message, AVLNode parent, AVLNode node, NodeDecorator nodeDecorator) {
		if (debug) {
			System.out.println(message + ": " + printNode(parent) + " -> " + printNode(node) + ". " + nodeDecorator.getName() + ".");
		}
	}

	private String printNode(AVLNode node) {
		return "(" + node.value + "[" + node.height + "])";
	}

}
