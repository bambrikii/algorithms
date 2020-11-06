package org.bambrikii.examples.graphs.shortestpath;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=lyw4FaxrwHg&ab_channel=WilliamFiset
 * https://www.youtube.com/watch?v=obWXjtg0L64&t=249s&ab_channel=MichaelSambol
 */
public class BellmanFordAlgo2 {
    private final Map<Integer, List<BellmanFordEdge2>> allEdges = new HashMap<>();

    public BellmanFordAlgo2 edge(int from, int to, int weight) {
        if (!this.allEdges.containsKey(from)) {
            this.allEdges.put(from, new ArrayList<>());
        }
        if (!this.allEdges.containsKey(to)) {
            this.allEdges.put(to, new ArrayList<>());
        }
        this.allEdges.get(from).add(new BellmanFordEdge2(from, to, weight));
        return this;
    }

    public Map<Integer, BellmanFordEdge2> find(int from) {
        Map<Integer, BellmanFordEdge2> currEdges = new HashMap<>();
        for (Integer edge : allEdges.keySet()) {
            currEdges.put(edge, new BellmanFordEdge2(null, edge, Integer.MAX_VALUE));
        }
        currEdges.get(from).setWeight(0);

        for (Map.Entry<Integer, List<BellmanFordEdge2>> entry : allEdges.entrySet()) {
            Integer fromVertex = entry.getKey();
            List<BellmanFordEdge2> edges = entry.getValue();
            BellmanFordEdge2 fromCurrEdge = currEdges.get(fromVertex);
            for (BellmanFordEdge2 fromEdge : edges) {
                Integer toVertex = fromEdge.getTo();
                BellmanFordEdge2 toCurrEdge = currEdges.get(toVertex);
                int expectedWeight = fromCurrEdge.getWeight() + fromEdge.getWeight();
                if (expectedWeight < toCurrEdge.getWeight()) {
                    toCurrEdge.setFrom(fromVertex);
                    toCurrEdge.setWeight(expectedWeight);
                }
            }
        }


        for (Map.Entry<Integer, List<BellmanFordEdge2>> entry : allEdges.entrySet()) {
            Integer fromVertex = entry.getKey();
            List<BellmanFordEdge2> edges = entry.getValue();
            BellmanFordEdge2 fromCurrEdge = currEdges.get(fromVertex);
            for (BellmanFordEdge2 fromEdge : edges) {
                Integer toVertex = fromEdge.getTo();
                BellmanFordEdge2 toCurrEdge = currEdges.get(toVertex);
                int expectedWeight = fromCurrEdge.getWeight() + fromEdge.getWeight();
                if (toCurrEdge.getWeight() < expectedWeight) {
//                    toCurrEdge.setFrom(fromVertex);
                    toCurrEdge.setWeight(Integer.MIN_VALUE);
                }
            }
        }

        return currEdges;
    }

    public void print(Map<Integer, BellmanFordEdge2> result) {
        Integer weight = null;
        BellmanFordEdge2 edge = result.get(6);
        System.out.println("Path:");
        while (edge != null) {
            System.out.println(" " + edge);
            if (weight == null) {
                weight = edge.getWeight();
            } else {
                weight += edge.getWeight();
            }
            edge = result.get(edge.getFrom());
        }

        System.out.println("Weight: " + weight);
        System.out.println("All entries:");

        for (Map.Entry<Integer, BellmanFordEdge2> entry : result.entrySet()) {
            System.out.println(" " + entry.getKey() + " -> " + entry.getValue());
        }
    }
}
