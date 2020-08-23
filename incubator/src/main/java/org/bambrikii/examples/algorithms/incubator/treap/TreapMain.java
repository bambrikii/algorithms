package org.bambrikii.examples.algorithms.incubator.treap;

import org.bambrikii.examples.algorithms.incubator.treeutils.RBTreePrinter;

public class TreapMain {
	public static void main(String[] args) {
		Treap<Integer> treap = new Treap<>();
		treap.add(1, 5);
		RBTreePrinter.print(treap.getRoot());

		treap.add(-3, 4);
		RBTreePrinter.print(treap.getRoot());

		treap.add(5, 3);
		RBTreePrinter.print(treap.getRoot());

		treap.add(-4, 4);
		RBTreePrinter.print(treap.getRoot());

		treap.add(2, 5);
		RBTreePrinter.print(treap.getRoot());
	}
}
