package org.bambrikii.examples.graphs.treap;

import static org.bambrikii.examples.graphs.treeutils.RBTreePrinter.print;

public class TreapMain {
	public static void main(String[] args) {
		printAdding();
		Treap<Integer> treap = new Treap<>();
		treap.add(1, 5);
		print(treap.getRoot());

		printAdding();
		treap.add(-3, 4);
		print(treap.getRoot());

		printAdding();
		treap.add(5, 3);
		print(treap.getRoot());

		printAdding();
		treap.add(-4, 4);
		print(treap.getRoot());

		printAdding();
		treap.add(2, 5);
		print(treap.getRoot());

		printSearching();
		System.out.println(treap.find(5));

		printRemoving();
		System.out.println(treap.remove(1));
		print(treap.getRoot());

		printRemoving();
		System.out.println(treap.remove(2));
		print(treap.getRoot());

		printRemoving();
		System.out.println(treap.remove(5));
		print(treap.getRoot());
	}

	private static void printRemoving() {
		System.out.println("--- Removing ---");
	}

	private static void printSearching() {
		System.out.println("--- Searching ---");
	}

	private static void printAdding() {
		System.out.println("--- Adding ---");
	}
}
