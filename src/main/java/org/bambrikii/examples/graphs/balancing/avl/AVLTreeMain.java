package org.bambrikii.examples.graphs.balancing.avl;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:50.
 */
public class AVLTreeMain {
	public static void main(String[] args) {
		AVLTree tree = new AVLTree(true)
				.add(9)
				.add(12)
				.add(14)
				.add(17)
				.add(19)
				.add(23)
				.add(50)
				.add(54)
				.add(67)
				.add(72)
				.add(76);
		AVLTreePrinter printer = new AVLTreePrinter();
		printer.print(tree.root);
	}
}
