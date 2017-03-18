package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.TreePrinter;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

/**
 * Created by Alexander Arakelyan on 12/02/17 21:50.
 */
public class AVLTree2Printer<T extends AVLNode2> implements AVLTreeListener<T> {
	private TreePrinter<T> printer;

	public AVLTree2Printer(TreePrinter<T> printer) {
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
		System.out.println("on-balance :  " + node);
		System.out.println("on-balanced: " + printer.toString(getRoot((T) node)));
	}

	private T getRoot(T node) {
		if (node == null) {
			return null;
		}
		while (node.getParent() != null) {
			node = (T) node.getParent();
		}
		return node;
	}

	@Override
	public void onRotating(T node, NodeDecorator<T> nodeDecorator) {
		System.out.println("on-rotating:  " + node + "." + nodeDecorator.getName());
	}

	@Override
	public void onRotated(T node, NodeDecorator<T> nodeDecorator) {
		System.out.println("on-rotate :   " + node + "." + nodeDecorator.getName());
		System.out.println("on-rotated:  " + printer.toString(getRoot(node)));
	}
}
