package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;

public class RotationUtils {
    private RotationUtils() {
    }

    public static <T> void rotateUp(RBNode<T> x) {
        RBNode<T> p = x.getParent();
        if (p == null) {
            return;
        }
        RBNode<T> g = p.getParent();

        RotationDecorator rDec = RotationDecoratorFactory.byParent(x);

        RBNode<T> c = rDec.getRight(x);
        x.setParent(g);
        rDec.setRight(x, p);

        p.setParent(x);
        rDec.setLeft(p, c);
    }
}
