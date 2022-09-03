package org.bambrikii.examples.algorithms.incubator.graphs.convexhull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;

public class ConvexHullAlgo {

    private final List<Coord> coords;

    private void log(Object msg) {
        System.out.println("log: " + msg);
    }

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

        if (dx > 0) {
            if (dy >= 0) {
                return Math.abs(dy / d);
            } else {
                return 1 - Math.abs(dy / d) + 3;
            }
        } else if (dx < 0) {
            if (dy > 0) {
                return 1 - Math.abs(dy / d) + 1;
            } else {
                return Math.abs(dy / d) + 2;
            }
        } else {
            if (dy > 0) {
                return 1;
            } else if (dy < 0) {
                return 3;
            } else {
                return 0;
            }
        }
    }

    public List<Coord> find() {
        var points = new HashSet<>(coords);
        if (coords.isEmpty()) {
            return Collections.emptyList();
        }
        var lastPoint = coords
                .stream()
                .sorted(Comparator.comparingInt(Coord::y).thenComparingInt(Coord::x))
                .findFirst()
                .get();

        log("lastPoint: " + lastPoint);

        List<Coord> results = new ArrayList<>();
        results.add(lastPoint);
        points.remove(lastPoint);
        double minAngle = 0.0;
        while (!points.isEmpty()) {
            double nextAngle = Double.MAX_VALUE;
            Coord nextPoint = null;
            for (Coord point : points) {
                var angle = calcAngle(lastPoint, point);
                if (angle < nextAngle && angle >= minAngle) {
                    nextPoint = point;
                    nextAngle = angle;
                } else {
                    log("skipping " + point + " <- " + lastPoint/* + ", " + angle + ">" + minAngle*/);
                }
            }
            log("nextPoint: " + nextPoint);
            log("nextAngle: " + nextAngle);
            if (nextPoint == null) {
                break;
            }
            if (nextAngle >= minAngle) {
                minAngle = nextAngle;
                results.add(nextPoint);
            } else {
                log("skipping " + nextPoint + " <- " + lastPoint);
            }
            points.remove(nextPoint);
            lastPoint = nextPoint;
        }

        return results;
    }

}
