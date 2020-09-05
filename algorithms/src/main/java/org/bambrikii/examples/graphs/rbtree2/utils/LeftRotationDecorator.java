package org.bambrikii.examples.graphs.rbtree2.utils;

import org.bambrikii.examples.graphs.rbtree.RBNode;
import org.bambrikii.examples.graphs.rbtree2.RotationDecorator;

class LeftRotationDecorator implements RotationDecorator {
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
        if (l != null) {
            l.setParent(x);
        }
        if (x != null) {
            x.setRight(l);
        }
    }

    @Override
    public <T> void setRight(RBNode<T> x, RBNode<T> r) {
        if (r != null) {
            r.setParent(x);
        }
        if (x != null) {
            x.setLeft(r);
        }
    }
}
