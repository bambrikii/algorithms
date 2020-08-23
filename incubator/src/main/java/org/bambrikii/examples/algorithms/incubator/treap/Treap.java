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
				newNode.setLeft(root.getLeft());
				newNode.setRight(root.getRight());
				root = newNode;
			} else {
				insert(root, newNode);
			}
		}
	}

	private void insert(TreapNode<T> node, TreapNode<T> newNode) {
		int weightDiff = WEIGHT_COMPARATOR.compare(node, newNode);
		if (weightDiff < 0) {
			insertDown(node, newNode);
		} else {
			insertWeighted(node, newNode);
		}
	}

	private void insertDown(TreapNode<T> node, TreapNode<T> newNode) {
		TreapRotation<T> rotation = TreapUtils.getRotation(node, newNode);
		TreapNode<T> child = rotation.getLeftChild(node);
		if (child == null) {
			rotation.addLeftChild(node, newNode);
		} else {
			insert(child, newNode);
		}
	}

	private void insertWeighted(TreapNode<T> node, TreapNode<T> newNode) {
		TreapRotation<T> rotation = TreapUtils.getRotation(node, newNode);
		TreapNode<T> child = rotation.getLeftChild(node);
		if (child == null) {
			rotation.addLeftChild(node, newNode);
		} else {
			int weightDiff = WEIGHT_COMPARATOR.compare(newNode, child);
			if (weightDiff < 0) {
				insertWeighted(child, newNode);
			} else {
				rotation.addLeftChild(node, newNode);
				insertWeighted(newNode, child);
			}
		}
	}

	public void remove(T val) {
		// TODO:
	}
}
