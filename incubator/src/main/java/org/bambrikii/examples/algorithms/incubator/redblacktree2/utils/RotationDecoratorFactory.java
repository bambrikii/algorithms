package org.bambrikii.examples.algorithms.incubator.redblacktree2.utils;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.RotationDecorator;

public class RotationDecoratorFactory {
    private static final RotationDecorator RIGHT_ROTATION_DECORATOR = new RightRotationDecorator();
    private static final RotationDecorator LEFT_ROTATION_DECORATOR = new LeftRotationDecorator();

    private RotationDecoratorFactory() {
    }

    public static <T> RotationDecorator byParent(RBNode<T> x) {
        return x.equals(x.getParent().getLeft())
                ? RIGHT_ROTATION_DECORATOR
                : LEFT_ROTATION_DECORATOR;
    }

    public static <T> RotationDecorator byDirection(int cmp) {
        return cmp > 0
                ? RIGHT_ROTATION_DECORATOR
                : LEFT_ROTATION_DECORATOR;
    }

}
