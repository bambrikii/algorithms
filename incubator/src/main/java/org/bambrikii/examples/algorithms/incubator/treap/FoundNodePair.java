package org.bambrikii.examples.algorithms.incubator.treap;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
class FoundNodePair<T extends Comparable<T>> {
	private final TreapNode<T> parent;
	private final TreapNode<T> child;
}
