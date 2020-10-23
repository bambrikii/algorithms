package org.bambrikii.examples.graphs.bfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class BFS<T> {
    private final Map<T, Vertex<T>> vertexes = new HashMap<>();
    private final Map<Vertex<T>, List<Edge<T>>> fromEdges = new HashMap<>();

    public BFS<T> edge(T valFrom, T valTo) {
        edge(valFrom, valTo, 0);
        return this;
    }

    public BFS<T> edge(T valFrom, T valTo, int weight) {
        Vertex<T> vFrom = ensureVertex(valFrom);
        Vertex<T> vTo = ensureVertex(valTo);
        ensureEdge(vFrom, vTo, weight);
        return this;
    }

    private Vertex<T> ensureVertex(T val) {
        Vertex<T> result;
        if (vertexes.containsKey(val)) {
            result = vertexes.get(val);
        } else {
            result = new Vertex<>();
            result.setVal(val);
            vertexes.put(val, result);
        }
        return result;
    }

    private Edge<T> ensureEdge(Vertex<T> vFrom, Vertex<T> vTo, int weight) {
        Edge<T> edge = new Edge<T>(vFrom, vTo, weight);
        if (!fromEdges.containsKey(vFrom)) {
            ArrayList<Edge<T>> edges = new ArrayList<>();
            fromEdges.put(vFrom, edges);
        }
        fromEdges.get(vFrom).add(edge);
        return edge;
    }

    private BFSDistance<T> createResult(BFSDistance<T> prev, Edge<T> edge, int weight) {
        BFSDistance<T> reverse = new BFSDistance<>();
        reverse.setPrev(prev);
        reverse.setEdge(edge);
        reverse.setWeight(weight);
        return reverse;
    }

    public BFSDistance<T> find(T valFrom, T valTo) {
        Map<Vertex<T>, BFSDistance<T>> distances = new HashMap<>();

        LinkedList<Edge<T>> queue = new LinkedList<>();

        Vertex<T> shortestDistanceVertex = vertexes.get(valFrom);
        Vertex<T> targetVertex = vertexes.get(valTo);
        BFSDistance<T> startingDistance = createResult(null, null, 0);
        distances.put(shortestDistanceVertex, startingDistance);

        List<Edge<T>> startingEdges = fromEdges.get(shortestDistanceVertex);
        for (Edge<T> edge : startingEdges) {
            queue.add(edge);
            Vertex<T> toVertex = edge.getTo();
            distances.put(toVertex, createResult(startingDistance, edge, edge.getWeight()));
            if (targetVertex.equals(toVertex)) {
                shortestDistanceVertex = toVertex;
            }
        }

        while (queue.size() > 0) {
            Edge<T> edge = queue.pollFirst();

            Vertex<T> vFrom = edge.getFrom();
            Vertex<T> vTo = edge.getTo();

            int thisEdgeWeight = edge.getWeight();

            BFSDistance<T> fromDistance = distances.get(vFrom);

            int newWeight = thisEdgeWeight + (fromDistance != null ? fromDistance.getWeight() : 0);

            if (distances.containsKey(vTo)) {
                BFSDistance<T> toDistance = distances.get(vTo);
                int currentWeight = toDistance.getWeight();
                if (newWeight < currentWeight) {
                    toDistance.setWeight(newWeight);
                    toDistance.setEdge(edge);
                    toDistance.setPrev(fromDistance);
                    shortestDistanceVertex = vTo;
                }
            }
            List<Edge<T>> edges = fromEdges.get(vTo);
            if (edges != null) {
                for (Edge<T> edge2 : edges) {
                    queue.add(edge2);
                    if (!distances.containsKey(edge2.getTo())) {
                        distances.put(edge2.getTo(), createResult(fromDistance, edge2, newWeight + edge2.getWeight()));
                    }
                }
            }
        }
        return distances.get(shortestDistanceVertex);
    }
}
