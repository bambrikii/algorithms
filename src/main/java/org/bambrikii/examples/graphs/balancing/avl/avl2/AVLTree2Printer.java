package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AVLTreePrinter;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

/**
 * Created by Alexander Arakelyan on 12/02/17 21:50.
 */
class AVLTree2Printer implements AVLTreeListener {
	private AVLTreePrinter printer;

	public AVLTree2Printer(AVLTreePrinter printer) {
		this.printer = printer;
	}

	@Override
	public void onAdding(AVLNode2 parent, AVLNode2 node) {
		System.out.println("on-adding:    " + parent.value + " <- " + node.value);
	}

	@Override
	public void onAdded(AVLNode2 parent, AVLNode2 node) {
		System.out.println("on-added:     " + parent + " <- " + node);
	}

	@Override
	public void onBalancing(AVLNode2 node) {
		System.out.println("on-balancing: " + node);
//		System.out.println("on-balancing*:" + printer.toString(getRoot(node)));
	}

	@Override
	public void onBalanced(AVLNode2 node) {
		System.out.println("on-balanced:  " + node);
		System.out.println("on-balanced*: " + printer.toString(getRoot(node)));
	}

	private AVLNode2 getRoot(AVLNode2 node) {
		if (node == null) {
			return null;
		}
		while (node.parent != null) {
			node = node.parent;
		}
		return node;
	}

	@Override
	public void onRotating(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		System.out.println("on-rotating:  " + node + "." + nodeDecorator.getName());
	}

	@Override
	public void onRotated(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		System.out.println("on-rotated:   " + node + "." + nodeDecorator.getName());
		System.out.println("on-rotated*:  " + printer.toString(getRoot(node)));
	}
}
