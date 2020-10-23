package org.bambrikii.examples.graphs.bfs;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Getter
@AllArgsConstructor
@ToString
public class Edge<T> {
    private Vertex<T> from;
    private Vertex<T> to;
    private int weight;
}
