package org.bambrikii.examples.algorithms.incubator.treap;

import org.bambrikii.examples.algorithms.incubator.treeutils.BinaryTreeNode;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(exclude = { "left", "right" })
public class TreapNode<T extends Comparable<T>> implements BinaryTreeNode<T> {
	private T val;
	private int weight;
	private TreapNode<T> left;
	private TreapNode<T> right;
}
