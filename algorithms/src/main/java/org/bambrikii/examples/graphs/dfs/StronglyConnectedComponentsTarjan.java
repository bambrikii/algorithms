package org.bambrikii.examples.graphs.dfs;

import java.util.ArrayList;
import java.util.Collection;
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

    public StronglyConnectedComponentsTarjan build() {
        for (Map.Entry<Integer, StronglyConnectedComponentsTarjanNode> entry : nodes.entrySet()) {
            StronglyConnectedComponentsTarjanNode node = entry.getValue();
            if (node.getIndex() == 0) {
                node.setIndex(++index);
            }
            dfs(node);
        }
        return this;
    }

    public Collection<List<StronglyConnectedComponentsTarjanNode>> collect() {
        Map<Integer, List<StronglyConnectedComponentsTarjanNode>> components = new HashMap<>();
        for (StronglyConnectedComponentsTarjanNode node : nodes.values()) {
            int index = node.getIndex();
            if (!components.containsKey(index)) {
                components.put(index, new ArrayList<>());
            }
            components.get(index).add(node);
        }
        return components.values();
    }

    public StronglyConnectedComponentsTarjan print() {
        for (StronglyConnectedComponentsTarjanNode node : nodes.values()) {
            System.out.println(node);
        }
        return this;
    }

    private void dfs(StronglyConnectedComponentsTarjanNode parent) {
        for (StronglyConnectedComponentsTarjanNode child : parent) {
            if (child.getIndex() == 0) {
                child.setIndex(++this.index);
            }
            if (child.getIndex() > parent.getIndex()) {
                dfs(child);
                if (child.getIndex() > parent.getIndex()) {
                    continue;
                }
            }
            int newIndex = child.getIndex();
            markIndexes(parent, newIndex);
            parent.setIndex(newIndex);
//                    System.out.println(" - " + parent + " <- " + child);
        }
    }

    private void markIndexes(StronglyConnectedComponentsTarjanNode parent, int newIndex) {
        int oldIndex = parent.getIndex();
        if (oldIndex == newIndex) {
            return;
        }
        for (StronglyConnectedComponentsTarjanNode child : parent) {
            if (child.getIndex() == oldIndex) {
                child.setIndex(newIndex);
                markIndexes(child, newIndex);
            }
        }
    }

    public void print(Collection<List<org.bambrikii.examples.graphs.dfs.StronglyConnectedComponentsTarjanNode>> result) {
        if (result == null) {
            return;
        }
        for (List<StronglyConnectedComponentsTarjanNode> component : result) {
            for (StronglyConnectedComponentsTarjanNode node : component) {
                System.out.print(" " + node.getVal() + "(" + node.getIndex() + ")");
            }
            System.out.println();
        }
    }
}
