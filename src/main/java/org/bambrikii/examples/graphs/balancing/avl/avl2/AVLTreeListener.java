package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

/**
 * Created by Alexander Arakelyan on 12/02/17 21:37.
 */
public interface AVLTreeListener {
	void onAdding(AVLNode2 parent, AVLNode2 node);

	void onAdded(AVLNode2 parent, AVLNode2 node);

	void onBalancing(AVLNode2 node);

	void onBalanced(AVLNode2 node);

	void onRotating(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator);

	void onRotated(AVLNode2 node, NodeDecorator<AVLNode2> nodeDecorator);
}
