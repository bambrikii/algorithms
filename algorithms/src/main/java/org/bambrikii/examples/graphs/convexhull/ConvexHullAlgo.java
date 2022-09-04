package org.bambrikii.examples.graphs.convexhull;

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

    private double calcDistance(Coord from, Coord to) {
        int x1 = from.x();
        int y1 = from.y();

        int x2 = to.x();
        int y2 = to.y();

        int dx = x2 - x1;
        int dy = y2 - y1;

        return Math.sqrt(dx * dx + dy * dy);
    }

    public List<Coord> find() {
        var points = new HashSet<>(coords);
        if (coords.isEmpty()) {
            return Collections.emptyList();
        }
        var from = coords
                .stream()
                .sorted(Comparator.comparingInt(Coord::y).thenComparingInt(Coord::x))
                .findFirst()
                .get();

        log("lastPoint: " + from);

        List<Coord> results = new ArrayList<>();
        results.add(from);
        points.remove(from);
        double minAllowedNextAngle = 0.0;
        while (!points.isEmpty()) {
            double nextAngle = Double.MAX_VALUE; // Angle related to x vector is increasing
            double nextLen = Double.MAX_VALUE; // if multiple points have the same angle, we choose the closest one.
            Coord nextPoint = null;
            for (Coord to : points) {
                var angle = calcAngle(from, to);
                var len = calcDistance(from, to);
                if (angle >= minAllowedNextAngle && (angle < nextAngle || (angle == nextAngle && len <= nextLen))) {
                    nextPoint = to;
                    nextLen = len;
                    nextAngle = angle;
                } else {
                    log("skipping " + to + " <- " + from + ", " + angle + ">" + minAllowedNextAngle + "," + nextLen);
                }
            }
            log("nextPoint: " + nextPoint);
            log("nextAngle: " + nextAngle);
            if (nextPoint == null) {
                break;
            }
            if (nextAngle >= minAllowedNextAngle) {
                minAllowedNextAngle = nextAngle;
                results.add(nextPoint);
            } else {
                log("skipping " + nextPoint + " <- " + from);
            }
            points.remove(nextPoint);
            from = nextPoint;
        }

        return results;
    }
}
