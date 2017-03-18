package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTree2Printer;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTreeListener;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTreeable;

/**
 * Created by Alexander Arakelyan on 18/03/17 11:11.
 */
public class AVLMain3 {
	public static void main(String[] args) {
		AVLTree3Printer printer = new AVLTree3Printer();
		AVLTreeListener listener = new AVLTree2Printer(printer);
		AVLTreeable tree2 = new AVLTree3(listener)
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
		printer.print((AVLNode3) tree2.getRoot());
	}
}
