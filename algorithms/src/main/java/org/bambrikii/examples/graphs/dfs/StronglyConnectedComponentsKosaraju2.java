package org.bambrikii.examples.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.programiz.com/dsa/strongly-connected-components
 * <p>
 * The main ideal is to build:
 * 1. stack of traversal with child-to-parent order (topological sort),
 * 2. child-to-parent tree (reverse graph) where traversal from child to parent will be confined within component
 * and won't be possible to other components as no reverse path will allow it.
 */
public class StronglyConnectedComponentsKosaraju2 {
    private Map<Integer, Node> nodesMap = new HashMap<>();

    public StronglyConnectedComponentsKosaraju2 edge(int val1, int val2) {
        Node node1 = ensureNode(val1);
        Node node2 = ensureNode(val2);
        node1.getChildren().add(node2);
        return this;
    }

    private Node ensureNode(int val) {
        Node node = nodesMap.get(val);
        if (node == null) {
            node = new Node(val);
            nodesMap.put(val, node);
        }
        return node;
    }

    public List<List<Node>> list() {
        LinkedList<Node> stack = buildStack();
        Map<Integer, Node> reverseNodes = createReverseChildToParentTree();
        List<List<Node>> components = buildComponents(stack, reverseNodes);
        return components;
    }

    private Map<Integer, Node> createReverseChildToParentTree() {
        Set<Integer> visited = new HashSet<>();
        Map<Integer, Node> reverseNodes = new HashMap<>();
        for (Node parent : nodesMap.values()) {
            int val = parent.getVal();
            Node parent2 = reverseNodes.get(val);
            if (parent2 == null) {
                parent2 = new Node(val);
                reverseNodes.put(val, parent2);
            }
            if (visited.contains(parent2)) {
                continue;
            }
            createReverseChildToParentTree(reverseNodes, parent, visited);
        }
        return reverseNodes;
    }

    private void createReverseChildToParentTree(Map<Integer, Node> reverseNodes, Node parent, Set<Integer> visited) {
        for (Node child : parent.getChildren()) {
            int childVal = child.getVal();
            Node child2 = reverseNodes.get(childVal);
            if (child2 == null) {
                child2 = new Node(childVal);
                reverseNodes.put(childVal, child2);
            }
            child2.getChildren().add(reverseNodes.get(parent.getVal()));
            if (visited.contains(childVal)) {
                continue;
            }
            visited.add(childVal);
            createReverseChildToParentTree(reverseNodes, child, visited);
        }
    }

    private LinkedList<Node> buildStack() {
        Set<Integer> visited = new HashSet<>();
        LinkedList<Node> stack = new LinkedList<>();
        for (Node node : nodesMap.values()) {
            int nodeVal = node.getVal();
            if (visited.contains(nodeVal)) {
                continue;
            }
            visited.add(nodeVal);
            buildStackDfs(visited, node, stack);
            stack.addLast(node);
        }
        return stack;
    }

    private void buildStackDfs(Set<Integer> visited, Node parent, LinkedList<Node> stack) {
        for (Node child : parent.getChildren()) {
            int childVal = child.getVal();
            if (visited.contains(childVal)) {
                continue;
            }
            visited.add(childVal);
            buildStackDfs(visited, parent, stack);
            stack.addLast(child);
        }
    }

    private List<List<Node>> buildComponents(LinkedList<Node> stack, Map<Integer, Node> reverseNodes) {
        Set<Integer> visited = new HashSet<>();
        List<List<Node>> components = new ArrayList<>();
        while (!stack.isEmpty()) {
            Node stackNode = stack.poll();
            if (visited.contains(stackNode.getVal())) {
                continue;
            }
            components.add(new ArrayList<>());
            createComponent(visited, stackNode.getVal(), components, reverseNodes);
        }
        return components;
    }

    private void createComponent(Set<Integer> visited, int stackNode, List<List<Node>> components, Map<Integer, Node> reverseNodes) {
        Node reverse = reverseNodes.get(stackNode);
        int reverseVal = reverse.getVal();
        if (!visited.contains(reverseVal)) {
            visited.add(reverseVal);
            components.get(components.size() - 1).add(reverse);
        }
        visited.add(reverseVal);
        for (Node child : reverse.getChildren()) {
            int childVal = child.getVal();
            if (visited.contains(childVal)) {
                continue;
            }
            createComponent(visited, childVal, components, reverseNodes);
        }
    }

    public void print(List<List<Node>> components) {
        for (List<Node> component : components) {
            for (Node node : component) {
                System.out.print(" " + node.getVal());
            }
            System.out.println();
        }
    }
}
