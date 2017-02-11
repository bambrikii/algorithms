package org.bambrikii.examples.graphs.balancing;

/**
 * Created by Alexander Arakelyan on 11/02/17 10:52.
 */
public class Node<T extends Node> {
	public final int value;
	public T left;
	public T right;

	public Node(int value) {
		this.value = value;
	}

	public Node(int value, T left, T right) {
		this.value = value;
		if (left != null) {
			this.left = left;
		}
		if (right != null) {
			this.right = right;
		}
	}
}
