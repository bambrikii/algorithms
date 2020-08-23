/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bambrikii.examples.algorithms.incubator.redblackrtee;

import static org.bambrikii.examples.algorithms.incubator.redblackrtee.RBColorEnum.RED;

import org.bambrikii.examples.algorithms.incubator.treeutils.BinaryTreeNode;

/**
 * @author asd
 */
public class RBNode<T> implements BinaryTreeNode<T> {

	private final T val;
	private RBColorEnum color;

	private RBNode<T> parent;
	private RBNode<T> left;
	private RBNode<T> right;

	public RBNode(T val) {
		this.val = val;
	}

	public T getVal() {
		return val;
	}

	public void setColorBlack() {
		this.color = RBColorEnum.BLACK;
	}

	public void setColorRed() {
		this.color = RED;
	}

	public boolean isColorBlack() {
		return RBColorEnum.BLACK.equals(this.color);
	}

	public boolean isColorRed() {
		return RED.equals(this.color);
	}

	public RBNode<T> getParent() {
		return parent;
	}

	public void setParent(RBNode<T> parent) {
		this.parent = parent;
	}

	public RBNode<T> getLeft() {
		return left;
	}

	public void setLeft(RBNode<T> left) {
		this.left = left;
	}

	public RBNode<T> getRight() {
		return right;
	}

	public void setRight(RBNode<T> right) {
		this.right = right;
	}

	public RBColorEnum getColor() {
		return color;
	}

	public void setColor(RBColorEnum color) {
		this.color = color;
	}

	public String toString() {
		return val + "[" + RBColorEnum.toString(color) + "]";
	}
}
