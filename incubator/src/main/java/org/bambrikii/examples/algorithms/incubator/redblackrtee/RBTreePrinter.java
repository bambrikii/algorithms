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
public class RBTreePrinter {

    public <T> void print(RBTree<T> tree) {
	RBNode<T> root = tree.getRoot();
	print(root);

    }

    private <T> void print(RBNode<T> n) {
	if (n == null) {
	    return;
	}
	System.out.print(n.getVal() + "[" + n.getColor() + "]");
	System.out.print("(");
	print(n.getLeft());
	System.out.print(" - ");
	print(n.getRight());
	System.out.print(")");
    }
}
