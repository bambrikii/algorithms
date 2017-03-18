package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AVLNodeComparator;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18/03/17 11:15.
 */
public abstract class AbstractAVLTree2<E extends AbstractAVLTree2, T extends AVLNode2> implements AVLTreeable<E, T> {
	protected NodeDecorator<T> leftNodeDecorator;
	protected NodeDecorator<T> rightNodeDecorator;
	protected final Comparator<T> comparator;
	protected List<AVLTreeListener> listeners = new ArrayList<>();

	protected abstract void init();

	protected T root;

	public AbstractAVLTree2() {
		init();
		comparator = new AVLNodeComparator<>();
	}

	public AbstractAVLTree2(Comparator<T> comparator) {
		init();
		this.comparator = comparator;
	}


	public AbstractAVLTree2(AVLTreeListener... listeners) {
		this();
		for (AVLTreeListener listener : listeners) {
			this.listeners.add(listener);
		}
	}

	@Override
	public T getRoot() {
		return root;
	}

	@Override
	public E add(int value) {
		T node = createFromValue(value);
		add(node);
		return (E) this;
	}

	protected abstract T createFromValue(int value);

	protected void add(T node) {
		if (root == null) {
			root = node;
		} else {
			add(root, node);
		}
	}

	protected void add(T parent, T node) {
		int compare = comparator.compare(parent, node);
		if (compare < 0) {
			add(parent, node, leftNodeDecorator);
		} else if (compare > 0) {
			add(parent, node, rightNodeDecorator);
		}
	}

	protected void add(T parent, T node, NodeDecorator<T> nodeDecorator) {
		T left = nodeDecorator.getLeft(parent);
		if (left == null) {
			onAdding(parent, node);
			nodeDecorator.setLeft(parent, node);
			node.setParent(parent);
			onAdded(parent, node);
			addHeight(parent, node);
		} else {
			onAdding(parent, node);
			add(left, node);
			addHeight(parent, node);
			balance(node);
		}
	}

	protected void onAdding(T parent, T node) {
		for (AVLTreeListener listener : listeners) {
			listener.onAdding(parent, node);
		}
	}

	protected void onAdded(T parent, T node) {
		for (AVLTreeListener listener : listeners) {
			listener.onAdded(parent, node);
		}
	}

	protected void balance(T node) {
		onBalancing(node);
		if (node == null) {
			return;
		}
		int leftHeightDiff = calcHeightDiff(node);
		if (leftHeightDiff > 1) {
			rotate(node, leftNodeDecorator);
		} else if (leftHeightDiff < -1) {
			rotate(node, rightNodeDecorator);
		}
		balance((T) node.getParent());
		onBalanced(node);
	}

	protected abstract int calcHeightDiff(T node);

	protected void onBalanced(T node) {
		for (AVLTreeListener listener : listeners) {
			listener.onBalanced(node);
		}
	}

	protected void onBalancing(T node) {
		for (AVLTreeListener listener : listeners) {
			listener.onBalancing(node);
		}
	}

	protected void onRotated(T node, NodeDecorator<T> nodeDecorator) {
		for (AVLTreeListener listener : listeners) {
			listener.onRotated(node, nodeDecorator);
		}
	}

	protected void onRotating(T node, NodeDecorator<T> nodeDecorator) {
		for (AVLTreeListener listener : listeners) {
			listener.onRotating(node, nodeDecorator);
		}
	}

	protected void rotate(T node, NodeDecorator<T> nodeDecorator) {
		onRotating(node, nodeDecorator);
		if (node == null) {
			return;
		}
		// Decrease height of the node
		T left = nodeDecorator.getLeft(node);
		if (node.getParent() != null) {
			left.setParent(node.getParent());
			nodeDecorator.setLeft((T) left.getParent(), left);
		} else {
			root = left;
			left.setParent(null);
		}
		updateHeightRotation(left, node, nodeDecorator);
		T rightmost = left;
		while (nodeDecorator.getRight(rightmost) != null) {
			rightmost = nodeDecorator.getRight(rightmost);
			updateHeightBubbling(rightmost, node, nodeDecorator);
		}
		nodeDecorator.setRight(rightmost, node);
		node.setParent(rightmost);
		nodeDecorator.setLeft(node, null);

		onRotated(node, nodeDecorator);
	}

	protected void addHeight(T parent, T node) {

	}

	protected void updateHeightRotation(T left, T node, NodeDecorator<T> nodeDecorator) {

	}

	protected void updateHeightBubbling(T left, T node, NodeDecorator<T> nodeDecorator) {

	}

}
