/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.bambrikii.examples.graphs.rbtree;

import static org.bambrikii.examples.graphs.rbtree.RBColorEnum.BLACK;
import static org.bambrikii.examples.graphs.rbtree.RBColorEnum.RED;

import java.util.Comparator;

/**
 * @author asd
 */
public class RBTree<T> extends AbstractRbTree<T> {
    public RBTree(Comparator<T> comparator) {
        super(comparator);
    }

    // Insert
    public void insert(T val) {
        RBNode<T> newNode = insertBefore(val);
        insertBalance(newNode);
    }

    private void insertBalance(RBNode<T> n) {
        if (n.getParent() == null) {
            insertCase1(n);
        } else if (n.getParent().getColor() == RBColorEnum.BLACK) {
            insertCase2(n);
        } else if (getUncle(n) != null && getUncle(n).getColor() == RBColorEnum.RED) {
            insertCase3(n);
        } else /*if (n.getParent().getParent() != null)*/ {
            insertCase4(n);
        }
    }

    private void insertCase1(RBNode<T> n) {
        if (n.getParent() == null) {
            n.setColor(BLACK);
        }
    }

    private void insertCase2(RBNode<T> n) {
        return;
    }

    private void insertCase3(RBNode<T> n) {
        n.setColor(BLACK);
        getUncle(n).setColor(BLACK);
        n.getParent().getParent().setColor(RBColorEnum.RED);
        insertBalance(n.getParent().getParent());
    }

    private void insertCase4(RBNode<T> n) {
        RBNode<T> p = n.getParent();
        RBNode<T> g = n.getParent().getParent();

        if (n == p.getRight() && p == g.getLeft()) {
            rotateLeft(p);
            n = n.getLeft();
        } else if (n == p.getLeft() && p == g.getRight()) {
            rotateRight(p);
            n = n.getRight();
        }

        insertCase4Step2(n);
    }

    private void insertCase4Step2(RBNode<T> n) {
        RBNode<T> p = n.getParent();
        RBNode<T> g = n.getParent().getParent();

        if (n == p.getLeft()) {
            rotateRight(g);
        } else {
            rotateLeft(g);
        }
        p.setColor(BLACK);
        g.setColor(RED);
    }

    private RBNode<T> getUncle(RBNode<T> n) {
        RBNode<T> p = n.getParent();
        if (p == null) {
            return null;
        }
        RBNode<T> g = p.getParent();
        if (g == null) {
            return null;
        }
        return g.getLeft() == p ? g.getRight() : g.getLeft();
    }

    // Rotate
    protected void rotateRight(RBNode<T> n) {
        RBNode<T> nnew = n.getLeft();
        RBNode<T> p = n.getParent();
        // assert(nnew != nullptr);  // Since the leaves of a red-black tree are empty,
        // they cannot become internal nodes.

        n.setLeft(nnew.getRight());
        nnew.setRight(n);
        n.setParent(nnew);

        // Handle other child/parent pointers.
        if (n.getLeft() != null) {
            n.getLeft().setParent(n);
        }

        // Initially n could be the root.
        if (p != null) {
            if (n.equals(p.getLeft())) {
                p.setLeft(nnew);
            } else if (n.equals(p.getRight())) {
                p.setRight(nnew);
            }
        }
        nnew.setParent(p);
    }

    protected void rotateLeft(RBNode<T> n) {
        RBNode<T> nnew = n.getRight();
        RBNode<T> p = n.getParent();
// assert (nnew != null); // TODO: Since the leaves of a red-black tree are empty, they cannot become internal nodes.
        n.setRight(nnew.getLeft());
        nnew.setLeft(n);
        n.setParent(nnew);
        // Handle other child/parent pointers.
        if (n.getRight() != null) {
            n.getRight().setParent(n);
        }

        // Initially n could be the root.
        if (p != null) {
            if (n.equals(p.getLeft())) {
                p.setLeft(nnew);
            } else if (n.equals(p.getRight())) {
                p.setRight(nnew);
            }
        }
        nnew.setParent(p);
    }

    // Find
    public RBNode<T> find(T val) {
        if (root == null) {
            return null;
        }
        return find(root, val);
    }

