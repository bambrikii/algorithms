package org.bambrikii.examples.algorithms.incubator.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class TarjanAlgo {
    private Map<Integer, List<Integer>> v = new HashMap<>();

    public TarjanAlgo edge(int from, int to) {
        if (!v.containsKey(from)) {
            v.put(from, new ArrayList<>());
        }
        v.get(from).add(to);
        return this;
    }

    private void print(Map<Integer, List<Integer>> components) {
        System.out.println("---");
        for (List<Integer> component : components.values()) {
            System.out.println(component);
        }
    }

    public static void main(String[] args) {
        main1();
        main2();
    }

    private static void main1() {
        TarjanAlgo algo = new TarjanAlgo();
        algo
                .edge(0, 1)
                .edge(1, 2)
                .edge(2, 3)
                .edge(3, 0)

                .edge(2, 4)

                .edge(4, 5)
                .edge(5, 6)
                .edge(6, 4)

                .edge(6, 7)

                .print(algo.find(new HashMap<>()));
    }

    private static void main2() {
        TarjanAlgo algo = new TarjanAlgo();
        algo
                .edge(2, 8)
                .edge(8, 9)
                .edge(9, 2)

                .edge(0, 1)
                .edge(1, 2)
                .edge(2, 3)
                .edge(3, 0)

                .edge(2, 4)

                .edge(4, 5)
                .edge(5, 6)
                .edge(6, 4)

                .edge(6, 7)

                .print(algo.find(new HashMap<>()));
    }

    private int counter = 0;

    private Map<Integer, List<Integer>> collect(Map<Integer, Integer> ids) {
        Map<Integer, List<Integer>> results = new HashMap<>();
        for (Map.Entry<Integer, Integer> entry : ids.entrySet()) {
            Integer value = entry.getValue();
            if (!results.containsKey(value)) {
                results.put(value, new ArrayList<>());
            }
            results.get(value).add(entry.getKey());
        }
        return results;
    }

    private Map<Integer, List<Integer>> find(Map<Integer, Integer> ids) {
        for (Integer from : v.keySet()) {
            LinkedHashSet<Integer> stack = new LinkedHashSet<>();
            if (!ids.containsKey(from)) {
                int counter = this.counter++;
                ids.put(from, counter);
            }
            stack.add(from);
            dfs(from, ids, stack);
            stack.clear();
        }
        return collect(ids);
    }

    private void dfs(Integer from, Map<Integer, Integer> ids, Set<Integer> stack) {
        Integer min = ids.get(from);
        List<Integer> edges = v.get(from);
        if (edges == null || edges.isEmpty()) {
            return;
        }
        for (Integer edge : edges) {
            if (!ids.containsKey(edge)) {
                int counter = this.counter++;
                ids.put(edge, counter);
            }
            if (stack.contains(edge)) {
                if (min > ids.get(edge)) {
                    min = ids.get(edge);
                    continue;
                }
                continue;
            }
            stack.add(edge);
            dfs(edge, ids, stack);
            Integer id = ids.get(edge);
            if (min > ids.get(edge)) {
                min = id;
            }
        }
        ids.put(from, min);
    }
}
