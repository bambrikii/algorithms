package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTreeListener;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AbstractAVLTree2;

/**
 * Created by Alexander Arakelyan on 18/03/17 11:12.
 */
public class AVLTree3 extends AbstractAVLTree2<AVLTree3, AVLNode3> {

	protected void init() {
		leftNodeDecorator = new LeftNodeDecorator3("left");
		rightNodeDecorator = new RightNodeDecorator3("right");
		leftNodeDecorator.setOther(rightNodeDecorator);
		rightNodeDecorator.setOther(leftNodeDecorator);
	}

	public AVLTree3(AVLTreeListener... listener) {
		super(listener);
	}

	@Override
	protected AVLNode3 createFromValue(int value) {
		return new AVLNode3(value);
	}

	protected int calcHeightDiff(AVLNode3 node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = node.left != null ? ((AVLNode3) node.left).height : 0;
		int rightHeight = node.right != null ? ((AVLNode3) node.right).height : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		return leftHeightDiff;
	}

	@Override
	protected void onAdding(AVLNode3 parent, AVLNode3 node) {
		super.onAdding(parent, node);
		parent.height += node.height;
	}

	protected void updateHeight(AVLNode3 left, AVLNode3 node) {
		node.height -= left.height;
		left.height += node.height;
	}

	protected void updateHeight2(AVLNode3 left, AVLNode3 node) {
		left.height += node.height;
	}

}
