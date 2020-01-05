package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.AbstractRBTree;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;

import java.util.Comparator;

import static org.bambrikii.examples.algorithms.incubator.redblackrtee.RBColorEnum.RED;

public class RBTree2<T> extends AbstractRBTree<T> {
    public RBTree2(Comparator<T> comparator) {
        super(comparator);
    }

    public void insert(T val) {
        RBNode<T> x = insertBefore(val);
        x.setColorRed();
        while (!x.equals(getRoot())
                && (x.getParent() != null
                && x.getParent().getColor().equals(RED)
        )) {
            RBNode<T> p = x.getParent();
            if (p == null) {
                continue;
            }
            if (p.isColorRed()) {
                RBNode<T> g = p.getParent();
                if (g == null) {
                    break;
                }
                RotationDecorator rDec = RotationDecoratorFactory.byParent(x);
                RBNode<T> u = rDec.getRight(g);
                if (u == null || u.isColorBlack()) {
                    if (x.equals(rDec.getRight(p))) {
                        x = p;
                        RotationUtils.rotateUp(x);
                    }
                    p.setColorBlack();
                    g.setColorRed();
                    if (root.equals(g)) {
                        root = p;
                    }
                    RotationUtils.rotateUp(p);
                } else {
                    p.setColorBlack();
                    u.setColorBlack();
                    g.setColorRed();
                }
            }
        }
    }


    public void delete(T val) {

    }
}
