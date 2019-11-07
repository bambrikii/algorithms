package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AbstractAVLNode;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

/**
 * Created by Alexander Arakelyan on 12/02/17 21:37.
 */
public interface AVLTreeListener<T extends AbstractAVLNode> {
	void onAdding(T parent, T node);

	void onAdded(T parent, T node);

	void onBalancing(T node);

	void onBalanced(T node);

	void onRotating(T node, NodeDecorator<T> nodeDecorator);

	void onRotated(T node, NodeDecorator<T> nodeDecorator);
}
