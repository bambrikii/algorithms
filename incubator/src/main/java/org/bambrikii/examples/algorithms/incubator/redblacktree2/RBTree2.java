package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.AbstractRBTree;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.print.RBTreePrinter;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.utils.RotationDecoratorFactory;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.utils.RotationUtils;

import java.util.Comparator;

import static org.bambrikii.examples.algorithms.incubator.redblackrtee.RBColorEnum.RED;

public class RBTree2<T> extends AbstractRBTree<T> {
    public RBTree2(Comparator<T> comparator) {
        super(comparator);
    }

    public void insert(T val) {
        RBNode<T> x = insertBefore(val);
        x.setColorRed();

        printRoot();

        while (!x.equals(getRoot())
                && (x.getParent() != null
                && x.getParent().getColor().equals(RED)
        )) {
            RBNode<T> p = x.getParent();
            if (p == null) {
                x.setColorBlack();
                break;
            }
            if (!p.isColorRed()) {
                break;
            }
            RBNode<T> g = p.getParent();
            if (g == null) {
                break;
            }
            RotationDecorator rDec = RotationDecoratorFactory.byParent(x);
            RBNode<T> u = rDec.getRight(g);
            if (u == null || u.isColorBlack()) {
                RBNode<T> left = rDec.getLeft(p);
                if (x.equals(left)) {
                    x = RotationUtils.rotateUp(x);
                    x = ensureRoot(p, x);
                }
                p.setColorBlack();
                g.setColorRed();
                RBNode<T> newP = RotationUtils.rotateUp(p);
                ensureRoot(p, newP);
                printRoot();
            } else {
                p.setColorBlack();
                u.setColorBlack();
                g.setColorRed();
            }
        }
    }

    private void printRoot() {
        RBTreePrinter.print(root);
    }

    private RBNode<T> ensureRoot(RBNode<T> previousRoot, RBNode<T> x) {
        if (!root.equals(previousRoot)) {
            return root;
        }
        root = x;
        System.out.println("Setting root: " + x);
        return x;
    }


    public void delete(T val) {

    }
}
