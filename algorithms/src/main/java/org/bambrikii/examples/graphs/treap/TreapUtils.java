package org.bambrikii.examples.graphs.treap;

public class TreapUtils {
	private TreapUtils() {
	}

	interface TreapRotation<T extends Comparable<T>> {
		TreapNode<T> getLeftChild(TreapNode<T> node);

		TreapNode<T> addLeftChild(TreapNode<T> node, TreapNode<T> child);

		TreapNode<T> rotate(TreapNode<T> parent);
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

		public TreapNode<T> rotate(TreapNode<T> parent) {
			if (parent == null) {
				return null;
			}
			TreapNode<T> left = parent.getLeft();
			if (left == null) {
				return null;
			}
			TreapNode<T> right = left.getRight();
			left.setRight(parent);
			parent.setLeft(right);
			return left;
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

		public TreapNode<T> rotate(TreapNode<T> parent) {
			if (parent == null) {
				return null;
			}

			TreapNode<T> right = parent.getRight();
			if (right == null) {
				return null;
			}
			TreapNode<T> left = right.getLeft();
			right.setLeft(parent);
			parent.setRight(left);
			return right;
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
