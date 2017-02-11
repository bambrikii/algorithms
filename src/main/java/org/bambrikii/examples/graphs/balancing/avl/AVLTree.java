package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:55.
 */
public class AVLTree {
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

}
