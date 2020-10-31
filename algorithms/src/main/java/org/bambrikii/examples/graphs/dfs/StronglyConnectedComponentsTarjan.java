package org.bambrikii.examples.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://iq.opengenus.org/tarjans-algorithm/
 */
public class StronglyConnectedComponentsTarjan {
    private final Map<Integer, StronglyConnectedComponentsTarjanNode> nodes = new HashMap<>();
    private int index;

    private StronglyConnectedComponentsTarjanNode ensureNode(int val) {
        StronglyConnectedComponentsTarjanNode node = nodes.get(val);
        if (node == null) {
            node = new StronglyConnectedComponentsTarjanNode(val);
            nodes.put(val, node);
        }
        return node;
    }

    public StronglyConnectedComponentsTarjan edge(int from, int to) {
        StronglyConnectedComponentsTarjanNode fromNode = ensureNode(from);
        fromNode.add(ensureNode(to));
        return this;
    }

    public List<List<StronglyConnectedComponentsTarjanNode>> list() {
        for (Map.Entry<Integer, StronglyConnectedComponentsTarjanNode> entry : nodes.entrySet()) {
            StronglyConnectedComponentsTarjanNode node = entry.getValue();
            if (node.getIndex() == 0) {
                node.setIndex(++index);

            }
            dfs(node);
        }
        for (StronglyConnectedComponentsTarjanNode node : nodes.values()) {
            System.out.println(node);
        }

        List<List<StronglyConnectedComponentsTarjanNode>> components = new ArrayList<>();
        int prevN = -1;
        for (StronglyConnectedComponentsTarjanNode node : nodes.values()) {
            if (prevN != node.getIndex()) {
                components.add(new ArrayList<>());
                prevN = node.getIndex();
            }
            components.get(components.size() - 1).add(node);
        }
        return components;
    }

    private void dfs(StronglyConnectedComponentsTarjanNode parent) {
        for (StronglyConnectedComponentsTarjanNode child : parent) {
            if (child.getIndex() == 0) {
                child.setIndex(++index);
            }
            if (child.getIndex() <= parent.getIndex()) {
                parent.setIndex(child.getIndex());
//                System.out.println(" - " + parent + " <- " + child);
            } else {
                dfs(child);
                if (child.getIndex() <= parent.getIndex()) {
                    parent.setIndex(child.getIndex());
//                    System.out.println(" - " + parent + " <- " + child);
                }
            }
        }
    }

    public void print(List<List<org.bambrikii.examples.graphs.dfs.StronglyConnectedComponentsTarjanNode>> result) {
        if (result == null) {
            return;
        }
        for (List<StronglyConnectedComponentsTarjanNode> component : result) {
            for (StronglyConnectedComponentsTarjanNode node : component) {
                System.out.print(" " + node.getVal());
            }
            System.out.println();
        }
    }
}
