package org.bambrikii.examples.algorithms.incubator.treap;

public class TreapUtils {
	private TreapUtils() {
	}

	interface TreapRotation<T extends Comparable<T>> {
		TreapNode<T> getLeftChild(TreapNode<T> node);

		TreapNode<T> addLeftChild(TreapNode<T> node, TreapNode<T> child);
	}

	static class LeftTreapRotation<T extends Comparable<T>> implements TreapRotation<T> {
		@Override
		public TreapNode<T> getLeftChild(TreapNode<T> node) {
			return node.getLeft();
		}

		@Override
		public TreapNode<T> addLeftChild(TreapNode<T> node, TreapNode<T> child) {
			node.setLeft(child);
			return child;
		}
	}

	static class RightTreapRotation<T extends Comparable<T>> implements TreapRotation<T> {
		@Override
		public TreapNode<T> getLeftChild(TreapNode<T> node) {
			return node.getRight();
		}

		@Override
		public TreapNode<T> addLeftChild(TreapNode<T> node, TreapNode<T> child) {
			node.setRight(child);
			return child;
		}
	}

	public static <T extends Comparable<T>> TreapRotation<T> getLeftRotation() {
		return new LeftTreapRotation<>();
	}

	public static <T extends Comparable<T>> TreapRotation<T> getRightRotation() {
		return new RightTreapRotation<>();
	}

	public static <T extends Comparable<T>> TreapRotation<T> getRotation(TreapNode<T> node, TreapNode<T> val) {
		return getRotation(node.getVal(), val.getVal());
	}

	public static <T extends Comparable<T>> TreapRotation<T> getRotation(TreapNode<T> node, T val) {
		return getRotation(node.getVal(), val);
	}

	public static <T extends Comparable<T>> TreapRotation<T> getRotation(T node, T val) {
		int compareTo = node.compareTo(val);
		if (compareTo == 0) {
			throw new IllegalArgumentException("value " + val + " already exists!");
		}
		return compareTo < 0 ? getLeftRotation() : getRightRotation();
	}
}
