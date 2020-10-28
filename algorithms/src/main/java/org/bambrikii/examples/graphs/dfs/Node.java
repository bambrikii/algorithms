package org.bambrikii.examples.graphs.dfs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Node {
    private int val;
    private List<Node> children = new ArrayList<>();

    public Node(int val, Node... children) {
        this.val = val;
        for (Node child : children) {
            this.children.add(child);
        }
    }
}
