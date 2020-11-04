package org.bambrikii.examples.graphs.shortestpath;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@EqualsAndHashCode
@ToString
@AllArgsConstructor
public class BellmanFordEdge2 {
    private Integer from;
    private Integer to;
    private Integer weight;
}
