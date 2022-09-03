package org.bambrikii.examples.algorithms.incubator.graphs.convexhull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ConvexHullAlgo {

    private final List<Coord> coords;

    public ConvexHullAlgo() {
        this.coords = new ArrayList<>();
    }

    public ConvexHullAlgo coord(int x, int y) {
        coords.add(new Coord(x, y));
        return this;
    }

    public ConvexHullAlgo coords(List<Coord> coords) {
        this.coords.addAll(coords);
        return this;
    }

    // TODO: is this logic ok?
    private double calcAngle(Coord from, Coord to) {
        int x1 = from.x();
        int y1 = from.y();

        int x2 = to.x();
        int y2 = to.y();

        int dx = x2 - x1;
        int dy = y2 - y1;

        double d = Math.sqrt(dx * dx + dy * dy);
        double sin = dy / d;

        if (dx >= 0) {
            if (dy >= 0) {
                return Math.abs(sin);
            } else {
                return Math.abs(sin) + 3;
            }
        } else {
            if (dy > 0) {
                return Math.abs(sin) + 1;
            } else {
                return Math.abs(sin) + 2;
            }
        }
    }

    public List<Coord> find() {
        var points = new HashSet<>(coords);
        if (coords.isEmpty()) {
            return Collections.emptyList();
        }
        var curr = coords
                .stream()
                .sorted(Comparator.comparingInt(Coord::y).thenComparingInt(Coord::x))
                .findFirst()
                .get();

        List<Coord> results = new ArrayList<>();
        points.remove(curr);
        results.add(curr);
        while (!points.isEmpty()) {
            curr = findMin(points, curr);
            if (curr == null) {
                break;
            }
            points.remove(curr);
            results.add(curr);
        }

        return results;
    }

    private Coord findMin(HashSet<Coord> points, Coord curr) {
        double minAngle = Double.MAX_VALUE;
        Coord next = null;
        for (Coord coord : points) {
            var angle = calcAngle(curr, coord);
            if (angle < minAngle) {
                next = coord;
                minAngle = angle;
            }
        }
        return next;
    }
}
