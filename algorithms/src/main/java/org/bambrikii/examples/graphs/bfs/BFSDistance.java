package org.bambrikii.examples.graphs.bfs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BFSDistance<T> {
    private int weight;
    private Edge<T> edge;
    private BFSDistance<T> prev;
}
