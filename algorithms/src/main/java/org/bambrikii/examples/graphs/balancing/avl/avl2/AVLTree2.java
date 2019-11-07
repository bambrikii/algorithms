package org.bambrikii.examples.graphs.balancing.avl.avl2;

import static java.lang.Math.abs;
import static java.lang.Math.max;

/**
 * Created by Alexander Arakelyan on 12/02/17 18:51.
 */
public class AVLTree2 extends AbstractAVLTree2<AVLTree2, AVLNode2> {

	protected void init() {
		leftNodeDecorator = new LeftNodeDecorator2<>("left");
		rightNodeDecorator = new RightNodeDecorator2<>("right");
		leftNodeDecorator.setOther(rightNodeDecorator);
		rightNodeDecorator.setOther(leftNodeDecorator);
	}

	public AVLTree2(AVLTreeListener... listeners) {
		super(listeners);
	}

	protected AVLNode2 createFromValue(int value) {
		AVLNode2 node = new AVLNode2(value);
		return node;
	}

	protected int calcHeightDiff(AVLNode2 node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = node.left != null ? abs(calcMaxHeight((AVLNode2) node.left)) + 1 : 0;
		int rightHeight = node.right != null ? abs(calcMaxHeight((AVLNode2) node.right)) + 1 : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		return leftHeightDiff;
	}

	private int calcMaxHeight(AVLNode2 node) {
		if (node == null) {
			return 0;
		}
		int leftHeight = node.left != null ? abs(calcMaxHeight((AVLNode2) node.left)) + 1 : 0;
		int rightHeight = node.right != null ? abs(calcMaxHeight((AVLNode2) node.right)) + 1 : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		int multiplier = 1;
		if (leftHeightDiff > 1) {
			multiplier = 1;
		} else if (leftHeightDiff < -1) {
			multiplier = -1;
		}
		return multiplier * max(leftHeight, rightHeight);
	}
}
