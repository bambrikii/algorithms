package org.bambrikii.examples.graphs.coloring;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * https://iq.opengenus.org/welsh-powell-algorithm/
 */
public class WelshPowellAlgo {
    private final Map<Integer, Set<Integer>> graph = new HashMap<>();

    private final Map<Integer, Set<Integer>> vertexes = new HashMap<>();

    public WelshPowellAlgo edge(int from, int to) {
        ensureEdge(from, to);
        ensureEdge(to, from);
        return this;
    }

    private void ensureEdge(int from, int to) {
        graph.computeIfAbsent(from, (ket) -> new HashSet<>());
        graph.get(from).add(to);
    }

    public Map<Integer, Integer> color() {
        Map<Integer, Integer> colors = new HashMap<>();
        int count = graph.size();
        List<Integer> vertexes = sortVertexes();

        for (int color = 0; color < count; color++) {
            color(color, colors, vertexes);
        }
        return colors;
    }

    public void print(Map<Integer, Integer> colors) {
        for (Map.Entry<Integer, Integer> color : colors.entrySet()) {
            System.out.println(" " + color.getKey() + " -> " + color.getValue());
        }
    }

    private void color(int color, Map<Integer, Integer> colors, List<Integer> vertexes) {
        Set<Integer> skip = new HashSet<>();
        for (Integer vertex : vertexes) {
            if (colors.containsKey(vertex)) {
                continue;
            }
            if (skip.contains(vertex)) {
                continue;
            }
            skip.addAll(graph.get(vertex));
            colors.put(vertex, color);
        }
    }


    private List<Integer> sortVertexes() {
        return graph.entrySet()
                .stream()
                .sorted(Comparator.comparingInt(l -> -l.getValue().size()))
                .map(e -> e.getKey())
                .collect(Collectors.toList());
    }

    public static void main(String[] args) {
        WelshPowellAlgo algo = new WelshPowellAlgo()
                .edge(1, 2)
                .edge(2, 3)
                .edge(3, 4)
                .edge(3, 5)
                .edge(4, 6)
                .edge(3, 7)
                .edge(4, 5)
                .edge(4, 7)
                .edge(4, 8);

        algo.print(algo.color());
    }
}
