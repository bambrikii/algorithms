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
            if (g == null) {
                break;
            }
            RotationDecorator gDec = RotationDecoratorFactory.byParent(x);
            RBNode<T> u = gDec.getRight(g);
            if (u == null || u.isColorBlack()) {
//                RotationDecorator pDec = RotationDecoratorFactory.byParent(x);
//                RBNode<T> left = pDec.getLeft(p);
//                if (x.equals(left)) {
//                    RotationUtils.rotateUp(x);
//                    ensureRoot(p, x);
//                    printRoot();
//                }
                p.setColorBlack();
                g.setColorRed();
                RotationUtils.rotateUp(p);
                ensureRoot(g, p);
                printRoot();
            } else {
                p.setColorBlack();
                u.setColorBlack();
                g.setColorRed();
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
