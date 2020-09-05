package org.bambrikii.examples.graphs.rbtree2.utils;

import org.bambrikii.examples.graphs.rbtree.RBNode;
import org.bambrikii.examples.graphs.rbtree2.RotationDecorator;

public class RotationUtils {
	private RotationUtils() {
	}

	public static <T> RBNode<T> rotateUp(RBNode<T> x) {
		RBNode<T> p = x.getParent();
		if (p == null) {
			return p;
		}
		RBNode<T> g = p.getParent();
		RotationDecorator pDec = RotationDecoratorFactory.byParent(x);
		if (g != null) {
			RotationDecorator gDec = RotationDecoratorFactory.byParent(p);
			gDec.setLeft(g, x);
			x.setParent(g);
		} else {
			x.setParent(null);
		}

		RBNode<T> c = pDec.getRight(x);
		pDec.setRight(x, p);
		pDec.setLeft(p, c);

		return x;
	}

	public static <T> RBNode<T> min(RBNode<T> n) {
		RotationDecorator decorator = RotationDecoratorFactory.byParent(n);
		RBNode<T> last = n;
		RBNode<T> left = decorator.getLeft(n);
		while (left != null) {
			last = left;
			left = decorator.getLeft(n);
		}
		return last;
	}

	public static <T> RBNode<T> replace(RBNode<T> what, RBNode<T> with) {
		RBNode<T> whatParent = what.getParent();
		if (whatParent == null) {
			with.setParent(null);
			return with;
		}

		RotationDecorator whatDecorator = RotationDecoratorFactory.byParent(what);
		whatDecorator.setLeft(whatDecorator.getLeft(whatParent), with);

		if (with != null) {
			RBNode<T> withParent = with.getParent();
			withParent.setParent(whatParent);
		}

		return whatParent;
	}
}
