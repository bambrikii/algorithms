package org.bambrikii.examples.algorithms.incubator.redblacktree2.utils;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.RotationDecorator;

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
}
