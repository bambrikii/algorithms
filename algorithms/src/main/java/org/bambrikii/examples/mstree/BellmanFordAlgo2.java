package org.bambrikii.examples.mstree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * https://www.youtube.com/watch?v=obWXjtg0L64&t=249s&ab_channel=MichaelSambol
 */
public class BellmanFordAlgo2 {
    private Map<Integer, List<BellmanFordEdge2>> allEdges = new HashMap<>();

    public BellmanFordAlgo2 edge(int from, int to, int weight) {
        if (!this.allEdges.containsKey(from)) {
            this.allEdges.put(from, new ArrayList<>());
        }
        this.allEdges.get(from).add(new BellmanFordEdge2(from, to, weight));
        return this;
    }

    public Map<Integer, BellmanFordEdge2> find(int from) {
        Map<Integer, BellmanFordEdge2> weights = new LinkedHashMap<>();

        LinkedList<Integer> q = new LinkedList<>();
        q.addLast(from);

        while (!q.isEmpty()) {
            Integer fromVertex = q.poll();
            List<BellmanFordEdge2> edges = allEdges.get(fromVertex);
            if (edges == null) {
                continue;
            }
            BellmanFordEdge2 fromWeight = weights.get(fromVertex);
            for (BellmanFordEdge2 edge : edges) {
                Integer to = edge.getTo();
                Integer w = edge.getWeight();

                if (to == null) {
                    continue;
                }
                BellmanFordEdge2 toW = weights.get(to);
                if (fromWeight == null) {
                    weights.put(to, new BellmanFordEdge2(fromVertex, to, w));
                } else if (toW == null || toW.getWeight() > fromWeight.getWeight() + w) {
                    weights.put(to, new BellmanFordEdge2(fromVertex, to, fromWeight.getWeight() + w));
                }
                q.addLast(to);
            }
        }

        return weights;
    }

    public void print(Map<Integer, BellmanFordEdge2> result) {
        Integer weight = null;
        BellmanFordEdge2 edge = result.get(6);
        while (edge != null) {
            System.out.println(edge);
            if (weight == null) {
                weight = edge.getWeight();
            } else {
                weight += edge.getWeight();
            }
            edge = result.get(edge.getFrom());
        }

        System.out.println("Weight: " + weight);
    }
}
