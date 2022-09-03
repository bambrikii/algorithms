package org.bambrikii.examples.algorithms.incubator.graphs.dfs;

import org.bambrikii.examples.algorithms.incubator.graphs.convexhull.ConvexHullAlgo;
import org.bambrikii.examples.algorithms.incubator.graphs.convexhull.Coord;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;

public class ConvexHullAlgoTest {
    private static Collection<Object[]> params() {
        Collection<Object[]> params = new ArrayList<>();
        params.add(
                params(
                        asList(new Coord(3, 3), new Coord(4, 4), new Coord(3, 5), new Coord(2, 4)),
                        asList(new Coord(3, 3), new Coord(4, 4), new Coord(3, 5), new Coord(2, 4))
                )
        );
        params.add(
                params(
                        asList(new Coord(3, 3), new Coord(4, 3), new Coord(4, 4), new Coord(3, 5), new Coord(2, 4)),
                        asList(new Coord(3, 3), new Coord(4, 3), new Coord(4, 4), new Coord(3, 5), new Coord(2, 4))
                )
        );
        // TODO: more test cases
        // with negative values
        return params;
    }

    private static Object[] params(List<Coord> coords, List<Coord> expected) {
        return new Object[]{coords, expected};
    }

    @ParameterizedTest
    @MethodSource("params")
    public void shouldFind(List<Coord> coords, List<Coord> expected) {
        assertThat(new ConvexHullAlgo().coords(coords).find()).isEqualTo(expected);
    }
}
