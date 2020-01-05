package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;

public class RotationDecoratorFactory {
    private static final RotationDecorator RIGHT_ROTATION_DECORATOR = new RotationDecorator() {
        @Override
        public <T> RBNode<T> getRight(RBNode<T> x) {
            return x.getRight();
        }

        @Override
        public <T> RBNode<T> getLeft(RBNode<T> x) {
            return x.getLeft();
        }

        @Override
        public <T> void setLeft(RBNode<T> x, RBNode<T> l) {
            x.setLeft(l);
        }

        @Override
        public <T> void setRight(RBNode<T> x, RBNode<T> r) {
            x.setRight(r);
        }
    };

    private static final RotationDecorator LEFT_ROTATION_DECORATOR = new RotationDecorator() {
        @Override
        public <T> RBNode<T> getRight(RBNode<T> x) {
            return x.getLeft();
        }

        @Override
        public <T> RBNode<T> getLeft(RBNode<T> x) {
            return x.getRight();
        }

        @Override
        public <T> void setLeft(RBNode<T> x, RBNode<T> l) {
            x.setRight(l);
        }

        @Override
        public <T> void setRight(RBNode<T> x, RBNode<T> r) {
            x.setLeft(r);
        }
    };

    private RotationDecoratorFactory() {
    }

    public static <T> RotationDecorator byParent(RBNode<T> x) {
        RBNode<T> p = x.getParent();
        return x.equals(p.getLeft())
                ? RIGHT_ROTATION_DECORATOR
                : LEFT_ROTATION_DECORATOR;
    }

    public static <T> RotationDecorator byDirection(int cmp) {
        return cmp < 0
                ? RIGHT_ROTATION_DECORATOR
                : LEFT_ROTATION_DECORATOR;
    }
}
