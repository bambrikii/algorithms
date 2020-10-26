package org.bambrikii.examples.graphs.bfs;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class BfsDistance<T> {
    private int weight;
    private Edge<T> edge;
    private BfsDistance<T> prev;
}
