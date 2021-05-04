package org.bambrikii.examples.graphs.mstree.prim;

import org.bambrikii.examples.graphs.mstree.Edge;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.bambrikii.examples.graphs.mstree.kruskal.KruskalAlgoMatrixTest.buildInput;
import static org.bambrikii.examples.graphs.mstree.kruskal.KruskalAlgoMatrixTest.printOutput;

public class PrimAlgoMatrixTest {
    @Test
    public void shouldFind() {
        PrimAlgoMatrix algo = new PrimAlgoMatrix();

        List<Edge> result = algo.find(buildInput());

        printOutput(result);
    }
}
