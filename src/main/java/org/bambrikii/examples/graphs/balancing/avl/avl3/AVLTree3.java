package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTreeListener;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AbstractAVLTree2;

import static java.lang.Math.max;

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
		int leftHeight = node.left != null ? node.left.height + 1 : 0;
		int rightHeight = node.right != null ? node.right.height + 1 : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		return leftHeightDiff;
	}

	protected void updateHeight(AVLNode3 node) {
		if (node != null) {
			int leftHeight = node.left != null ? node.left.height + 1 : 0;
			int rightHight = node.right != null ? node.right.height + 1 : 0;
			node.height = max(leftHeight, rightHight);
			updateHeight(node.getParent());
		}
	}

}
