package org.bambrikii.examples.algorithms.incubator.redblacktree2.utils;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.RotationDecorator;

class RightRotationDecorator implements RotationDecorator {
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
        if (l != null) {
            l.setParent(x);
        }
        if (x != null) {
            x.setLeft(l);
        }
    }

    @Override
    public <T> void setRight(RBNode<T> x, RBNode<T> r) {
        if (r != null) {
            r.setParent(x);
        }
        if (x != null) {
            x.setRight(r);
        }
    }
}
