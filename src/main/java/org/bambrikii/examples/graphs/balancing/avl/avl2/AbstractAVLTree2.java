package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AVLNodeComparator;
import org.bambrikii.examples.graphs.balancing.avl.core.LeftNodeDecorator;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;
import org.bambrikii.examples.graphs.balancing.avl.core.RightNodeDecorator;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

/**
 * Created by Alexander Arakelyan on 18/03/17 11:15.
 */
public abstract class AbstractAVLTree2<E extends AVLTreeable, T extends AVLNode2> implements AVLTreeable<E, T> {
	protected static final NodeDecorator LEFT_NODE_DECORATOR;
	protected static final NodeDecorator RIGHT_NODE_DECORATOR;
	protected final Comparator<T> comparator;
	protected List<AVLTreeListener> listeners = new ArrayList<>();

	static {
		LEFT_NODE_DECORATOR = new LeftNodeDecorator("left");
		RIGHT_NODE_DECORATOR = new RightNodeDecorator("right");
		LEFT_NODE_DECORATOR.setOther(RIGHT_NODE_DECORATOR);
		RIGHT_NODE_DECORATOR.setOther(LEFT_NODE_DECORATOR);
	}

	protected T root;

	public AbstractAVLTree2() {
		comparator = new AVLNodeComparator<>();
	}

	public AbstractAVLTree2(Comparator<T> comparator) {
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
			add(parent, node, LEFT_NODE_DECORATOR);
		} else if (compare > 0) {
			add(parent, node, RIGHT_NODE_DECORATOR);
		}
	}

	protected void add(T parent, T node, NodeDecorator<T> nodeDecorator) {
		T left = nodeDecorator.getLeft(parent);
		if (left == null) {
			onAdding(parent, node);
			nodeDecorator.setLeft(parent, node);
			node.parent = parent;
			onAdded(parent, node);
		} else {
			onAdding(parent, node);
			add(left, node);
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

	protected abstract void balance(T node);


	protected void onBalanced(AVLNode2 node) {
		for (AVLTreeListener listener : listeners) {
			listener.onBalanced(node);
		}
	}

	protected void onBalancing(AVLNode2 node) {
		for (AVLTreeListener listener : listeners) {
			listener.onBalancing(node);
		}
	}

	protected void onRotated(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		for (AVLTreeListener listener : listeners) {
			listener.onRotated(node, nodeDecorator);
		}
	}

	protected void onRotating(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator) {
		for (AVLTreeListener listener : listeners) {
			listener.onRotating(node, nodeDecorator);
		}
	}

}
