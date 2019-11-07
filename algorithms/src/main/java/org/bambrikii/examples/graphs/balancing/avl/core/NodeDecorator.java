package org.bambrikii.examples.graphs.balancing.avl.core;

/**
 * Created by Alexander Arakelyan on 11/02/17 16:46.
 */
public abstract class NodeDecorator<T extends AbstractAVLNode> {
	private final String name;
	private NodeDecorator<T> other;

	public NodeDecorator(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public abstract T getLeft(T node);

	public abstract T getRight(T left);

	public abstract void setRight(T rightmost, T node);

	public abstract void setLeft(T parent, T node);

	public void setOther(NodeDecorator<T> other) {
		this.other = other;
	}

	public NodeDecorator getOther() {
		return other;
	}
}
