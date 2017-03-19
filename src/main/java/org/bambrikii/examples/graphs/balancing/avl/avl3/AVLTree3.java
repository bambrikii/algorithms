package org.bambrikii.examples.graphs.balancing.avl.avl3;

import org.bambrikii.examples.graphs.balancing.avl.avl2.AVLTreeListener;
import org.bambrikii.examples.graphs.balancing.avl.avl2.AbstractAVLTree2;
import org.bambrikii.examples.graphs.balancing.avl.core.NodeDecorator;

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
		int leftHeight = node.left != null ? ((AVLNode3) node.left).height + 1 : 0;
		int rightHeight = node.right != null ? ((AVLNode3) node.right).height + 1 : 0;
		int leftHeightDiff = leftHeight - rightHeight;
		return leftHeightDiff;
	}

	private void updateHeightRecursive(AVLNode3 parent) {
		while (parent.getParent() != null) {
			addHeight((AVLNode3) parent.getParent(), parent);
			parent = (AVLNode3) parent.getParent();
		}
	}

	protected void updateHeight(AVLNode3 node) {
		int leftHeight = node.left != null ? ((AVLNode3) node.left).height + 1 : 0;
		int rightHight = node.right != null ? ((AVLNode3) node.right).height + 1 : 0;
		node.height = max(leftHeight, rightHight);
	}

	protected void addHeight(AVLNode3 parent, AVLNode3 node) {
		int newHeight = node.height + 1;
		if (newHeight > parent.height) {
			parent.height = newHeight;
			updateHeightRecursive(parent);
		}
	}

	protected void updateHeightBubbling(AVLNode3 left, AVLNode3 node, NodeDecorator<AVLNode3> nodeDecorator) {
		int newHeight = node.height + 1;
		if (newHeight > left.height) {
			left.height = newHeight;
			updateHeightRecursive(left);
		}
	}

}
