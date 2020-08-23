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

	public void remove(T val) {
		// TODO:
	}
}
