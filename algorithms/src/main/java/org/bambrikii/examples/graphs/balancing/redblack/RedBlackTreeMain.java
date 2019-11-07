package org.bambrikii.examples.graphs.balancing.redblack;

/**
 * Created by Alexander Arakelyan on 10/02/17 21:39.
 * <p>
 * https://www.cpp.edu/~ftang/courses/CS241/notes/self%20balance%20bst.htm
 */
public class RedBlackTreeMain {

	private boolean debug;

	private void debug(RedBlackNode node) {
		if (debug) {
			System.out.println(node.value);
		}
	}

	public static void main(String[] args) {
		RedBlackNode root = new RedBlackNode(50,
				new RedBlackNode(17,
						new RedBlackNode(9, null, new RedBlackNode(14, new RedBlackNode(12, null, null), null)),
						new RedBlackNode(23, new RedBlackNode(19), null)),
				new RedBlackNode(76, new RedBlackNode(54, null, new RedBlackNode(72, new RedBlackNode(67), null)),
						null));
		RedBlackTreePrinter printer = new RedBlackTreePrinter();
		RedBlackTreeMain balancing = new RedBlackTreeMain();
		System.out.println("Before balancing:");
		printer.print(root);
		System.out.println();

		System.out.println("After balancing:");
		balancing.balance(root, null);
		printer.print(root);
		System.out.println();
	}

	public void balance(RedBlackNode node, RedBlackNode parent) {
		if (node == null) {
			return;
		}
		debug("balancing tree: ", parent, node);
		if (parent != null) {
			if (node.left != null && node.right == null) {
				changeLeft(parent, node, node.left);
			} else if (node.right != null && node.left == null) {
				changeRight(parent, node.right, node);
			}
		} else {
			balance(node.left, node);
			balance(node.right, node);
		}
	}

	private void debug(String message, RedBlackNode parent, RedBlackNode node) {
		if (debug) {
			System.out.println(message + format(parent) + " -> " + format(node));
		}

	}

	private String format(RedBlackNode node) {
		StringBuilder sb = new StringBuilder();
		sb.append("(").append(node.value + ")");
		return sb.toString();
	}

	private void changeLeft(RedBlackNode parent, RedBlackNode node, RedBlackNode left) {
		parent.left = left;
		if (left.right == null) {
			left.right = node;
		} else {
			changeRight(left, node, left.right);
		}
	}

	private void changeRight(RedBlackNode parent, RedBlackNode node, RedBlackNode right) {
		parent.right = right;
		if (right.left == null) {
			right.left = node;
		} else {
			changeLeft(right, node, right.left);
		}
	}

}
