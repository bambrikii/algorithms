/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bambrikii.examples.algorithms.incubator.redblackrtee;

/**
 *
 * @author asd
 */
public class RBMain {

    public static void main(String[] args) {
	RBTree<Integer> tree = new RBTree<>(new IntegerComparator());
	for (int i = 0; i < 10; i++) {
	    tree.insert(i);
	}
	RBTreePrinter printer = new RBTreePrinter();
	printer.print(tree);

	for (int i = 3; i < 6; i++) {
	    tree.delete(i);
	}
	printer.print(tree);
    }
}
