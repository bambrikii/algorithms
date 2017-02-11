package org.bambrikii.examples.graphs.balancing;

/**
 * Prints tree hierarchy.
 *
 * @param <T> tree node type
 */
public abstract class TreePrinter<T extends Node> {

	public void print(T node) {
		if (node != null) {
			System.out.println(printImpl(node));
		}
	}

	protected abstract String printImpl(T node);
}
