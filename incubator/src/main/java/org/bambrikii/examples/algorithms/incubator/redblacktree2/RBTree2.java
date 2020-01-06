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
            RotationDecorator gDec = RotationDecoratorFactory.byParent(x);
            RBNode<T> u = g != null ? gDec.getRight(g) : null;
            if (u == null || u.isColorBlack()) {
                RotationUtils.rotateUp(p);
                p.setColorBlack();
                if (g != null) {
                    g.setColorRed();
                }
                x.setColorRed();
                ensureRoot(g, p);
                x = p;
                printRoot();
            } else {
                p.setColorBlack();
                if (u != null) {
                    u.setColorBlack();
                }
                if (g != null) {
                    g.setColorRed();
                }
            }
        }
    }

    private void printRoot() {
        RBNode<T> node = root;
//        while (node.getParent() != null) {
//            node = node.getParent();
//        }
        RBTreePrinter.print(node);
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