    private RBNode<T> find(RBNode<T> node, T val) {
        int cmp = comparator.compare(val, node.getVal());
        if (cmp == 0) {
            return root;
        }
        if (cmp < 0) {
            return find(node.getLeft(), val);
        } else {
            return find(node.getRight(), val);
        }
    }

    // Delete
    public void delete(T val) {
        RBNode<T> node = find(val);
        if (node == null) {
            return;
        }
        delete(node);
    }

    public void delete(RBNode<T> n) {
        // Precondition: n has at most one non-leaf child.
        RBNode<T> child = (n.getRight() == null) ? n.getLeft() : n.getRight();
//	assert (child);

        replaceNode(n, child);
        if (n.getColor() == BLACK) {
            if (child.getColor() == RED) {
                child.setColor(BLACK);
            } else {
                deleteCase1(child);
            }
        }
//	free(n);
    }

    private void replaceNode(RBNode<T> n, RBNode<T> child) {
        child.setParent(n.getParent());
        if (n.equals(n.getParent().getLeft())) {
            n.getParent().setLeft(child);
        } else {
            n.getParent().setRight(child);
        }
    }

    private void deleteCase1(RBNode<T> n) {
        if (n.getParent() != null) {
            deleteCase2(n);
        }
    }

    private void deleteCase2(RBNode<T> n) {
        RBNode<T> s = getSibling(n);

        if (s.getColor() == RED) {
            n.getParent().setColor(RED);
            ;
            s.setColor(BLACK);
            if (n.equals(n.getParent().getLeft())) {
                rotateLeft(n.getParent());
            } else {
                rotateRight(n.getParent());
            }
        }
        deleteCase3(n);
    }

    private void deleteCase3(RBNode<T> n) {
        RBNode<T> s = getSibling(n);

        if ((n.getParent().getColor() == BLACK)
                && (s.getColor() == BLACK)
                && (s.getLeft().getColor() == BLACK)
                && (s.getRight().getColor() == BLACK)) {
            s.setColor(RED);
            deleteCase1(n.getParent());
        } else {
            deleteCase4(n);
        }
    }

    private void deleteCase4(RBNode<T> n) {
        RBNode<T> s = getSibling(n);

        if ((n.getParent().getColor() == RED)
                && (s.getColor() == BLACK)
                && (s.getLeft().getColor() == BLACK)
                && (s.getRight().getColor() == BLACK)) {
            s.setColor(RED);
            n.getParent().setColor(BLACK);
        } else {
            deleteCase5(n);
        }
    }

    private void deleteCase5(RBNode<T> n) {
        RBNode<T> s = getSibling(n);

        // This if statement is trivial, due to case 2 (even though case 2 changed
        // the sibling to a sibling's child, the sibling's child can't be red, since
        // no red parent can have a red child).
        if (s.getColor() == BLACK) {
            // The following statements just force the red to be on the left of the
            // left of the parent, or right of the right, so case six will rotate
            // correctly.
            if ((n == n.getParent().getLeft())
                    && (s.getRight().getColor() == BLACK)
                    && (s.getLeft().getColor() == RED)) {
                // This last test is trivial too due to cases 2-4.
                s.setColor(RED);
                s.getLeft().setColor(BLACK);
                rotateRight(s);
            } else if ((n == n.getParent().getRight())
                    && (s.getLeft().getColor() == BLACK)
                    && (s.getRight().getColor() == RED)) {
                // This last test is trivial too due to cases 2-4.
                s.setColor(RED);
                s.getRight().setColor(BLACK);
                rotateLeft(s);
            }
        }
        deleteCase6(n);
    }

    private void deleteCase6(RBNode<T> n) {
        RBNode<T> s = getSibling(n);
        s.setColor(n.getParent().getColor());
        n.getParent().setColor(BLACK);

        if (n.equals(n.getParent().getLeft())) {
            s.getRight().setColor(BLACK);
            rotateLeft(n.getParent());
        } else {
            s.getLeft().setColor(BLACK);
            rotateRight(n.getParent());
        }
    }

    private RBNode<T> getSibling(RBNode<T> n) {
        RBNode<T> p = n.getParent();

        // No parent means no sibling.
        if (p == null) {
            return null;
        }

        if (n == p.getLeft()) {
            return p.getRight();
        } else {
            return p.getLeft();
        }
    }

}
