package org.bambrikii.examples.graphs.cycledetection;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * https://www.baeldung.com/java-graph-has-a-cycle
 */
public class CycleDetectionBfs {
    private Set<Integer> vertexes = new HashSet<>();
    private Map<Integer, List<Integer>> edges = new HashMap<>();
    private boolean hasCycles;

    public CycleDetectionBfs edge(int from, int to) {
        vertexes.add(from);
        vertexes.add(to);
        edges.computeIfAbsent(from, (from2) -> new ArrayList<>());
        edges.get(from).add(to);
        return this;
    }

    private boolean log = true;

    private void log(String message) {
        if (!log) {
            return;
        }
        System.out.println(message);
    }

    public boolean print() {
        System.out.println(hasCycles);
        return hasCycles;
    }

    public CycleDetectionBfs find() {
        Set<Integer> visited = new HashSet<>();
        for (Integer vertex : vertexes) {
            if (visited.contains(vertex)) {
                log("Skipping, vertex " + vertex + " has been visited");
                continue;
            }
            if (hasCycle(vertex, visited, new HashSet<>())) {
                hasCycles = true;
                log("Has cycles, vertex " + vertex + " has cycles");
                return this;
            }
        }
        log("Completing, no cycles");
        hasCycles = false;
        return this;
    }

    private boolean hasCycle(Integer from, Set<Integer> visited, Set<Integer> isBeingVisited) {
        if (isBeingVisited.contains(from)) {
            log("Cycle found, vertex " + from + " is being visited.");
            return true;
        }
        isBeingVisited.add(from);
        List<Integer> children = edges.get(from);
        if (children == null || children.isEmpty()) {
            log("No cycles, vertex " + from + " has no outgoing edges.");
            isBeingVisited.remove(from);
            return false;
        }
        for (Integer child : children) {
            log("Trying vertex " + from + "...");
            if (hasCycle(child, visited, isBeingVisited)) {
                log("Cycle found, vertex " + from + " has cycles.");
                isBeingVisited.remove(from);
                return true;
            }
        }
        isBeingVisited.remove(from);
        visited.add(from);
        log("No cycles, vertex " + from + " processing complete.");
        return false;
    }
}
