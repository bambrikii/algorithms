package org.bambrikii.examples.graphs.travellingsalesman;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

import java.util.LinkedHashSet;

@Getter
@AllArgsConstructor
@ToString
public class TravellingSalesmanResult {
    private final Integer weight;
    private final LinkedHashSet<Integer> vertexes;
}
