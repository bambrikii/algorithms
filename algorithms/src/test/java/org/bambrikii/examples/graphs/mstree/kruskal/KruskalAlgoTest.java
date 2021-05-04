package org.bambrikii.examples.graphs.mstree.kruskal;

import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.function.Predicate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class KruskalAlgoTest {
    @Test
    public void shouldFindMinimumSpanningTree() {
        KruskalTree<String, Integer> tree = new KruskalTree<>();

        tree.edge(1, "1-7", "1-3-4-5");
        tree.edge(2, "2-4", "2-3-6");
        tree.edge(3, "2-3-6", "1-3-4-5");
        tree.edge(4, "1-3-4-5", "2-4");
        tree.edge(5, "1-3-4-5", "5-6-7");
        tree.edge(6, "2-3-6", "5-6-7");
        tree.edge(7, "1-7", "5-6-7");

        KruskalAlgo<String, Integer> algo = new KruskalAlgo<>();
        List<KruskalEdge<Integer, String>> result = algo.minimumSpanningTree(tree);

        for (KruskalEdge<Integer, String> edge : result) {
            System.out.println(edge);
        }

        assertEquals(4, result.size());
        assertTrue(matchEdge(result, 1, "1-7", "1-3-4-5"));
        assertTrue(matchEdge(result, 2, "2-4", "2-3-6"));
        assertTrue(matchEdge(result, 3, "2-3-6", "1-3-4-5"));
        assertTrue(matchEdge(result, 5, "1-3-4-5", "5-6-7"));
    }

    private boolean matchEdge( //
                               List<KruskalEdge<Integer, String>> result, //
                               Integer edgeVal, String vertex1Val, String vertex2Val //
    ) {
        return result.stream().filter(matchEdgePredicate(edgeVal, vertex1Val, vertex2Val)).findAny().isPresent();
    }

    private Predicate<KruskalEdge<Integer, String>> matchEdgePredicate(Integer edgeVal, String vertex1Val,
                                                                       String vertex2Val) {
        return new Predicate<KruskalEdge<Integer, String>>() {

            @Override
            public boolean test(KruskalEdge<Integer, String> edge) {
                Integer val = edge.getVal();
                String val1 = edge.getVertex1().getVal();
                String val2 = edge.getVertex2().getVal();
                return Integer.valueOf(edgeVal).equals(val) //
                        && (vertex1Val.equals(val1) || vertex2Val.equals(val1)) //
                        && (vertex2Val.equals(val2) || vertex1Val.equals(val2)) //
                        ;
            }
        };
    }
}
