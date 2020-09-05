package org.bambrikii.examples.graphs.rbtree;

import org.bambrikii.examples.graphs.treeutils.RBTreePrinter;

public class RBMain {
	public static void main(String[] args) {
		RBTree<Integer> tree = new RBTree<>(new IntegerComparator());
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		RBTreePrinter.print(tree.getRoot());

		for (int i = 3; i < 6; i++) {
			tree.delete(i);
		}
		RBTreePrinter.print(tree.getRoot());
	}
}
