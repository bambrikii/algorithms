package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:55.
 */
public class AVLTree {
	private static final NodeDecorator LEFT_NODE_DECORATOR = new LeftNodeDecorator();
	private static final NodeDecorator RIGHT_NODE_DECORATOR = new RightNodeDecorator();

	public AVLNode root;

	public AVLTree add(int value) {
		AVLNode node = new AVLNode(value);
		if (root == null) {
			root = node;
		} else {
			add(root, node);
		}
		return this;
	}

	private void add(AVLNode parent, AVLNode node) {
		if (parent.value > node.value) {
			addLeft(parent, node);
		} else {
			addRight(parent, node);
		}
	}

	private void addLeft(AVLNode parent, AVLNode node) {
		if (parent != null) {
			parent.height++;
			if (parent.left == null) {
				parent.left = node;
			} else {
				if (parent.left.value < node.value) {
					addRight(parent.left, node);
				} else {
					addLeft(parent.left, node);
				}
			}
			balance(parent);
		}
	}

	private void addRight(AVLNode parent, AVLNode node) {
		if (parent != null) {
			parent.height++;
			if (parent.right == null) {
				parent.right = node;
			} else {
				if (parent.right.value < node.value) {
					addRight(parent.right, node);
				} else {
					addLeft(parent.right, node);
				}
			}
		}
	}

	private void balance(AVLNode parent) {
		int leftHeight = parent.left != null ? parent.left.height : 0;
		int rightHeight = parent.right != null ? parent.right.height : 0;
		int diff = leftHeight - rightHeight;
		if (diff > 1) {
			rotate(parent, RIGHT_NODE_DECORATOR);
			balance(parent);
		} else if (diff < -1) {
			rotate(parent, LEFT_NODE_DECORATOR);
			balance(parent);
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

//	private void rotateRight(AVLNode node) {
//		AVLNode left = node.left;
//		node.height -= left.height;
//		if (root == node) {
//			root = left;
//		}
//		while (left.right != null) {
//			left = left.right;
//		}
//		AVLNode rightmost = left;
//		rightmost.right = node;
//		rightmost.height += node.height;
//	}

}
