package org.bambrikii.examples.algorithms.incubator.redblackrtee;

import org.bambrikii.examples.algorithms.incubator.redblacktree2.RotationDecorator;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.utils.RotationDecoratorFactory;

import java.util.Comparator;

public abstract class AbstractRbTree<T> {
    protected RBNode<T> root;
    protected final Comparator<T> comparator;

    public AbstractRbTree(Comparator<T> comparator) {
        this.comparator = comparator;
    }

    public RBNode<T> getRoot() {
        return root;
    }

    public abstract void insert(T val);

    public abstract void delete(T val);

    protected RBNode<T> insertBefore(T val) {
        if (root == null) {
            root = new RBNode<T>(val);
            return root;
        }
        return insert(root, val);
    }

    private RBNode<T> insert(RBNode<T> p, T val) {
        int cmp = comparator.compare(val, p.getVal());
        if (cmp == 0) {
            throw new IllegalArgumentException("Node's " + p + " value is equal to " + val + "! But shouldn't!");
        }
        RotationDecorator rotationDecorator = RotationDecoratorFactory.byDirection(cmp);
        if (rotationDecorator.getRight(p) == null) {
            RBNode<T> x = new RBNode<>(val);
            rotationDecorator.setRight(p, x);
            return x;
        }
        return insert(rotationDecorator.getRight(p), val);
    }
}
