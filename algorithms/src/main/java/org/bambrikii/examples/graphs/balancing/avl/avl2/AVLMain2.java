package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AVLTreePrinter;

/**
 * Created by Alexander Arakelyan on 12/02/17 21:35.
 */
public class AVLMain2 {
	public static void main(String[] args) {
		AVLTreePrinter printer = new AVLTreePrinter();
		AVLTreeListener listener = new AVLTree2Printer(printer);
		AVLTreeable tree2 = new AVLTree2(listener)
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
		printer.print(tree2.getRoot());
	}

}
