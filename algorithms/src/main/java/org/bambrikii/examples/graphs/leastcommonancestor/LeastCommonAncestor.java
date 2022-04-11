package org.bambrikii.examples.graphs.leastcommonancestor;

public class LeastCommonAncestor {
    private static class Node {
        private final Integer val;
        private final Node lt;
        private final Node gt;

        Node(int val) {
            this(val, null, null);
        }

        Node(int val, Node lt, Node gt) {
            this.val = val;
            this.lt = lt;
            this.gt = gt;
        }
    }

    public static void main(String[] args) {
        Node root = new Node(9,
                new Node(5,
                        new Node(3,
                                new Node(2),
                                new Node(4)
                        ),
                        new Node(7,
                                new Node(6),
                                new Node(8)
                        )
                ),
                new Node(11,
                        new Node(10),
                        new Node(12)
                )
        );

        print(root, 2, 4); // 3
        print(root, 4, 6); // 5
    }

    private static void print(Node root, int val1, int val2) {
        System.out.println(new LeastCommonAncestor().find(root, val1, val2));
    }

    private int find(Node root, int val1, int val2) {
        while (true) {
            if (root.val > val1 && root.val > val2) {
                root = root.lt;
            } else if (root.val < val1 && root.val < val2) {
                root = root.gt;
            } else if (root != null) {
                return root.val;
            } else {
                throw new IllegalArgumentException("Binary tree is not correct");
            }
        }
    }
}
