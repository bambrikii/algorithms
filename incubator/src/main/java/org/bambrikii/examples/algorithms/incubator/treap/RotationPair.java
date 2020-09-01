package org.bambrikii.examples.algorithms.incubator.treap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class RotationPair<T extends Comparable<T>> {
	private TreapNode<T> rotated;
	private TreapNode<T> parent;
}