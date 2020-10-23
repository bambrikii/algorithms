package org.bambrikii.examples.graphs.bfs;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
public class Vertex<T> {
    private T val;
}
