package org.bambrikii.examples.algorithms.incubator.redblacktree2;

import org.bambrikii.examples.algorithms.incubator.redblackrtee.AbstractRbTree;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBColorEnum;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.RBNode;
import org.bambrikii.examples.algorithms.incubator.redblackrtee.print.RBTreePrinter;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.utils.RotationDecoratorFactory;
import org.bambrikii.examples.algorithms.incubator.redblacktree2.utils.RotationUtils;

import java.text.MessageFormat;
import java.util.Comparator;

import static org.bambrikii.examples.algorithms.incubator.redblackrtee.RBColorEnum.BLACK;
import static org.bambrikii.examples.algorithms.incubator.redblackrtee.RBColorEnum.RED;

public class RBTree2<T> extends AbstractRbTree<T> {
    public RBTree2(Comparator<T> comparator) {
        super(comparator);
    }

    public void insert(T val) {
        RBNode<T> x = insertBefore(val);
        x.setColorRed();

        while (!x.equals(getRoot()) && (x.getParent() != null && x.getParent().getColor().equals(RED))) {
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

    private RBNode<T> find(T val) {
        RBNode<T> n = root;
        while (n != null) {
            int cmp = comparator.compare(n.getVal(), val);
            if (cmp > 0) {
                n = n.getLeft();
            } else if (cmp < 0) {
                n = n.getRight();
            } else {
                return n;
            }
        }
        throw new IllegalArgumentException(MessageFormat.format("Node with value {0} is not found", val));
    }

    public void delete(T val) {
        RBNode<T> z = find(val);
        RBNode<T> y = z;
        RBColorEnum yOriginalColor = y.getColor();

        RBNode<T> x;
        if (z.getLeft() == null) {
            x = z.getRight();
            replace(z, z.getLeft());
        } else if (z.getRight() == null) {
            x = z.getLeft();
            replace(z, z.getLeft());
        } else {
            y = RotationUtils.min(z.getRight());
            yOriginalColor = y.getColor();
            x = y.getRight();
            if (y.getParent() == z) {
                if (x != null) {
                    x.setParent(y);
                }
            } else {
                RotationUtils.replace(y, y.getRight());
                y.setRight(z.getRight());
                y.getRight().setParent(y);
            }
            RotationUtils.replace(z, y);
            y.setLeft(z.getLeft());
            y.getLeft().setParent(z.getLeft());
            y.setColor(z.getColor());
        }
        if (BLACK.equals(yOriginalColor)) {
            fixDelete(x);
        }
    }

    private void replace(RBNode<T> z, RBNode<T> left) {
        RBNode<T> newRoot = RotationUtils.replace(z, left);
        if (root.equals(z.getParent()) && !root.equals(newRoot)) {
            root = newRoot;
        }
    }

    private void fixDelete(RBNode<T> x) {
        // TODO:
        while (x != root && BLACK.equals(x.getColor())) {
            RotationDecorator rotationDecor = RotationDecoratorFactory.byParent(x);
            RBNode<T> w = rotationDecor.getRight(x.getParent());
            if (RED.equals(w.getColor())) {
                w.setColor(BLACK);
                x.getParent().setColor(RED);
                RotationUtils.rotateUp(w); //
                w = rotationDecor.getRight(x.getParent());
            }
            if (BLACK.equals(rotationDecor.getLeft(w).getColor())
                    && BLACK.equals(rotationDecor.getRight(w).getColor())) {
                w.setColor(RED);
                x = x.getParent();
            } else if (BLACK.equals(rotationDecor.getRight(w).getColor())) {
                rotationDecor.getLeft(w).setColor(BLACK);
                w.setColor(RED);
                RotationUtils.rotateUp(w);
                w = rotationDecor.getRight(x.getParent());

                w.setColor(x.getParent().getColor());
                x.getParent().setColor(BLACK);
                rotationDecor.getRight(w).setColor(BLACK);
                RotationUtils.rotateUp(x.getParent());
                x = root;
            }
        }
        x.setColor(BLACK);
    }
}
