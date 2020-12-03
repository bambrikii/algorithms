package org.bambrikii.examples.graphs.allvertexes;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class HamiltonPathVertex {
    private boolean visited;
    private List<HamiltonPathEdge> edges = new ArrayList<>();

    public void addEdge(HamiltonPathEdge edge) {
        edges.add(edge);
    }
}
