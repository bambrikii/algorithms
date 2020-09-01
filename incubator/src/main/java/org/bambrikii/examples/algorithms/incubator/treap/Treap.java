package org.bambrikii.examples.algorithms.incubator.treap;

import java.util.Comparator;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.IntegerComparator;
import org.bambrikii.examples.algorithms.incubator.treap.TreapUtils.TreapRotation;

import lombok.Getter;

@Getter
public class Treap<T extends Comparable<T>> {
	private TreapNode<T> root;
	private static final Comparator<Integer> INT_COMPARATOR = new IntegerComparator();
	private static final Comparator<TreapNode<?>> WEIGHT_COMPARATOR = new Comparator<TreapNode<?>>() {
		@Override
		public int compare(TreapNode<?> o1, TreapNode<?> o2) {
			return INT_COMPARATOR.compare(o1.getWeight(), o2.getWeight());
		}

	};

	public void add(T val, int weight) {
		TreapNode<T> newNode = new TreapNode<>();
		newNode.setVal(val);
		newNode.setWeight(weight);
		if (root == null) {
			root = newNode;
		} else {
			int weightDiff = WEIGHT_COMPARATOR.compare(root, newNode);
			if (weightDiff < 0) {
				TreapRotation<T> rotation = TreapUtils.getRotation(newNode, root);
				rotation.addLeftChild(newNode, root);
				root = newNode;
			} else {
				insertDown(root, newNode);
			}
		}
	}

	private void insertDown(TreapNode<T> node, TreapNode<T> newNode) {
		TreapRotation<T> rotation = TreapUtils.getRotation(node, newNode);
		TreapNode<T> child = rotation.getLeftChild(node);
		if (child == null) {
			rotation.addLeftChild(node, newNode);
		} else {
			int weightDiff = WEIGHT_COMPARATOR.compare(child, newNode);
			if (weightDiff < 0) {
				rotation.addLeftChild(node, newNode);
				TreapRotation<T> newRotation = TreapUtils.getRotation(newNode, child);
				newRotation.addLeftChild(newNode, child);
			} else {
				insertDown(child, newNode);
			}
		}
	}

	public TreapNode<T> find(T val) {
		FoundNodePair<T> nodes = find(root, val);
		if (nodes == null) {
			return null;
		}
		return nodes.getChild();
	}

	private FoundNodePair<T> find(TreapNode<T> node, T val) {
		if (node == null) {
			return null;
		}
		TreapNode<T> parentNode = null;
		while (node != null) {
			int valDiff = node.getVal().compareTo(val);
			if (valDiff == 0) {
				return new FoundNodePair<>(parentNode, node);
			}
			parentNode = node;
			if (valDiff < 0) {
				node = node.getLeft();
			} else {
				node = node.getRight();
			}
		}
		return null;
	}

	public TreapNode<T> remove(T val) {
		if (root == null) {
			throw new IllegalArgumentException("Node with value " + val + " is not found!");
		}

		FoundNodePair<T> nodes = find(root, val);
		if (nodes == null) {
			return null;
		}

		TreapNode<T> parent = nodes.getParent();
		TreapNode<T> node = nodes.getChild();
		while (node.getRight() != null || node.getLeft() != null) {
			TreapRotation<T> parentRotation;
			TreapNode<T> oldParent = parent;
			TreapNode<T> rotated;

			TreapNode<T> originalLeft = node.getLeft();
			TreapNode<T> originalRight = node.getRight();

			if (originalLeft != null && originalRight != null) {
				int weightDiff = WEIGHT_COMPARATOR.compare(originalLeft, originalRight);
				if (weightDiff > 0) {
					RotationPair<T> pair = rotateLeft(node);
					rotated = pair.getRotated();
					parent = pair.getParent();
				} else if (weightDiff < 0) {
					RotationPair<T> pair = rotateRight(node);
					rotated = pair.getRotated();
					parent = pair.getParent();
				} else {
					T leftVal = originalLeft.getVal();
					T rightValVal = originalRight.getVal();
					int valDiff = leftVal.compareTo(rightValVal);
					if (valDiff > 0) {
						RotationPair<T> pair = rotateLeft(node);
						rotated = pair.getRotated();
						parent = pair.getParent();
					} else {
						RotationPair<T> pair = rotateRight(node);
						rotated = pair.getRotated();
						parent = pair.getParent();
					}
				}
			} else if (originalLeft != null) {
				RotationPair<T> pair = rotateLeft(node);
				rotated = pair.getRotated();
				parent = pair.getParent();
			} else {
				RotationPair<T> pair = rotateRight(node);
				rotated = pair.getRotated();
				parent = pair.getParent();
			}
			if (oldParent != null) {
				parentRotation = TreapUtils.getRotation(oldParent, node);
				parentRotation.addLeftChild(oldParent, rotated);
			}
			if (node == root) {
				root = rotated;
			}
		}
		if (parent == null) {
			root = null;
		}
		TreapRotation<T> rotation = TreapUtils.getRotation(parent, node);
		rotation.addLeftChild(parent, null);
		return node;
	}

	private RotationPair<T> rotateLeft(TreapNode<T> node) {
		TreapRotation<T> rotation = TreapUtils.getLeftRotation();
		TreapNode<T> left = node.getLeft();
		return new RotationPair<T>(rotation.rotate(node), left);
	}

	private RotationPair<T> rotateRight(TreapNode<T> node) {
		TreapRotation<T> rotation = TreapUtils.getRightRotation();
		TreapNode<T> right = node.getRight();
		return new RotationPair<T>(rotation.rotate(node), right);
	}
}
