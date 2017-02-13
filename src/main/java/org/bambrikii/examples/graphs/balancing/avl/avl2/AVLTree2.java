package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AVLNodeComparator;
import org.bambrikii.examples.graphs.balancing.avl.core.LeftNodeDecorator;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;
import org.bambrikii.examples.graphs.balancing.avl.core.RightNodeDecorator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Created by Alexander Arakelyan on 12/02/17 18:51.
 */
public class AVLTree2 {
	private static final NodeDecorator LEFT_NODE_DECORATOR;
	private static final NodeDecorator RIGHT_NODE_DECORATOR;

	static {
		LEFT_NODE_DECORATOR = new LeftNodeDecorator("left");
		RIGHT_NODE_DECORATOR = new RightNodeDecorator("right");
		LEFT_NODE_DECORATOR.setOther(RIGHT_NODE_DECORATOR);
		RIGHT_NODE_DECORATOR.setOther(LEFT_NODE_DECORATOR);
	}

	private final Comparator<AVLNode2> comparator;
	private List<AVLTreeListener> listeners = new ArrayList<>();

	public AVLTree2() {
		comparator = new AVLNodeComparator<>();
	}

	public AVLTree2(Comparator<AVLNode2> comparator) {
		this.comparator = comparator;
	}

	public AVLTree2(AVLTreeListener... listeners) {
		this();
		for (AVLTreeListener listener : listeners) {
			this.listeners.add(listener);
		}
	}

	private AVLNode2 root;

	public AVLNode2 getRoot() {
		return root;
	}

	public AVLTree2 add(int value) {
		AVLNode2 node = new AVLNode2(value);
		add(node);
		return this;
	}

	private void add(AVLNode2 node) {
		if (root == null) {
			root = node;
		} else {
			add(root, node);
		}
	}

	private void add(AVLNode2 parent, AVLNode2 node) {
		int compare = comparator.compare(parent, node);
		if (compare < 0) {
			add(parent, node, LEFT_NODE_DECORATOR);
		} else if (compare > 0) {
			add(parent, node, RIGHT_NODE_DECORATOR);
		}
	}

	private void add(AVLNode2 parent, AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		AVLNode2 left = nodeDecorator.getLeft(parent);
		if (left == null) {
			onAdding(parent, node);
			nodeDecorator.setLeft(parent, node);
			node.parent = parent;
			onAdded(parent, node);
		} else {
			onAdding(parent, node);
			add(left, node);
			balance(node);
		}
	}

	private void onAdding(AVLNode2 parent, AVLNode2 node) {
		for (AVLTreeListener listener : listeners) {
			listener.onAdding(parent, node);
		}
	}

	private void onAdded(AVLNode2 parent, AVLNode2 node) {
		for (AVLTreeListener listener : listeners) {
			listener.onAdded(parent, node);
		}
	}

	private void balance(AVLNode2 node) {
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

	private void onBalanced(AVLNode2 node) {
		for (AVLTreeListener listener : listeners) {
			listener.onBalanced(node);
		}
	}

	private void onBalancing(AVLNode2 node) {
		for (AVLTreeListener listener : listeners) {
			listener.onBalancing(node);
		}
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

	private void onRotated(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		for (AVLTreeListener listener : listeners) {
			listener.onRotated(node, nodeDecorator);
		}
	}

	private void onRotating(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		for (AVLTreeListener listener : listeners) {
			listener.onRotating(node, nodeDecorator);
		}
	}
}
