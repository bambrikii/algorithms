package org.bambrikii.examples.algorithms.incubator.graphs.dfs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class TarjanAlgo {
    private Map<Integer, List<Integer>> v = new LinkedHashMap<>();
    private Map<Integer, Integer> time = new HashMap<>();
    private Map<Integer, Integer> min = new HashMap<>();

    public TarjanAlgo edge(int from, int to) {
        if (!v.containsKey(from)) {
            v.put(from, new ArrayList<>());
        }
        v.get(from).add(to);
        return this;
    }

    private void print(Map<Integer, List<Integer>> components) {
        for (List<Integer> component : components.values()) {
            System.out.println(component);
        }
    }

    public static void main(String[] args) {
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

                .edge(6, 7);

        Map<Integer, Integer> ids = new HashMap<>();
        Map<Integer, List<Integer>> com = algo.find(ids);
        algo.print(com);
    }

    private int counter = 0;

    private Map<Integer, List<Integer>> find(Map<Integer, Integer> ids) {
        for (Integer from : v.keySet()) {
            if (!ids.containsKey(from)) {
                int counter = this.counter++;
                ids.put(from, counter);
                dfs(from, ids);
            }
        }
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

    private void dfs(Integer from, Map<Integer, Integer> ids) {
        List<Integer> edges = v.get(from);
        if (edges == null || edges.isEmpty()) {
            return;
        }
        for (Integer edge : edges) {
            if (!ids.containsKey(edge)) {
                int counter = this.counter++;
                ids.put(edge, counter);
            }
            if (ids.get(edge) > ids.get(from)) {
                dfs(edge, ids);
                if (ids.get(edge) > ids.get(from)) {
                    continue;
                }
            }
            Integer id = ids.get(edge);
            markAgain(from, ids, id);
            ids.put(from, id);
        }
    }

    private void markAgain(Integer from, Map<Integer, Integer> ids, Integer id) {
        if (!ids.containsKey(from)) {
            return;
        }
        if (ids.get(from) == id) {
            return;
        }
        List<Integer> edges = v.get(from);
        if (edges == null || edges.isEmpty()) {
            return;
        }
        for (Integer edge : edges) {
            markAgain(edge, ids, id);
        }
    }
}
