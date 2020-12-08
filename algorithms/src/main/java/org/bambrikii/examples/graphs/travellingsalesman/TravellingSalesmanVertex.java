package org.bambrikii.examples.graphs.travellingsalesman;

import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class TravellingSalesmanVertex {
    private final List<TravellingSalesmanEdge> edges = new ArrayList<>();
}
