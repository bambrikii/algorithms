package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLNode2;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTreeListener;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AbstractAVLTree2;

/**
 * Created by Alexander Arakelyan on 18/03/17 11:12.
 */
public class AVLTree3 extends AbstractAVLTree2<AVLTree3, AVLNode2> {
	public AVLTree3(AVLTreeListener... listener) {
		super(listener);
	}

	@Override
	protected AVLNode2 createFromValue(int value) {
		return new AVLNode2(value);
	}

	@Override
	protected void balance(AVLNode2 node) {
		throw new UnsupportedOperationException("Not yet implemented.");
	}
}
