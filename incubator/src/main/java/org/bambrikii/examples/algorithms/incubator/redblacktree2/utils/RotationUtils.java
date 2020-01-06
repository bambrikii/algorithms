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

        RotationDecorator gDec = RotationDecoratorFactory.byParent(p);
        RotationDecorator pDec = RotationDecoratorFactory.byParent(x);

        RBNode<T> c = pDec.getRight(x);
        x.setParent(g);
        gDec.setLeft(g, x);

        pDec.setRight(x, p);
        p.setParent(x);
        pDec.setLeft(p, c);

        return x;
    }
}
