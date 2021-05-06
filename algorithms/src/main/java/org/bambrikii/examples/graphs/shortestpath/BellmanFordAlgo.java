package org.bambrikii.examples.graphs.shortestpath;

import java.text.MessageFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class BellmanFordAlgo {
    public static void main(String[] args) {
        new BellmanFordAlgo()
                .edge(1, 2, 1)
                .edge(1, 2, 2)
                .edge(1, 3, 3)
                .edge(2, 4, 4)
                .edge(2, 5, 5)
                .edge(3, 6, 6)
                .calc(1)
                .print();
    }


    private final Map<Integer, Map<Integer, Integer>> edges = new HashMap<>();

    private Integer from;
    private Map<Integer, Integer> weights;

    public BellmanFordAlgo edge(Integer from, Integer to, Integer weight) {
        if (!edges.containsKey(from)) {
            edges.put(from, new HashMap<>());
        }
        edges.get(from).put(to, weight);
        return this;
    }

    public BellmanFordAlgo print() {
        for (Entry<Integer, Integer> weight : weights.entrySet()) {
            System.out.println(MessageFormat.format("{0} -> {1} ({2})",
                    from, weight.getKey(), weight.getValue()
            ));
        }
        return this;
    }

    private void init(Integer from, Map<Integer, Integer> weights) {
        for (Entry<Integer, Map<Integer, Integer>> froms : edges.entrySet()) {
            weights.put(froms.getKey(), Integer.MAX_VALUE);
            for (Entry<Integer, Integer> tos : froms.getValue().entrySet()) {
                weights.put(tos.getKey(), Integer.MAX_VALUE);
            }
        }
        weights.put(from, 0);
    }

    private void relax(Map<Integer, Integer> weights) {
        int card = weights.size();
        for (int i = 1; i < card; i++) {
            for (Entry<Integer, Map<Integer, Integer>> froms : edges.entrySet()) {
                Integer from = froms.getKey();
                for (Entry<Integer, Integer> tos : froms.getValue().entrySet()) {
                    relax(weights, from, tos);
                }
            }
        }
    }

    private void relax(Map<Integer, Integer> weights, Integer from, Entry<Integer, Integer> tos) {
        Integer to = tos.getKey();
        Integer fromWeight = weights.get(from);
        Integer toWeight = weights.get(to);
        if (fromWeight == Integer.MAX_VALUE) {
            return;
        }
        Integer toWeightExpected = weights.get(from) + tos.getValue();
        if (toWeight == Integer.MAX_VALUE && toWeight > toWeightExpected) {
            weights.put(to, toWeightExpected);
        }
    }

    private void verify(Map<Integer, Integer> weights) {
        for (Entry<Integer, Map<Integer, Integer>> froms : edges.entrySet()) {
            Integer from = froms.getKey();
            for (Entry<Integer, Integer> tos : froms.getValue().entrySet()) {
                if (weights.get(tos.getKey()) > weights.get(from) + tos.getValue()) {
                    throw new IllegalArgumentException("Graph contains negative weight cycle");
                }
            }
        }
    }

    public BellmanFordAlgo calc(Integer from) {
        Map<Integer, Integer> weights = new HashMap<>();
        init(from, weights);
        relax(weights);
        verify(weights);
        this.from = from;
        this.weights = weights;
        return this;
    }
}
