package org.bambrikii.examples.graphs.balancing.redblack;

public class RedBlackTreeInsert {
    private boolean debug;

    private void debug(RedBlackNode node) {
        if (debug) {
            System.out.println(node.value);
        }
    }

    public void balance(RedBlackNode node, RedBlackNode parent) {
        if (node == null) {
            return;
        }
        debug("balancing tree: ", parent, node);
        if (parent != null) {
            if (node.left != null && node.right == null) {
                changeLeft(parent, node, node.left);
            } else if (node.right != null && node.left == null) {
                changeRight(parent, node.right, node);
            }
        } else {
            balance(node.left, node);
            balance(node.right, node);
        }
    }

    private void debug(String message, RedBlackNode parent, RedBlackNode node) {
        if (debug) {
            System.out.println(message + format(parent) + " -> " + format(node));
        }

    }

    private String format(RedBlackNode node) {
        StringBuilder sb = new StringBuilder();
        sb.append("(").append(node.value + ")");
        return sb.toString();
    }

    private void changeLeft(RedBlackNode parent, RedBlackNode node, RedBlackNode left) {
        parent.left = left;
        if (left.right == null) {
            left.right = node;
        } else {
            changeRight(left, node, left.right);
        }
    }

    private void changeRight(RedBlackNode parent, RedBlackNode node, RedBlackNode right) {
        parent.right = right;
        if (right.left == null) {
            right.left = node;
        } else {
            changeLeft(right, node, right.left);
        }
    }

}
