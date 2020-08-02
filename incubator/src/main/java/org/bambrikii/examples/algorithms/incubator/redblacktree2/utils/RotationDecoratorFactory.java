package org.bambrikii.examples.algorithms.incubator.redblacktree2.utils;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.RotationDecorator;

import java.text.MessageFormat;

public class RotationDecoratorFactory {
    private static final RotationDecorator RIGHT_ROTATION_DECORATOR = new RightRotationDecorator();
    private static final RotationDecorator LEFT_ROTATION_DECORATOR = new LeftRotationDecorator();

    private RotationDecoratorFactory() {
    }

    private static <T> RBNode<T> validateParent(RBNode<T> x) {
        RBNode<T> p = x.getParent();
        if (p == null) {
            throw new IllegalArgumentException(MessageFormat.format("Cannot define direction of ", x));
        }
        return p;
    }

    public static <T> RotationDecorator byParent(RBNode<T> x) {
        RBNode<T> p = validateParent(x);
        return x.equals(p.getLeft())
                ? RIGHT_ROTATION_DECORATOR
                : LEFT_ROTATION_DECORATOR;
    }

    public static <T> RotationDecorator byDirection(int cmp) {
        return cmp > 0
                ? RIGHT_ROTATION_DECORATOR
                : LEFT_ROTATION_DECORATOR;
    }

}
