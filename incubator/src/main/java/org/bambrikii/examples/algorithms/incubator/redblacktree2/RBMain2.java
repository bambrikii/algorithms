package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.IntegerComparator;
import org.bambrikii.examples.algorithms.incubator.treeutils.RBTreePrinter;

public class RBMain2 {
	public static void main(String[] args) {
		RBTree2<Integer> tree = new RBTree2<>(new IntegerComparator());
		for (int i = 0; i < 10; i++) {
			tree.insert(i);
		}
		RBTreePrinter.print(tree.getRoot());

		tree.delete(0);
		tree.delete(2);
		tree.delete(4);
		tree.delete(6);
		RBTreePrinter.print(tree.getRoot());
	}
}
