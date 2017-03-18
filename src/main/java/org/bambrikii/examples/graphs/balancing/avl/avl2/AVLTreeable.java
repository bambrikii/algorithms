package org.bambrikii.examples.graphs.balancing.avl.avl2;

import org.bambrikii.examples.graphs.balancing.avl.core.AbstractAVLNode;

/**
 * Created by Alexander Arakelyan on 18/03/17 11:13.
 */
public interface AVLTreeable<E extends AVLTreeable, T extends AbstractAVLNode> {
	AbstractAVLNode getRoot();

	E add(int value);
}
